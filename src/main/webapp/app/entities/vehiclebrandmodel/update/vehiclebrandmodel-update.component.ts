import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IVehiclebrandmodel } from '../vehiclebrandmodel.model';
import { VehiclebrandmodelService } from '../service/vehiclebrandmodel.service';
import { VehiclebrandmodelFormService, VehiclebrandmodelFormGroup } from './vehiclebrandmodel-form.service';

@Component({
  standalone: true,
  selector: 'jhi-vehiclebrandmodel-update',
  templateUrl: './vehiclebrandmodel-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VehiclebrandmodelUpdateComponent implements OnInit {
  isSaving = false;
  vehiclebrandmodel: IVehiclebrandmodel | null = null;

  protected vehiclebrandmodelService = inject(VehiclebrandmodelService);
  protected vehiclebrandmodelFormService = inject(VehiclebrandmodelFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: VehiclebrandmodelFormGroup = this.vehiclebrandmodelFormService.createVehiclebrandmodelFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vehiclebrandmodel }) => {
      this.vehiclebrandmodel = vehiclebrandmodel;
      if (vehiclebrandmodel) {
        this.updateForm(vehiclebrandmodel);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vehiclebrandmodel = this.vehiclebrandmodelFormService.getVehiclebrandmodel(this.editForm);
    if (vehiclebrandmodel.id !== null) {
      this.subscribeToSaveResponse(this.vehiclebrandmodelService.update(vehiclebrandmodel));
    } else {
      this.subscribeToSaveResponse(this.vehiclebrandmodelService.create(vehiclebrandmodel));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehiclebrandmodel>>): void {
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

  protected updateForm(vehiclebrandmodel: IVehiclebrandmodel): void {
    this.vehiclebrandmodel = vehiclebrandmodel;
    this.vehiclebrandmodelFormService.resetForm(this.editForm, vehiclebrandmodel);
  }
}
