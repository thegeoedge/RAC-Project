import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocarejobcategory } from '../autocarejobcategory.model';
import { AutocarejobcategoryService } from '../service/autocarejobcategory.service';
import { AutocarejobcategoryFormService, AutocarejobcategoryFormGroup } from './autocarejobcategory-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocarejobcategory-update',
  templateUrl: './autocarejobcategory-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocarejobcategoryUpdateComponent implements OnInit {
  isSaving = false;
  autocarejobcategory: IAutocarejobcategory | null = null;

  protected autocarejobcategoryService = inject(AutocarejobcategoryService);
  protected autocarejobcategoryFormService = inject(AutocarejobcategoryFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocarejobcategoryFormGroup = this.autocarejobcategoryFormService.createAutocarejobcategoryFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocarejobcategory }) => {
      this.autocarejobcategory = autocarejobcategory;
      if (autocarejobcategory) {
        this.updateForm(autocarejobcategory);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocarejobcategory = this.autocarejobcategoryFormService.getAutocarejobcategory(this.editForm);
    if (autocarejobcategory.id !== null) {
      this.subscribeToSaveResponse(this.autocarejobcategoryService.update(autocarejobcategory));
    } else {
      this.subscribeToSaveResponse(this.autocarejobcategoryService.create(autocarejobcategory));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocarejobcategory>>): void {
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

  protected updateForm(autocarejobcategory: IAutocarejobcategory): void {
    this.autocarejobcategory = autocarejobcategory;
    this.autocarejobcategoryFormService.resetForm(this.editForm, autocarejobcategory);
  }
}
