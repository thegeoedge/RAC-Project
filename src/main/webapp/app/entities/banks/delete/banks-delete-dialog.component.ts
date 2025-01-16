import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IBanks } from '../banks.model';
import { BanksService } from '../service/banks.service';

@Component({
  standalone: true,
  templateUrl: './banks-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class BanksDeleteDialogComponent {
  banks?: IBanks;

  protected banksService = inject(BanksService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.banksService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
