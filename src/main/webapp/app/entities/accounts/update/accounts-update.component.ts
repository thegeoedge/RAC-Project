import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAccounts } from '../accounts.model';
import { AccountsService } from '../service/accounts.service';
import { AccountsFormService, AccountsFormGroup } from './accounts-form.service';

@Component({
  standalone: true,
  selector: 'jhi-accounts-update',
  templateUrl: './accounts-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AccountsUpdateComponent implements OnInit {
  isSaving = false;
  accounts: IAccounts | null = null;

  protected accountsService = inject(AccountsService);
  protected accountsFormService = inject(AccountsFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AccountsFormGroup = this.accountsFormService.createAccountsFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ accounts }) => {
      this.accounts = accounts;
      if (accounts) {
        this.updateForm(accounts);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const accounts = this.accountsFormService.getAccounts(this.editForm);
    if (accounts.id !== null) {
      this.subscribeToSaveResponse(this.accountsService.update(accounts));
    } else {
      this.subscribeToSaveResponse(this.accountsService.create(accounts));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAccounts>>): void {
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

  protected updateForm(accounts: IAccounts): void {
    this.accounts = accounts;
    this.accountsFormService.resetForm(this.editForm, accounts);
  }
}
