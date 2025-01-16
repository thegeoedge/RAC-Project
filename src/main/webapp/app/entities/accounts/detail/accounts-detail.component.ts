import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAccounts } from '../accounts.model';

@Component({
  standalone: true,
  selector: 'jhi-accounts-detail',
  templateUrl: './accounts-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AccountsDetailComponent {
  accounts = input<IAccounts | null>(null);

  previousState(): void {
    window.history.back();
  }
}
