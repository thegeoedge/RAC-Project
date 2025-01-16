import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IReceipt } from '../receipt.model';
import { ReceiptService } from '../service/receipt.service';
import { ReceiptFormService, ReceiptFormGroup } from './receipt-form.service';

@Component({
  standalone: true,
  selector: 'jhi-receipt-update',
  templateUrl: './receipt-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReceiptUpdateComponent implements OnInit {
  isSaving = false;
  receipt: IReceipt | null = null;

  protected receiptService = inject(ReceiptService);
  protected receiptFormService = inject(ReceiptFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ReceiptFormGroup = this.receiptFormService.createReceiptFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ receipt }) => {
      this.receipt = receipt;
      if (receipt) {
        this.updateForm(receipt);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const receipt = this.receiptFormService.getReceipt(this.editForm);
    if (receipt.id !== null) {
      this.subscribeToSaveResponse(this.receiptService.update(receipt));
    } else {
      this.subscribeToSaveResponse(this.receiptService.create(receipt));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReceipt>>): void {
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

  protected updateForm(receipt: IReceipt): void {
    this.receipt = receipt;
    this.receiptFormService.resetForm(this.editForm, receipt);
  }
}
