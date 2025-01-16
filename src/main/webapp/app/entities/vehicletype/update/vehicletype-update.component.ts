import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IVehicletype } from '../vehicletype.model';
import { VehicletypeService } from '../service/vehicletype.service';
import { VehicletypeFormService, VehicletypeFormGroup } from './vehicletype-form.service';

@Component({
  standalone: true,
  selector: 'jhi-vehicletype-update',
  templateUrl: './vehicletype-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VehicletypeUpdateComponent implements OnInit {
  isSaving = false;
  vehicletype: IVehicletype | null = null;

  protected vehicletypeService = inject(VehicletypeService);
  protected vehicletypeFormService = inject(VehicletypeFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: VehicletypeFormGroup = this.vehicletypeFormService.createVehicletypeFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vehicletype }) => {
      this.vehicletype = vehicletype;
      if (vehicletype) {
        this.updateForm(vehicletype);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vehicletype = this.vehicletypeFormService.getVehicletype(this.editForm);
    if (vehicletype.id !== null) {
      this.subscribeToSaveResponse(this.vehicletypeService.update(vehicletype));
    } else {
      this.subscribeToSaveResponse(this.vehicletypeService.create(vehicletype));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehicletype>>): void {
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

  protected updateForm(vehicletype: IVehicletype): void {
    this.vehicletype = vehicletype;
    this.vehicletypeFormService.resetForm(this.editForm, vehicletype);
  }
}
