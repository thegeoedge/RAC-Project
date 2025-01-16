import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IVehiclebrandname } from '../vehiclebrandname.model';
import { VehiclebrandnameService } from '../service/vehiclebrandname.service';

@Component({
  standalone: true,
  templateUrl: './vehiclebrandname-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class VehiclebrandnameDeleteDialogComponent {
  vehiclebrandname?: IVehiclebrandname;

  protected vehiclebrandnameService = inject(VehiclebrandnameService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vehiclebrandnameService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
