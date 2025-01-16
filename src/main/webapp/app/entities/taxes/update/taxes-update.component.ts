import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITaxes } from '../taxes.model';
import { TaxesService } from '../service/taxes.service';
import { TaxesFormService, TaxesFormGroup } from './taxes-form.service';

@Component({
  standalone: true,
  selector: 'jhi-taxes-update',
  templateUrl: './taxes-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TaxesUpdateComponent implements OnInit {
  isSaving = false;
  taxes: ITaxes | null = null;

  protected taxesService = inject(TaxesService);
  protected taxesFormService = inject(TaxesFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: TaxesFormGroup = this.taxesFormService.createTaxesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taxes }) => {
      this.taxes = taxes;
      if (taxes) {
        this.updateForm(taxes);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const taxes = this.taxesFormService.getTaxes(this.editForm);
    if (taxes.id !== null) {
      this.subscribeToSaveResponse(this.taxesService.update(taxes));
    } else {
      this.subscribeToSaveResponse(this.taxesService.create(taxes));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaxes>>): void {
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

  protected updateForm(taxes: ITaxes): void {
    this.taxes = taxes;
    this.taxesFormService.resetForm(this.editForm, taxes);
  }
}
