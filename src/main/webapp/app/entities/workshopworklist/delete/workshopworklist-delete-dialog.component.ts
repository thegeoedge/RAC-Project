import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IWorkshopworklist } from '../workshopworklist.model';
import { WorkshopworklistService } from '../service/workshopworklist.service';

@Component({
  standalone: true,
  templateUrl: './workshopworklist-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class WorkshopworklistDeleteDialogComponent {
  workshopworklist?: IWorkshopworklist;

  protected workshopworklistService = inject(WorkshopworklistService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.workshopworklistService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
