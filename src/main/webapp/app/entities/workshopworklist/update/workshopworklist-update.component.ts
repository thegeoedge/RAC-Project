import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IWorkshopworklist } from '../workshopworklist.model';
import { WorkshopworklistService } from '../service/workshopworklist.service';
import { WorkshopworklistFormService, WorkshopworklistFormGroup } from './workshopworklist-form.service';

@Component({
  standalone: true,
  selector: 'jhi-workshopworklist-update',
  templateUrl: './workshopworklist-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class WorkshopworklistUpdateComponent implements OnInit {
  isSaving = false;
  workshopworklist: IWorkshopworklist | null = null;

  protected workshopworklistService = inject(WorkshopworklistService);
  protected workshopworklistFormService = inject(WorkshopworklistFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: WorkshopworklistFormGroup = this.workshopworklistFormService.createWorkshopworklistFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ workshopworklist }) => {
      this.workshopworklist = workshopworklist;
      if (workshopworklist) {
        this.updateForm(workshopworklist);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const workshopworklist = this.workshopworklistFormService.getWorkshopworklist(this.editForm);
    if (workshopworklist.id !== null) {
      this.subscribeToSaveResponse(this.workshopworklistService.update(workshopworklist));
    } else {
      this.subscribeToSaveResponse(this.workshopworklistService.create(workshopworklist));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkshopworklist>>): void {
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

  protected updateForm(workshopworklist: IWorkshopworklist): void {
    this.workshopworklist = workshopworklist;
    this.workshopworklistFormService.resetForm(this.editForm, workshopworklist);
  }
}
