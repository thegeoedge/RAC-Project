import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ITaxes } from '../taxes.model';

@Component({
  standalone: true,
  selector: 'jhi-taxes-detail',
  templateUrl: './taxes-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class TaxesDetailComponent {
  taxes = input<ITaxes | null>(null);

  previousState(): void {
    window.history.back();
  }
}
