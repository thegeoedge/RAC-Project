import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IStocklocations } from '../stocklocations.model';
import { StocklocationsService } from '../service/stocklocations.service';

@Component({
  standalone: true,
  templateUrl: './stocklocations-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class StocklocationsDeleteDialogComponent {
  stocklocations?: IStocklocations;

  protected stocklocationsService = inject(StocklocationsService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.stocklocationsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
