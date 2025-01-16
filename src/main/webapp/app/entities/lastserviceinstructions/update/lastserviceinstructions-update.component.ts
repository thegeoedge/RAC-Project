import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ILastserviceinstructions } from '../lastserviceinstructions.model';
import { LastserviceinstructionsService } from '../service/lastserviceinstructions.service';
import { LastserviceinstructionsFormService, LastserviceinstructionsFormGroup } from './lastserviceinstructions-form.service';

@Component({
  standalone: true,
  selector: 'jhi-lastserviceinstructions-update',
  templateUrl: './lastserviceinstructions-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class LastserviceinstructionsUpdateComponent implements OnInit {
  isSaving = false;
  lastserviceinstructions: ILastserviceinstructions | null = null;

  protected lastserviceinstructionsService = inject(LastserviceinstructionsService);
  protected lastserviceinstructionsFormService = inject(LastserviceinstructionsFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: LastserviceinstructionsFormGroup = this.lastserviceinstructionsFormService.createLastserviceinstructionsFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lastserviceinstructions }) => {
      this.lastserviceinstructions = lastserviceinstructions;
      if (lastserviceinstructions) {
        this.updateForm(lastserviceinstructions);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lastserviceinstructions = this.lastserviceinstructionsFormService.getLastserviceinstructions(this.editForm);
    if (lastserviceinstructions.id !== null) {
      this.subscribeToSaveResponse(this.lastserviceinstructionsService.update(lastserviceinstructions));
    } else {
      this.subscribeToSaveResponse(this.lastserviceinstructionsService.create(lastserviceinstructions));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILastserviceinstructions>>): void {
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

  protected updateForm(lastserviceinstructions: ILastserviceinstructions): void {
    this.lastserviceinstructions = lastserviceinstructions;
    this.lastserviceinstructionsFormService.resetForm(this.editForm, lastserviceinstructions);
  }
}
