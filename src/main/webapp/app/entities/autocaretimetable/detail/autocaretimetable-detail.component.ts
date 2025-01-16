import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocaretimetable } from '../autocaretimetable.model';

@Component({
  standalone: true,
  selector: 'jhi-autocaretimetable-detail',
  templateUrl: './autocaretimetable-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocaretimetableDetailComponent {
  autocaretimetable = input<IAutocaretimetable | null>(null);

  previousState(): void {
    window.history.back();
  }
}
