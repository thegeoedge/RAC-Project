import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocarecancelitemopt } from '../autocarecancelitemopt.model';
import { AutocarecancelitemoptService } from '../service/autocarecancelitemopt.service';
import { AutocarecancelitemoptFormService, AutocarecancelitemoptFormGroup } from './autocarecancelitemopt-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocarecancelitemopt-update',
  templateUrl: './autocarecancelitemopt-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocarecancelitemoptUpdateComponent implements OnInit {
  isSaving = false;
  autocarecancelitemopt: IAutocarecancelitemopt | null = null;

  protected autocarecancelitemoptService = inject(AutocarecancelitemoptService);
  protected autocarecancelitemoptFormService = inject(AutocarecancelitemoptFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocarecancelitemoptFormGroup = this.autocarecancelitemoptFormService.createAutocarecancelitemoptFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocarecancelitemopt }) => {
      this.autocarecancelitemopt = autocarecancelitemopt;
      if (autocarecancelitemopt) {
        this.updateForm(autocarecancelitemopt);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocarecancelitemopt = this.autocarecancelitemoptFormService.getAutocarecancelitemopt(this.editForm);
    if (autocarecancelitemopt.id !== null) {
      this.subscribeToSaveResponse(this.autocarecancelitemoptService.update(autocarecancelitemopt));
    } else {
      this.subscribeToSaveResponse(this.autocarecancelitemoptService.create(autocarecancelitemopt));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocarecancelitemopt>>): void {
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

  protected updateForm(autocarecancelitemopt: IAutocarecancelitemopt): void {
    this.autocarecancelitemopt = autocarecancelitemopt;
    this.autocarecancelitemoptFormService.resetForm(this.editForm, autocarecancelitemopt);
  }
}
