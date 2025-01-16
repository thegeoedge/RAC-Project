import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IServicecategory } from '../servicecategory.model';
import { ServicecategoryService } from '../service/servicecategory.service';
import { ServicecategoryFormService, ServicecategoryFormGroup } from './servicecategory-form.service';

@Component({
  standalone: true,
  selector: 'jhi-servicecategory-update',
  templateUrl: './servicecategory-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ServicecategoryUpdateComponent implements OnInit {
  isSaving = false;
  servicecategory: IServicecategory | null = null;

  protected servicecategoryService = inject(ServicecategoryService);
  protected servicecategoryFormService = inject(ServicecategoryFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ServicecategoryFormGroup = this.servicecategoryFormService.createServicecategoryFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ servicecategory }) => {
      this.servicecategory = servicecategory;
      if (servicecategory) {
        this.updateForm(servicecategory);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const servicecategory = this.servicecategoryFormService.getServicecategory(this.editForm);
    if (servicecategory.id !== null) {
      this.subscribeToSaveResponse(this.servicecategoryService.update(servicecategory));
    } else {
      this.subscribeToSaveResponse(this.servicecategoryService.create(servicecategory));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServicecategory>>): void {
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

  protected updateForm(servicecategory: IServicecategory): void {
    this.servicecategory = servicecategory;
    this.servicecategoryFormService.resetForm(this.editForm, servicecategory);
  }
}
