import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocaretimetable } from '../autocaretimetable.model';
import { AutocaretimetableService } from '../service/autocaretimetable.service';
import { AutocaretimetableFormService, AutocaretimetableFormGroup } from './autocaretimetable-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocaretimetable-update',
  templateUrl: './autocaretimetable-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocaretimetableUpdateComponent implements OnInit {
  isSaving = false;
  autocaretimetable: IAutocaretimetable | null = null;

  protected autocaretimetableService = inject(AutocaretimetableService);
  protected autocaretimetableFormService = inject(AutocaretimetableFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocaretimetableFormGroup = this.autocaretimetableFormService.createAutocaretimetableFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocaretimetable }) => {
      this.autocaretimetable = autocaretimetable;
      if (autocaretimetable) {
        this.updateForm(autocaretimetable);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocaretimetable = this.autocaretimetableFormService.getAutocaretimetable(this.editForm);
    if (autocaretimetable.id !== null) {
      this.subscribeToSaveResponse(this.autocaretimetableService.update(autocaretimetable));
    } else {
      this.subscribeToSaveResponse(this.autocaretimetableService.create(autocaretimetable));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocaretimetable>>): void {
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

  protected updateForm(autocaretimetable: IAutocaretimetable): void {
    this.autocaretimetable = autocaretimetable;
    this.autocaretimetableFormService.resetForm(this.editForm, autocaretimetable);
  }
}
