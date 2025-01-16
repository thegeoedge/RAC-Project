import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocareappointment } from '../autocareappointment.model';
import { AutocareappointmentService } from '../service/autocareappointment.service';

@Component({
  standalone: true,
  templateUrl: './autocareappointment-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocareappointmentDeleteDialogComponent {
  autocareappointment?: IAutocareappointment;

  protected autocareappointmentService = inject(AutocareappointmentService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocareappointmentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
