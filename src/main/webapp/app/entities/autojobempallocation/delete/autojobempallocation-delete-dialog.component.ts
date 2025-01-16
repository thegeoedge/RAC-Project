import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutojobempallocation } from '../autojobempallocation.model';
import { AutojobempallocationService } from '../service/autojobempallocation.service';

@Component({
  standalone: true,
  templateUrl: './autojobempallocation-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutojobempallocationDeleteDialogComponent {
  autojobempallocation?: IAutojobempallocation;

  protected autojobempallocationService = inject(AutojobempallocationService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autojobempallocationService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
