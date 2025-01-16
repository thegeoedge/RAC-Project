import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IHoisttype } from '../hoisttype.model';

@Component({
  standalone: true,
  selector: 'jhi-hoisttype-detail',
  templateUrl: './hoisttype-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class HoisttypeDetailComponent {
  hoisttype = input<IHoisttype | null>(null);

  previousState(): void {
    window.history.back();
  }
}
