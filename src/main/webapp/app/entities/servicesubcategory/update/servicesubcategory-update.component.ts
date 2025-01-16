import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IServicesubcategory } from '../servicesubcategory.model';
import { ServicesubcategoryService } from '../service/servicesubcategory.service';
import { ServicesubcategoryFormService, ServicesubcategoryFormGroup } from './servicesubcategory-form.service';

@Component({
  standalone: true,
  selector: 'jhi-servicesubcategory-update',
  templateUrl: './servicesubcategory-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ServicesubcategoryUpdateComponent implements OnInit {
  isSaving = false;
  servicesubcategory: IServicesubcategory | null = null;

  protected servicesubcategoryService = inject(ServicesubcategoryService);
  protected servicesubcategoryFormService = inject(ServicesubcategoryFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ServicesubcategoryFormGroup = this.servicesubcategoryFormService.createServicesubcategoryFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ servicesubcategory }) => {
      this.servicesubcategory = servicesubcategory;
      if (servicesubcategory) {
        this.updateForm(servicesubcategory);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const servicesubcategory = this.servicesubcategoryFormService.getServicesubcategory(this.editForm);
    if (servicesubcategory.id !== null) {
      this.subscribeToSaveResponse(this.servicesubcategoryService.update(servicesubcategory));
    } else {
      this.subscribeToSaveResponse(this.servicesubcategoryService.create(servicesubcategory));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServicesubcategory>>): void {
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

  protected updateForm(servicesubcategory: IServicesubcategory): void {
    this.servicesubcategory = servicesubcategory;
    this.servicesubcategoryFormService.resetForm(this.editForm, servicesubcategory);
  }
}
