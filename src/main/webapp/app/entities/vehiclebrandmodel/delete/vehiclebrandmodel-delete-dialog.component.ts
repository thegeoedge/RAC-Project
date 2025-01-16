import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IVehiclebrandmodel } from '../vehiclebrandmodel.model';
import { VehiclebrandmodelService } from '../service/vehiclebrandmodel.service';

@Component({
  standalone: true,
  templateUrl: './vehiclebrandmodel-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class VehiclebrandmodelDeleteDialogComponent {
  vehiclebrandmodel?: IVehiclebrandmodel;

  protected vehiclebrandmodelService = inject(VehiclebrandmodelService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vehiclebrandmodelService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
