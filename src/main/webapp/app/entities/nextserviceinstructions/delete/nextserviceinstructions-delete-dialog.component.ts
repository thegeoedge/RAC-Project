import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { INextserviceinstructions } from '../nextserviceinstructions.model';
import { NextserviceinstructionsService } from '../service/nextserviceinstructions.service';

@Component({
  standalone: true,
  templateUrl: './nextserviceinstructions-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class NextserviceinstructionsDeleteDialogComponent {
  nextserviceinstructions?: INextserviceinstructions;

  protected nextserviceinstructionsService = inject(NextserviceinstructionsService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nextserviceinstructionsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
