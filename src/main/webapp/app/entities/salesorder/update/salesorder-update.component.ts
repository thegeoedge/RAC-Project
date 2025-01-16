import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ISalesorder } from '../salesorder.model';
import { SalesorderService } from '../service/salesorder.service';
import { SalesorderFormService, SalesorderFormGroup } from './salesorder-form.service';

@Component({
  standalone: true,
  selector: 'jhi-salesorder-update',
  templateUrl: './salesorder-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SalesorderUpdateComponent implements OnInit {
  isSaving = false;
  salesorder: ISalesorder | null = null;

  protected salesorderService = inject(SalesorderService);
  protected salesorderFormService = inject(SalesorderFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: SalesorderFormGroup = this.salesorderFormService.createSalesorderFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ salesorder }) => {
      this.salesorder = salesorder;
      if (salesorder) {
        this.updateForm(salesorder);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const salesorder = this.salesorderFormService.getSalesorder(this.editForm);
    if (salesorder.id !== null) {
      this.subscribeToSaveResponse(this.salesorderService.update(salesorder));
    } else {
      this.subscribeToSaveResponse(this.salesorderService.create(salesorder));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalesorder>>): void {
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

  protected updateForm(salesorder: ISalesorder): void {
    this.salesorder = salesorder;
    this.salesorderFormService.resetForm(this.editForm, salesorder);
  }
}
