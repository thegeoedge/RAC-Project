import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocarecompany } from '../autocarecompany.model';
import { AutocarecompanyService } from '../service/autocarecompany.service';
import { AutocarecompanyFormService, AutocarecompanyFormGroup } from './autocarecompany-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocarecompany-update',
  templateUrl: './autocarecompany-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocarecompanyUpdateComponent implements OnInit {
  isSaving = false;
  autocarecompany: IAutocarecompany | null = null;

  protected autocarecompanyService = inject(AutocarecompanyService);
  protected autocarecompanyFormService = inject(AutocarecompanyFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocarecompanyFormGroup = this.autocarecompanyFormService.createAutocarecompanyFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocarecompany }) => {
      this.autocarecompany = autocarecompany;
      if (autocarecompany) {
        this.updateForm(autocarecompany);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocarecompany = this.autocarecompanyFormService.getAutocarecompany(this.editForm);
    if (autocarecompany.id !== null) {
      this.subscribeToSaveResponse(this.autocarecompanyService.update(autocarecompany));
    } else {
      this.subscribeToSaveResponse(this.autocarecompanyService.create(autocarecompany));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocarecompany>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(autocarecompany: IAutocarecompany): void {
    this.autocarecompany = autocarecompany;
    this.autocarecompanyFormService.resetForm(this.editForm, autocarecompany);
  }
}
