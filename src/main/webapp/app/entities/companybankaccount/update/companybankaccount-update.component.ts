import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICompanybankaccount } from '../companybankaccount.model';
import { CompanybankaccountService } from '../service/companybankaccount.service';
import { CompanybankaccountFormService, CompanybankaccountFormGroup } from './companybankaccount-form.service';

@Component({
  standalone: true,
  selector: 'jhi-companybankaccount-update',
  templateUrl: './companybankaccount-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CompanybankaccountUpdateComponent implements OnInit {
  isSaving = false;
  companybankaccount: ICompanybankaccount | null = null;

  protected companybankaccountService = inject(CompanybankaccountService);
  protected companybankaccountFormService = inject(CompanybankaccountFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CompanybankaccountFormGroup = this.companybankaccountFormService.createCompanybankaccountFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ companybankaccount }) => {
      this.companybankaccount = companybankaccount;
      if (companybankaccount) {
        this.updateForm(companybankaccount);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const companybankaccount = this.companybankaccountFormService.getCompanybankaccount(this.editForm);
    if (companybankaccount.id !== null) {
      this.subscribeToSaveResponse(this.companybankaccountService.update(companybankaccount));
    } else {
      this.subscribeToSaveResponse(this.companybankaccountService.create(companybankaccount));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompanybankaccount>>): void {
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

  protected updateForm(companybankaccount: ICompanybankaccount): void {
    this.companybankaccount = companybankaccount;
    this.companybankaccountFormService.resetForm(this.editForm, companybankaccount);
  }
}
