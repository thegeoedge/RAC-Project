import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IVehiclebrandname } from '../vehiclebrandname.model';
import { VehiclebrandnameService } from '../service/vehiclebrandname.service';
import { VehiclebrandnameFormService, VehiclebrandnameFormGroup } from './vehiclebrandname-form.service';

@Component({
  standalone: true,
  selector: 'jhi-vehiclebrandname-update',
  templateUrl: './vehiclebrandname-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VehiclebrandnameUpdateComponent implements OnInit {
  isSaving = false;
  vehiclebrandname: IVehiclebrandname | null = null;

  protected vehiclebrandnameService = inject(VehiclebrandnameService);
  protected vehiclebrandnameFormService = inject(VehiclebrandnameFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: VehiclebrandnameFormGroup = this.vehiclebrandnameFormService.createVehiclebrandnameFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vehiclebrandname }) => {
      this.vehiclebrandname = vehiclebrandname;
      if (vehiclebrandname) {
        this.updateForm(vehiclebrandname);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vehiclebrandname = this.vehiclebrandnameFormService.getVehiclebrandname(this.editForm);
    if (vehiclebrandname.id !== null) {
      this.subscribeToSaveResponse(this.vehiclebrandnameService.update(vehiclebrandname));
    } else {
      this.subscribeToSaveResponse(this.vehiclebrandnameService.create(vehiclebrandname));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehiclebrandname>>): void {
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

  protected updateForm(vehiclebrandname: IVehiclebrandname): void {
    this.vehiclebrandname = vehiclebrandname;
    this.vehiclebrandnameFormService.resetForm(this.editForm, vehiclebrandname);
  }
}
