import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICommonserviceoption } from '../commonserviceoption.model';
import { CommonserviceoptionService } from '../service/commonserviceoption.service';
import { CommonserviceoptionFormService, CommonserviceoptionFormGroup } from './commonserviceoption-form.service';

@Component({
  standalone: true,
  selector: 'jhi-commonserviceoption-update',
  templateUrl: './commonserviceoption-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CommonserviceoptionUpdateComponent implements OnInit {
  isSaving = false;
  commonserviceoption: ICommonserviceoption | null = null;

  protected commonserviceoptionService = inject(CommonserviceoptionService);
  protected commonserviceoptionFormService = inject(CommonserviceoptionFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CommonserviceoptionFormGroup = this.commonserviceoptionFormService.createCommonserviceoptionFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commonserviceoption }) => {
      this.commonserviceoption = commonserviceoption;
      if (commonserviceoption) {
        this.updateForm(commonserviceoption);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commonserviceoption = this.commonserviceoptionFormService.getCommonserviceoption(this.editForm);
    if (commonserviceoption.id !== null) {
      this.subscribeToSaveResponse(this.commonserviceoptionService.update(commonserviceoption));
    } else {
      this.subscribeToSaveResponse(this.commonserviceoptionService.create(commonserviceoption));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommonserviceoption>>): void {
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

  protected updateForm(commonserviceoption: ICommonserviceoption): void {
    this.commonserviceoption = commonserviceoption;
    this.commonserviceoptionFormService.resetForm(this.editForm, commonserviceoption);
  }
}
