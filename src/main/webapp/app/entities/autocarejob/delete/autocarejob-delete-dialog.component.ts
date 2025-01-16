import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocarejob } from '../autocarejob.model';
import { AutocarejobService } from '../service/autocarejob.service';

@Component({
  standalone: true,
  templateUrl: './autocarejob-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocarejobDeleteDialogComponent {
  autocarejob?: IAutocarejob;

  protected autocarejobService = inject(AutocarejobService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocarejobService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
