import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IBankbranch } from '../bankbranch.model';

@Component({
  standalone: true,
  selector: 'jhi-bankbranch-detail',
  templateUrl: './bankbranch-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class BankbranchDetailComponent {
  bankbranch = input<IBankbranch | null>(null);

  previousState(): void {
    window.history.back();
  }
}
