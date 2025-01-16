import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ILocationbasedstock } from '../locationbasedstock.model';
import { LocationbasedstockService } from '../service/locationbasedstock.service';

@Component({
  standalone: true,
  templateUrl: './locationbasedstock-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class LocationbasedstockDeleteDialogComponent {
  locationbasedstock?: ILocationbasedstock;

  protected locationbasedstockService = inject(LocationbasedstockService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.locationbasedstockService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
