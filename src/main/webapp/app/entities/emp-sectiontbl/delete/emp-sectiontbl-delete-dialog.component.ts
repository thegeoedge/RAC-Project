import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IEmpSectiontbl } from '../emp-sectiontbl.model';
import { EmpSectiontblService } from '../service/emp-sectiontbl.service';

@Component({
  standalone: true,
  templateUrl: './emp-sectiontbl-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class EmpSectiontblDeleteDialogComponent {
  empSectiontbl?: IEmpSectiontbl;

  protected empSectiontblService = inject(EmpSectiontblService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.empSectiontblService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
