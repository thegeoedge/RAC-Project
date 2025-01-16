import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICustomervehicle } from '../customervehicle.model';
import { CustomervehicleService } from '../service/customervehicle.service';

@Component({
  standalone: true,
  templateUrl: './customervehicle-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CustomervehicleDeleteDialogComponent {
  customervehicle?: ICustomervehicle;

  protected customervehicleService = inject(CustomervehicleService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.customervehicleService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
