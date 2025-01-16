import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IBillingserviceoption } from '../billingserviceoption.model';

@Component({
  standalone: true,
  selector: 'jhi-billingserviceoption-detail',
  templateUrl: './billingserviceoption-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class BillingserviceoptionDetailComponent {
  billingserviceoption = input<IBillingserviceoption | null>(null);

  previousState(): void {
    window.history.back();
  }
}
