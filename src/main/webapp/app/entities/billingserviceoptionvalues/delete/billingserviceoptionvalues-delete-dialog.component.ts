import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';
import { BillingserviceoptionvaluesService } from '../service/billingserviceoptionvalues.service';

@Component({
  standalone: true,
  templateUrl: './billingserviceoptionvalues-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class BillingserviceoptionvaluesDeleteDialogComponent {
  billingserviceoptionvalues?: IBillingserviceoptionvalues;

  protected billingserviceoptionvaluesService = inject(BillingserviceoptionvaluesService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.billingserviceoptionvaluesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
