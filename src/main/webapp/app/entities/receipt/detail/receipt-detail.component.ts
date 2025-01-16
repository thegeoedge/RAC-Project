import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IReceipt } from '../receipt.model';

@Component({
  standalone: true,
  selector: 'jhi-receipt-detail',
  templateUrl: './receipt-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ReceiptDetailComponent {
  receipt = input<IReceipt | null>(null);

  previousState(): void {
    window.history.back();
  }
}
