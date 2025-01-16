import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ISalesorder } from '../salesorder.model';

@Component({
  standalone: true,
  selector: 'jhi-salesorder-detail',
  templateUrl: './salesorder-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class SalesorderDetailComponent {
  salesorder = input<ISalesorder | null>(null);

  previousState(): void {
    window.history.back();
  }
}
