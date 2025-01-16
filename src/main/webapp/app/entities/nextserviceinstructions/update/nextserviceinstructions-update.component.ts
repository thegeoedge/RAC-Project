import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { INextserviceinstructions } from '../nextserviceinstructions.model';
import { NextserviceinstructionsService } from '../service/nextserviceinstructions.service';
import { NextserviceinstructionsFormService, NextserviceinstructionsFormGroup } from './nextserviceinstructions-form.service';

@Component({
  standalone: true,
  selector: 'jhi-nextserviceinstructions-update',
  templateUrl: './nextserviceinstructions-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class NextserviceinstructionsUpdateComponent implements OnInit {
  isSaving = false;
  nextserviceinstructions: INextserviceinstructions | null = null;

  protected nextserviceinstructionsService = inject(NextserviceinstructionsService);
  protected nextserviceinstructionsFormService = inject(NextserviceinstructionsFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: NextserviceinstructionsFormGroup = this.nextserviceinstructionsFormService.createNextserviceinstructionsFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nextserviceinstructions }) => {
      this.nextserviceinstructions = nextserviceinstructions;
      if (nextserviceinstructions) {
        this.updateForm(nextserviceinstructions);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nextserviceinstructions = this.nextserviceinstructionsFormService.getNextserviceinstructions(this.editForm);
    if (nextserviceinstructions.id !== null) {
      this.subscribeToSaveResponse(this.nextserviceinstructionsService.update(nextserviceinstructions));
    } else {
      this.subscribeToSaveResponse(this.nextserviceinstructionsService.create(nextserviceinstructions));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INextserviceinstructions>>): void {
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

  protected updateForm(nextserviceinstructions: INextserviceinstructions): void {
    this.nextserviceinstructions = nextserviceinstructions;
    this.nextserviceinstructionsFormService.resetForm(this.editForm, nextserviceinstructions);
  }
}
