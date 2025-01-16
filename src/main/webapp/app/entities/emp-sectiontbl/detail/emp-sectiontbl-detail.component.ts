import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IEmpSectiontbl } from '../emp-sectiontbl.model';

@Component({
  standalone: true,
  selector: 'jhi-emp-sectiontbl-detail',
  templateUrl: './emp-sectiontbl-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class EmpSectiontblDetailComponent {
  empSectiontbl = input<IEmpSectiontbl | null>(null);

  previousState(): void {
    window.history.back();
  }
}
