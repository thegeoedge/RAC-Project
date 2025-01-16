import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocareservicemillages } from '../autocareservicemillages.model';
import { AutocareservicemillagesService } from '../service/autocareservicemillages.service';
import { AutocareservicemillagesFormService, AutocareservicemillagesFormGroup } from './autocareservicemillages-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocareservicemillages-update',
  templateUrl: './autocareservicemillages-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocareservicemillagesUpdateComponent implements OnInit {
  isSaving = false;
  autocareservicemillages: IAutocareservicemillages | null = null;

  protected autocareservicemillagesService = inject(AutocareservicemillagesService);
  protected autocareservicemillagesFormService = inject(AutocareservicemillagesFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocareservicemillagesFormGroup = this.autocareservicemillagesFormService.createAutocareservicemillagesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocareservicemillages }) => {
      this.autocareservicemillages = autocareservicemillages;
      if (autocareservicemillages) {
        this.updateForm(autocareservicemillages);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocareservicemillages = this.autocareservicemillagesFormService.getAutocareservicemillages(this.editForm);
    if (autocareservicemillages.id !== null) {
      this.subscribeToSaveResponse(this.autocareservicemillagesService.update(autocareservicemillages));
    } else {
      this.subscribeToSaveResponse(this.autocareservicemillagesService.create(autocareservicemillages));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocareservicemillages>>): void {
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

  protected updateForm(autocareservicemillages: IAutocareservicemillages): void {
    this.autocareservicemillages = autocareservicemillages;
    this.autocareservicemillagesFormService.resetForm(this.editForm, autocareservicemillages);
  }
}
