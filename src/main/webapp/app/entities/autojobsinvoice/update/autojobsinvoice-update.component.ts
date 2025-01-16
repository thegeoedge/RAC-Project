import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutojobsinvoice } from '../autojobsinvoice.model';
import { AutojobsinvoiceService } from '../service/autojobsinvoice.service';
import { AutojobsinvoiceFormService, AutojobsinvoiceFormGroup } from './autojobsinvoice-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autojobsinvoice-update',
  templateUrl: './autojobsinvoice-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutojobsinvoiceUpdateComponent implements OnInit {
  isSaving = false;
  autojobsinvoice: IAutojobsinvoice | null = null;

  protected autojobsinvoiceService = inject(AutojobsinvoiceService);
  protected autojobsinvoiceFormService = inject(AutojobsinvoiceFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutojobsinvoiceFormGroup = this.autojobsinvoiceFormService.createAutojobsinvoiceFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autojobsinvoice }) => {
      this.autojobsinvoice = autojobsinvoice;
      if (autojobsinvoice) {
        this.updateForm(autojobsinvoice);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autojobsinvoice = this.autojobsinvoiceFormService.getAutojobsinvoice(this.editForm);
    if (autojobsinvoice.id !== null) {
      this.subscribeToSaveResponse(this.autojobsinvoiceService.update(autojobsinvoice));
    } else {
      this.subscribeToSaveResponse(this.autojobsinvoiceService.create(autojobsinvoice));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutojobsinvoice>>): void {
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

  protected updateForm(autojobsinvoice: IAutojobsinvoice): void {
    this.autojobsinvoice = autojobsinvoice;
    this.autojobsinvoiceFormService.resetForm(this.editForm, autojobsinvoice);
  }
}
