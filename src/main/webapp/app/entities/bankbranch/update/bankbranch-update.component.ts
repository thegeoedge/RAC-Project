import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IBankbranch } from '../bankbranch.model';
import { BankbranchService } from '../service/bankbranch.service';
import { BankbranchFormService, BankbranchFormGroup } from './bankbranch-form.service';

@Component({
  standalone: true,
  selector: 'jhi-bankbranch-update',
  templateUrl: './bankbranch-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BankbranchUpdateComponent implements OnInit {
  isSaving = false;
  bankbranch: IBankbranch | null = null;

  protected bankbranchService = inject(BankbranchService);
  protected bankbranchFormService = inject(BankbranchFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: BankbranchFormGroup = this.bankbranchFormService.createBankbranchFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bankbranch }) => {
      this.bankbranch = bankbranch;
      if (bankbranch) {
        this.updateForm(bankbranch);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bankbranch = this.bankbranchFormService.getBankbranch(this.editForm);
    if (bankbranch.id !== null) {
      this.subscribeToSaveResponse(this.bankbranchService.update(bankbranch));
    } else {
      this.subscribeToSaveResponse(this.bankbranchService.create(bankbranch));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBankbranch>>): void {
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

  protected updateForm(bankbranch: IBankbranch): void {
    this.bankbranch = bankbranch;
    this.bankbranchFormService.resetForm(this.editForm, bankbranch);
  }
}
