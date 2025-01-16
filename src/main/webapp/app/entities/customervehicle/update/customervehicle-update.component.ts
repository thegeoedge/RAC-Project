import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICustomervehicle } from '../customervehicle.model';
import { CustomervehicleService } from '../service/customervehicle.service';
import { CustomervehicleFormService, CustomervehicleFormGroup } from './customervehicle-form.service';

@Component({
  standalone: true,
  selector: 'jhi-customervehicle-update',
  templateUrl: './customervehicle-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CustomervehicleUpdateComponent implements OnInit {
  isSaving = false;
  customervehicle: ICustomervehicle | null = null;

  protected customervehicleService = inject(CustomervehicleService);
  protected customervehicleFormService = inject(CustomervehicleFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CustomervehicleFormGroup = this.customervehicleFormService.createCustomervehicleFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customervehicle }) => {
      this.customervehicle = customervehicle;
      if (customervehicle) {
        this.updateForm(customervehicle);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customervehicle = this.customervehicleFormService.getCustomervehicle(this.editForm);
    if (customervehicle.id !== null) {
      this.subscribeToSaveResponse(this.customervehicleService.update(customervehicle));
    } else {
      this.subscribeToSaveResponse(this.customervehicleService.create(customervehicle));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomervehicle>>): void {
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

  protected updateForm(customervehicle: ICustomervehicle): void {
    this.customervehicle = customervehicle;
    this.customervehicleFormService.resetForm(this.editForm, customervehicle);
  }
}
