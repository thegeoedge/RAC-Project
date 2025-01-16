import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ISalesinvoice } from '../salesinvoice.model';
import { SalesinvoiceService } from '../service/salesinvoice.service';

@Component({
  standalone: true,
  templateUrl: './salesinvoice-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class SalesinvoiceDeleteDialogComponent {
  salesinvoice?: ISalesinvoice;

  protected salesinvoiceService = inject(SalesinvoiceService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.salesinvoiceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
