import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutojobsinvoice } from '../autojobsinvoice.model';
import { AutojobsinvoiceService } from '../service/autojobsinvoice.service';

@Component({
  standalone: true,
  templateUrl: './autojobsinvoice-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutojobsinvoiceDeleteDialogComponent {
  autojobsinvoice?: IAutojobsinvoice;

  protected autojobsinvoiceService = inject(AutojobsinvoiceService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autojobsinvoiceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
