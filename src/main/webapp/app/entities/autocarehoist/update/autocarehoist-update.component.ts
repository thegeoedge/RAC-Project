import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocarehoist } from '../autocarehoist.model';
import { AutocarehoistService } from '../service/autocarehoist.service';
import { AutocarehoistFormService, AutocarehoistFormGroup } from './autocarehoist-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocarehoist-update',
  templateUrl: './autocarehoist-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocarehoistUpdateComponent implements OnInit {
  isSaving = false;
  autocarehoist: IAutocarehoist | null = null;

  protected autocarehoistService = inject(AutocarehoistService);
  protected autocarehoistFormService = inject(AutocarehoistFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocarehoistFormGroup = this.autocarehoistFormService.createAutocarehoistFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocarehoist }) => {
      this.autocarehoist = autocarehoist;
      if (autocarehoist) {
        this.updateForm(autocarehoist);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocarehoist = this.autocarehoistFormService.getAutocarehoist(this.editForm);
    if (autocarehoist.id !== null) {
      this.subscribeToSaveResponse(this.autocarehoistService.update(autocarehoist));
    } else {
      this.subscribeToSaveResponse(this.autocarehoistService.create(autocarehoist));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocarehoist>>): void {
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

  protected updateForm(autocarehoist: IAutocarehoist): void {
    this.autocarehoist = autocarehoist;
    this.autocarehoistFormService.resetForm(this.editForm, autocarehoist);
  }
}
