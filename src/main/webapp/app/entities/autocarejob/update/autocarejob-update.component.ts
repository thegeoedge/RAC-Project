import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocarejob } from '../autocarejob.model';
import { AutocarejobService } from '../service/autocarejob.service';
import { AutocarejobFormService, AutocarejobFormGroup } from './autocarejob-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocarejob-update',
  templateUrl: './autocarejob-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocarejobUpdateComponent implements OnInit {
  isSaving = false;
  autocarejob: IAutocarejob | null = null;

  protected autocarejobService = inject(AutocarejobService);
  protected autocarejobFormService = inject(AutocarejobFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocarejobFormGroup = this.autocarejobFormService.createAutocarejobFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocarejob }) => {
      this.autocarejob = autocarejob;
      if (autocarejob) {
        this.updateForm(autocarejob);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocarejob = this.autocarejobFormService.getAutocarejob(this.editForm);
    if (autocarejob.id !== null) {
      this.subscribeToSaveResponse(this.autocarejobService.update(autocarejob));
    } else {
      this.subscribeToSaveResponse(this.autocarejobService.create(autocarejob));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocarejob>>): void {
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

  protected updateForm(autocarejob: IAutocarejob): void {
    this.autocarejob = autocarejob;
    this.autocarejobFormService.resetForm(this.editForm, autocarejob);
  }
}
