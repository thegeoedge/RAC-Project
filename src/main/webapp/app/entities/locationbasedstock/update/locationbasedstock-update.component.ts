import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ILocationbasedstock } from '../locationbasedstock.model';
import { LocationbasedstockService } from '../service/locationbasedstock.service';
import { LocationbasedstockFormService, LocationbasedstockFormGroup } from './locationbasedstock-form.service';

@Component({
  standalone: true,
  selector: 'jhi-locationbasedstock-update',
  templateUrl: './locationbasedstock-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class LocationbasedstockUpdateComponent implements OnInit {
  isSaving = false;
  locationbasedstock: ILocationbasedstock | null = null;

  protected locationbasedstockService = inject(LocationbasedstockService);
  protected locationbasedstockFormService = inject(LocationbasedstockFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: LocationbasedstockFormGroup = this.locationbasedstockFormService.createLocationbasedstockFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locationbasedstock }) => {
      this.locationbasedstock = locationbasedstock;
      if (locationbasedstock) {
        this.updateForm(locationbasedstock);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const locationbasedstock = this.locationbasedstockFormService.getLocationbasedstock(this.editForm);
    if (locationbasedstock.id !== null) {
      this.subscribeToSaveResponse(this.locationbasedstockService.update(locationbasedstock));
    } else {
      this.subscribeToSaveResponse(this.locationbasedstockService.create(locationbasedstock));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocationbasedstock>>): void {
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

  protected updateForm(locationbasedstock: ILocationbasedstock): void {
    this.locationbasedstock = locationbasedstock;
    this.locationbasedstockFormService.resetForm(this.editForm, locationbasedstock);
  }
}
