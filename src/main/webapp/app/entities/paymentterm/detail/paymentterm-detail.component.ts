import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IPaymentterm } from '../paymentterm.model';

@Component({
  standalone: true,
  selector: 'jhi-paymentterm-detail',
  templateUrl: './paymentterm-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class PaymenttermDetailComponent {
  paymentterm = input<IPaymentterm | null>(null);

  previousState(): void {
    window.history.back();
  }
}
