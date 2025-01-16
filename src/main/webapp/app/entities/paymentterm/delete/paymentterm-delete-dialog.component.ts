import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IPaymentterm } from '../paymentterm.model';
import { PaymenttermService } from '../service/paymentterm.service';

@Component({
  standalone: true,
  templateUrl: './paymentterm-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class PaymenttermDeleteDialogComponent {
  paymentterm?: IPaymentterm;

  protected paymenttermService = inject(PaymenttermService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paymenttermService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
