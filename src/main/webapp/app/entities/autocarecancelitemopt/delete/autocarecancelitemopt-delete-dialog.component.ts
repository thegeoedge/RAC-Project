import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocarecancelitemopt } from '../autocarecancelitemopt.model';
import { AutocarecancelitemoptService } from '../service/autocarecancelitemopt.service';

@Component({
  standalone: true,
  templateUrl: './autocarecancelitemopt-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocarecancelitemoptDeleteDialogComponent {
  autocarecancelitemopt?: IAutocarecancelitemopt;

  protected autocarecancelitemoptService = inject(AutocarecancelitemoptService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocarecancelitemoptService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
