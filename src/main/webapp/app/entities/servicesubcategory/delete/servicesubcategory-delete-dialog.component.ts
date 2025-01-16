import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IServicesubcategory } from '../servicesubcategory.model';
import { ServicesubcategoryService } from '../service/servicesubcategory.service';

@Component({
  standalone: true,
  templateUrl: './servicesubcategory-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ServicesubcategoryDeleteDialogComponent {
  servicesubcategory?: IServicesubcategory;

  protected servicesubcategoryService = inject(ServicesubcategoryService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.servicesubcategoryService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
