import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutojobempallocation } from '../autojobempallocation.model';
import { AutojobempallocationService } from '../service/autojobempallocation.service';
import { AutojobempallocationFormService, AutojobempallocationFormGroup } from './autojobempallocation-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autojobempallocation-update',
  templateUrl: './autojobempallocation-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutojobempallocationUpdateComponent implements OnInit {
  isSaving = false;
  autojobempallocation: IAutojobempallocation | null = null;

  protected autojobempallocationService = inject(AutojobempallocationService);
  protected autojobempallocationFormService = inject(AutojobempallocationFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutojobempallocationFormGroup = this.autojobempallocationFormService.createAutojobempallocationFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autojobempallocation }) => {
      this.autojobempallocation = autojobempallocation;
      if (autojobempallocation) {
        this.updateForm(autojobempallocation);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autojobempallocation = this.autojobempallocationFormService.getAutojobempallocation(this.editForm);
    if (autojobempallocation.id !== null) {
      this.subscribeToSaveResponse(this.autojobempallocationService.update(autojobempallocation));
    } else {
      this.subscribeToSaveResponse(this.autojobempallocationService.create(autojobempallocation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutojobempallocation>>): void {
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

  protected updateForm(autojobempallocation: IAutojobempallocation): void {
    this.autojobempallocation = autojobempallocation;
    this.autojobempallocationFormService.resetForm(this.editForm, autojobempallocation);
  }
}
