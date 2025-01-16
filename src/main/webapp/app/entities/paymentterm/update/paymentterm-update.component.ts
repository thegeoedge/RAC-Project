import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPaymentterm } from '../paymentterm.model';
import { PaymenttermService } from '../service/paymentterm.service';
import { PaymenttermFormService, PaymenttermFormGroup } from './paymentterm-form.service';

@Component({
  standalone: true,
  selector: 'jhi-paymentterm-update',
  templateUrl: './paymentterm-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class PaymenttermUpdateComponent implements OnInit {
  isSaving = false;
  paymentterm: IPaymentterm | null = null;

  protected paymenttermService = inject(PaymenttermService);
  protected paymenttermFormService = inject(PaymenttermFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: PaymenttermFormGroup = this.paymenttermFormService.createPaymenttermFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentterm }) => {
      this.paymentterm = paymentterm;
      if (paymentterm) {
        this.updateForm(paymentterm);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paymentterm = this.paymenttermFormService.getPaymentterm(this.editForm);
    if (paymentterm.id !== null) {
      this.subscribeToSaveResponse(this.paymenttermService.update(paymentterm));
    } else {
      this.subscribeToSaveResponse(this.paymenttermService.create(paymentterm));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentterm>>): void {
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

  protected updateForm(paymentterm: IPaymentterm): void {
    this.paymentterm = paymentterm;
    this.paymenttermFormService.resetForm(this.editForm, paymentterm);
  }
}
