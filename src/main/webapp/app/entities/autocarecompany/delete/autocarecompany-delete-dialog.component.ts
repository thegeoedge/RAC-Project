import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocarecompany } from '../autocarecompany.model';
import { AutocarecompanyService } from '../service/autocarecompany.service';

@Component({
  standalone: true,
  templateUrl: './autocarecompany-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocarecompanyDeleteDialogComponent {
  autocarecompany?: IAutocarecompany;

  protected autocarecompanyService = inject(AutocarecompanyService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocarecompanyService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
