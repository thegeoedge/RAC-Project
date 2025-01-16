import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IBillingserviceoption } from '../billingserviceoption.model';
import { BillingserviceoptionService } from '../service/billingserviceoption.service';
import { BillingserviceoptionFormService, BillingserviceoptionFormGroup } from './billingserviceoption-form.service';

@Component({
  standalone: true,
  selector: 'jhi-billingserviceoption-update',
  templateUrl: './billingserviceoption-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BillingserviceoptionUpdateComponent implements OnInit {
  isSaving = false;
  billingserviceoption: IBillingserviceoption | null = null;

  protected billingserviceoptionService = inject(BillingserviceoptionService);
  protected billingserviceoptionFormService = inject(BillingserviceoptionFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: BillingserviceoptionFormGroup = this.billingserviceoptionFormService.createBillingserviceoptionFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingserviceoption }) => {
      this.billingserviceoption = billingserviceoption;
      if (billingserviceoption) {
        this.updateForm(billingserviceoption);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const billingserviceoption = this.billingserviceoptionFormService.getBillingserviceoption(this.editForm);
    if (billingserviceoption.id !== null) {
      this.subscribeToSaveResponse(this.billingserviceoptionService.update(billingserviceoption));
    } else {
      this.subscribeToSaveResponse(this.billingserviceoptionService.create(billingserviceoption));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillingserviceoption>>): void {
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

  protected updateForm(billingserviceoption: IBillingserviceoption): void {
    this.billingserviceoption = billingserviceoption;
    this.billingserviceoptionFormService.resetForm(this.editForm, billingserviceoption);
  }
}
