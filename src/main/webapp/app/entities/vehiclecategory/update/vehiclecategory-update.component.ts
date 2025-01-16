import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IVehiclecategory } from '../vehiclecategory.model';
import { VehiclecategoryService } from '../service/vehiclecategory.service';
import { VehiclecategoryFormService, VehiclecategoryFormGroup } from './vehiclecategory-form.service';

@Component({
  standalone: true,
  selector: 'jhi-vehiclecategory-update',
  templateUrl: './vehiclecategory-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VehiclecategoryUpdateComponent implements OnInit {
  isSaving = false;
  vehiclecategory: IVehiclecategory | null = null;

  protected vehiclecategoryService = inject(VehiclecategoryService);
  protected vehiclecategoryFormService = inject(VehiclecategoryFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: VehiclecategoryFormGroup = this.vehiclecategoryFormService.createVehiclecategoryFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vehiclecategory }) => {
      this.vehiclecategory = vehiclecategory;
      if (vehiclecategory) {
        this.updateForm(vehiclecategory);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vehiclecategory = this.vehiclecategoryFormService.getVehiclecategory(this.editForm);
    if (vehiclecategory.id !== null) {
      this.subscribeToSaveResponse(this.vehiclecategoryService.update(vehiclecategory));
    } else {
      this.subscribeToSaveResponse(this.vehiclecategoryService.create(vehiclecategory));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehiclecategory>>): void {
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

  protected updateForm(vehiclecategory: IVehiclecategory): void {
    this.vehiclecategory = vehiclecategory;
    this.vehiclecategoryFormService.resetForm(this.editForm, vehiclecategory);
  }
}
