import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IEmpJobcommission } from '../emp-jobcommission.model';

@Component({
  standalone: true,
  selector: 'jhi-emp-jobcommission-detail',
  templateUrl: './emp-jobcommission-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class EmpJobcommissionDetailComponent {
  empJobcommission = input<IEmpJobcommission | null>(null);

  previousState(): void {
    window.history.back();
  }
}
