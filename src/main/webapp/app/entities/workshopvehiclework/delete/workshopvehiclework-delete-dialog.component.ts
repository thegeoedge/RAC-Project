import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IWorkshopvehiclework } from '../workshopvehiclework.model';
import { WorkshopvehicleworkService } from '../service/workshopvehiclework.service';

@Component({
  standalone: true,
  templateUrl: './workshopvehiclework-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class WorkshopvehicleworkDeleteDialogComponent {
  workshopvehiclework?: IWorkshopvehiclework;

  protected workshopvehicleworkService = inject(WorkshopvehicleworkService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.workshopvehicleworkService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
