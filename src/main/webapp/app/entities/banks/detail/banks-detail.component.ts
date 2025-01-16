import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IBanks } from '../banks.model';

@Component({
  standalone: true,
  selector: 'jhi-banks-detail',
  templateUrl: './banks-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class BanksDetailComponent {
  banks = input<IBanks | null>(null);

  previousState(): void {
    window.history.back();
  }
}
