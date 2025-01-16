import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ILastserviceinstructions } from '../lastserviceinstructions.model';
import { LastserviceinstructionsService } from '../service/lastserviceinstructions.service';

@Component({
  standalone: true,
  templateUrl: './lastserviceinstructions-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class LastserviceinstructionsDeleteDialogComponent {
  lastserviceinstructions?: ILastserviceinstructions;

  protected lastserviceinstructionsService = inject(LastserviceinstructionsService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lastserviceinstructionsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
