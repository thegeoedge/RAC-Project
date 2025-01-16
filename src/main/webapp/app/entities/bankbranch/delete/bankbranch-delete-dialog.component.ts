import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IBankbranch } from '../bankbranch.model';
import { BankbranchService } from '../service/bankbranch.service';

@Component({
  standalone: true,
  templateUrl: './bankbranch-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class BankbranchDeleteDialogComponent {
  bankbranch?: IBankbranch;

  protected bankbranchService = inject(BankbranchService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bankbranchService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
