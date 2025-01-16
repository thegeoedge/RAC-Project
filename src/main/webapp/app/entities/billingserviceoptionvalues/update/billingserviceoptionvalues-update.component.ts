import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';
import { BillingserviceoptionvaluesService } from '../service/billingserviceoptionvalues.service';
import { BillingserviceoptionvaluesFormService, BillingserviceoptionvaluesFormGroup } from './billingserviceoptionvalues-form.service';

@Component({
  standalone: true,
  selector: 'jhi-billingserviceoptionvalues-update',
  templateUrl: './billingserviceoptionvalues-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BillingserviceoptionvaluesUpdateComponent implements OnInit {
  isSaving = false;
  billingserviceoptionvalues: IBillingserviceoptionvalues | null = null;

  protected billingserviceoptionvaluesService = inject(BillingserviceoptionvaluesService);
  protected billingserviceoptionvaluesFormService = inject(BillingserviceoptionvaluesFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: BillingserviceoptionvaluesFormGroup = this.billingserviceoptionvaluesFormService.createBillingserviceoptionvaluesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingserviceoptionvalues }) => {
      this.billingserviceoptionvalues = billingserviceoptionvalues;
      if (billingserviceoptionvalues) {
        this.updateForm(billingserviceoptionvalues);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const billingserviceoptionvalues = this.billingserviceoptionvaluesFormService.getBillingserviceoptionvalues(this.editForm);
    if (billingserviceoptionvalues.id !== null) {
      this.subscribeToSaveResponse(this.billingserviceoptionvaluesService.update(billingserviceoptionvalues));
    } else {
      this.subscribeToSaveResponse(this.billingserviceoptionvaluesService.create(billingserviceoptionvalues));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillingserviceoptionvalues>>): void {
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

  protected updateForm(billingserviceoptionvalues: IBillingserviceoptionvalues): void {
    this.billingserviceoptionvalues = billingserviceoptionvalues;
    this.billingserviceoptionvaluesFormService.resetForm(this.editForm, billingserviceoptionvalues);
  }
}
