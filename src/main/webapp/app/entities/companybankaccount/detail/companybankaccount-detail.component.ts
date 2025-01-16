import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICompanybankaccount } from '../companybankaccount.model';

@Component({
  standalone: true,
  selector: 'jhi-companybankaccount-detail',
  templateUrl: './companybankaccount-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CompanybankaccountDetailComponent {
  companybankaccount = input<ICompanybankaccount | null>(null);

  previousState(): void {
    window.history.back();
  }
}
