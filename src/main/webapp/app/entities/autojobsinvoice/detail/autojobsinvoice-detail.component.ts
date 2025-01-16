import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutojobsinvoice } from '../autojobsinvoice.model';

@Component({
  standalone: true,
  selector: 'jhi-autojobsinvoice-detail',
  templateUrl: './autojobsinvoice-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutojobsinvoiceDetailComponent {
  autojobsinvoice = input<IAutojobsinvoice | null>(null);

  previousState(): void {
    window.history.back();
  }
}
