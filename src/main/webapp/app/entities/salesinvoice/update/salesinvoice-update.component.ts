import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ISalesinvoice } from '../salesinvoice.model';
import { SalesinvoiceService } from '../service/salesinvoice.service';
import { SalesinvoiceFormService, SalesinvoiceFormGroup } from './salesinvoice-form.service';

@Component({
  standalone: true,
  selector: 'jhi-salesinvoice-update',
  templateUrl: './salesinvoice-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SalesinvoiceUpdateComponent implements OnInit {
  isSaving = false;
  salesinvoice: ISalesinvoice | null = null;

  protected salesinvoiceService = inject(SalesinvoiceService);
  protected salesinvoiceFormService = inject(SalesinvoiceFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: SalesinvoiceFormGroup = this.salesinvoiceFormService.createSalesinvoiceFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ salesinvoice }) => {
      this.salesinvoice = salesinvoice;
      if (salesinvoice) {
        this.updateForm(salesinvoice);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const salesinvoice = this.salesinvoiceFormService.getSalesinvoice(this.editForm);
    if (salesinvoice.id !== null) {
      this.subscribeToSaveResponse(this.salesinvoiceService.update(salesinvoice));
    } else {
      this.subscribeToSaveResponse(this.salesinvoiceService.create(salesinvoice));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalesinvoice>>): void {
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

  protected updateForm(salesinvoice: ISalesinvoice): void {
    this.salesinvoice = salesinvoice;
    this.salesinvoiceFormService.resetForm(this.editForm, salesinvoice);
  }
}
