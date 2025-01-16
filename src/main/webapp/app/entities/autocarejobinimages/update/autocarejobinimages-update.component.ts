import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocarejobinimages } from '../autocarejobinimages.model';
import { AutocarejobinimagesService } from '../service/autocarejobinimages.service';
import { AutocarejobinimagesFormService, AutocarejobinimagesFormGroup } from './autocarejobinimages-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocarejobinimages-update',
  templateUrl: './autocarejobinimages-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocarejobinimagesUpdateComponent implements OnInit {
  isSaving = false;
  autocarejobinimages: IAutocarejobinimages | null = null;

  protected autocarejobinimagesService = inject(AutocarejobinimagesService);
  protected autocarejobinimagesFormService = inject(AutocarejobinimagesFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocarejobinimagesFormGroup = this.autocarejobinimagesFormService.createAutocarejobinimagesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocarejobinimages }) => {
      this.autocarejobinimages = autocarejobinimages;
      if (autocarejobinimages) {
        this.updateForm(autocarejobinimages);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocarejobinimages = this.autocarejobinimagesFormService.getAutocarejobinimages(this.editForm);
    if (autocarejobinimages.id !== null) {
      this.subscribeToSaveResponse(this.autocarejobinimagesService.update(autocarejobinimages));
    } else {
      this.subscribeToSaveResponse(this.autocarejobinimagesService.create(autocarejobinimages));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocarejobinimages>>): void {
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

  protected updateForm(autocarejobinimages: IAutocarejobinimages): void {
    this.autocarejobinimages = autocarejobinimages;
    this.autocarejobinimagesFormService.resetForm(this.editForm, autocarejobinimages);
  }
}
