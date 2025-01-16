import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IStocklocations } from '../stocklocations.model';

@Component({
  standalone: true,
  selector: 'jhi-stocklocations-detail',
  templateUrl: './stocklocations-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class StocklocationsDetailComponent {
  stocklocations = input<IStocklocations | null>(null);

  previousState(): void {
    window.history.back();
  }
}
