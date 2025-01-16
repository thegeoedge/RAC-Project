import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocareappointmenttype } from '../autocareappointmenttype.model';
import { AutocareappointmenttypeService } from '../service/autocareappointmenttype.service';

@Component({
  standalone: true,
  templateUrl: './autocareappointmenttype-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocareappointmenttypeDeleteDialogComponent {
  autocareappointmenttype?: IAutocareappointmenttype;

  protected autocareappointmenttypeService = inject(AutocareappointmenttypeService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocareappointmenttypeService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
