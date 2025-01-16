import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAutocarejobcategory } from '../autocarejobcategory.model';
import { AutocarejobcategoryService } from '../service/autocarejobcategory.service';

@Component({
  standalone: true,
  templateUrl: './autocarejobcategory-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AutocarejobcategoryDeleteDialogComponent {
  autocarejobcategory?: IAutocarejobcategory;

  protected autocarejobcategoryService = inject(AutocarejobcategoryService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autocarejobcategoryService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
