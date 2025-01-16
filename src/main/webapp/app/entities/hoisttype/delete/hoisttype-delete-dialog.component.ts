import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IHoisttype } from '../hoisttype.model';
import { HoisttypeService } from '../service/hoisttype.service';

@Component({
  standalone: true,
  templateUrl: './hoisttype-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class HoisttypeDeleteDialogComponent {
  hoisttype?: IHoisttype;

  protected hoisttypeService = inject(HoisttypeService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hoisttypeService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
