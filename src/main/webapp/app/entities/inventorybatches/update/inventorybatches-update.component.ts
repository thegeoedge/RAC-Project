import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IInventorybatches } from '../inventorybatches.model';
import { InventorybatchesService } from '../service/inventorybatches.service';
import { InventorybatchesFormService, InventorybatchesFormGroup } from './inventorybatches-form.service';

@Component({
  standalone: true,
  selector: 'jhi-inventorybatches-update',
  templateUrl: './inventorybatches-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class InventorybatchesUpdateComponent implements OnInit {
  isSaving = false;
  inventorybatches: IInventorybatches | null = null;

  protected inventorybatchesService = inject(InventorybatchesService);
  protected inventorybatchesFormService = inject(InventorybatchesFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: InventorybatchesFormGroup = this.inventorybatchesFormService.createInventorybatchesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inventorybatches }) => {
      this.inventorybatches = inventorybatches;
      if (inventorybatches) {
        this.updateForm(inventorybatches);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inventorybatches = this.inventorybatchesFormService.getInventorybatches(this.editForm);
    if (inventorybatches.id !== null) {
      this.subscribeToSaveResponse(this.inventorybatchesService.update(inventorybatches));
    } else {
      this.subscribeToSaveResponse(this.inventorybatchesService.create(inventorybatches));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInventorybatches>>): void {
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

  protected updateForm(inventorybatches: IInventorybatches): void {
    this.inventorybatches = inventorybatches;
    this.inventorybatchesFormService.resetForm(this.editForm, inventorybatches);
  }
}
