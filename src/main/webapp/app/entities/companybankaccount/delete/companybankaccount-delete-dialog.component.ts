import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICompanybankaccount } from '../companybankaccount.model';
import { CompanybankaccountService } from '../service/companybankaccount.service';

@Component({
  standalone: true,
  templateUrl: './companybankaccount-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CompanybankaccountDeleteDialogComponent {
  companybankaccount?: ICompanybankaccount;

  protected companybankaccountService = inject(CompanybankaccountService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.companybankaccountService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
