import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IEmpJobcommission } from '../emp-jobcommission.model';
import { EmpJobcommissionService } from '../service/emp-jobcommission.service';

@Component({
  standalone: true,
  templateUrl: './emp-jobcommission-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class EmpJobcommissionDeleteDialogComponent {
  empJobcommission?: IEmpJobcommission;

  protected empJobcommissionService = inject(EmpJobcommissionService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.empJobcommissionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
