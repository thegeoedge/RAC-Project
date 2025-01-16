import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IServicecategory } from '../servicecategory.model';
import { ServicecategoryService } from '../service/servicecategory.service';

@Component({
  standalone: true,
  templateUrl: './servicecategory-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ServicecategoryDeleteDialogComponent {
  servicecategory?: IServicecategory;

  protected servicecategoryService = inject(ServicecategoryService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.servicecategoryService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
