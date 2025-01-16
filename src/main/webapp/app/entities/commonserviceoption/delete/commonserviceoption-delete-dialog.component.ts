import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICommonserviceoption } from '../commonserviceoption.model';
import { CommonserviceoptionService } from '../service/commonserviceoption.service';

@Component({
  standalone: true,
  templateUrl: './commonserviceoption-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CommonserviceoptionDeleteDialogComponent {
  commonserviceoption?: ICommonserviceoption;

  protected commonserviceoptionService = inject(CommonserviceoptionService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commonserviceoptionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
