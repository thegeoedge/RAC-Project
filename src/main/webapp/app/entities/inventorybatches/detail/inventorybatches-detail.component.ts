import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IInventorybatches } from '../inventorybatches.model';

@Component({
  standalone: true,
  selector: 'jhi-inventorybatches-detail',
  templateUrl: './inventorybatches-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class InventorybatchesDetailComponent {
  inventorybatches = input<IInventorybatches | null>(null);

  previousState(): void {
    window.history.back();
  }
}
