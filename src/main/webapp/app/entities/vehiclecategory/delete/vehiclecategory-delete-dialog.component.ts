import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IVehiclecategory } from '../vehiclecategory.model';
import { VehiclecategoryService } from '../service/vehiclecategory.service';

@Component({
  standalone: true,
  templateUrl: './vehiclecategory-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class VehiclecategoryDeleteDialogComponent {
  vehiclecategory?: IVehiclecategory;

  protected vehiclecategoryService = inject(VehiclecategoryService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vehiclecategoryService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
