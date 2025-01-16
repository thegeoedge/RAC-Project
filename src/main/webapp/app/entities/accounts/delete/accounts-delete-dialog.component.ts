import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAccounts } from '../accounts.model';
import { AccountsService } from '../service/accounts.service';

@Component({
  standalone: true,
  templateUrl: './accounts-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AccountsDeleteDialogComponent {
  accounts?: IAccounts;

  protected accountsService = inject(AccountsService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.accountsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
