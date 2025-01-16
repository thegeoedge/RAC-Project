import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';

@Component({
  standalone: true,
  selector: 'jhi-billingserviceoptionvalues-detail',
  templateUrl: './billingserviceoptionvalues-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class BillingserviceoptionvaluesDetailComponent {
  billingserviceoptionvalues = input<IBillingserviceoptionvalues | null>(null);

  previousState(): void {
    window.history.back();
  }
}
