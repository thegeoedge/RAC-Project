import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocarejobinimages } from '../autocarejobinimages.model';
import { AutocarejobinimagesService } from '../service/autocarejobinimages.service';

@Component({
  standalone: true,
  templateUrl: './autocarejobinimages-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocarejobinimagesDeleteDialogComponent {
  autocarejobinimages?: IAutocarejobinimages;

  protected autocarejobinimagesService = inject(AutocarejobinimagesService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocarejobinimagesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
