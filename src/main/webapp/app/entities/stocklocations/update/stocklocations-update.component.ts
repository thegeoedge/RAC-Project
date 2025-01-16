import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IStocklocations } from '../stocklocations.model';
import { StocklocationsService } from '../service/stocklocations.service';
import { StocklocationsFormService, StocklocationsFormGroup } from './stocklocations-form.service';

@Component({
  standalone: true,
  selector: 'jhi-stocklocations-update',
  templateUrl: './stocklocations-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class StocklocationsUpdateComponent implements OnInit {
  isSaving = false;
  stocklocations: IStocklocations | null = null;

  protected stocklocationsService = inject(StocklocationsService);
  protected stocklocationsFormService = inject(StocklocationsFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: StocklocationsFormGroup = this.stocklocationsFormService.createStocklocationsFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stocklocations }) => {
      this.stocklocations = stocklocations;
      if (stocklocations) {
        this.updateForm(stocklocations);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const stocklocations = this.stocklocationsFormService.getStocklocations(this.editForm);
    if (stocklocations.id !== null) {
      this.subscribeToSaveResponse(this.stocklocationsService.update(stocklocations));
    } else {
      this.subscribeToSaveResponse(this.stocklocationsService.create(stocklocations));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStocklocations>>): void {
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

  protected updateForm(stocklocations: IStocklocations): void {
    this.stocklocations = stocklocations;
    this.stocklocationsFormService.resetForm(this.editForm, stocklocations);
  }
}
