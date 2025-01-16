import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ISalesinvoice } from '../salesinvoice.model';

@Component({
  standalone: true,
  selector: 'jhi-salesinvoice-detail',
  templateUrl: './salesinvoice-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class SalesinvoiceDetailComponent {
  salesinvoice = input<ISalesinvoice | null>(null);

  previousState(): void {
    window.history.back();
  }
}
