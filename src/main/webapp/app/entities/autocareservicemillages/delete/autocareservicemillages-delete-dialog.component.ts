import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocareservicemillages } from '../autocareservicemillages.model';
import { AutocareservicemillagesService } from '../service/autocareservicemillages.service';

@Component({
  standalone: true,
  templateUrl: './autocareservicemillages-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocareservicemillagesDeleteDialogComponent {
  autocareservicemillages?: IAutocareservicemillages;

  protected autocareservicemillagesService = inject(AutocareservicemillagesService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocareservicemillagesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
