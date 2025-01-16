import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocarejob } from '../autocarejob.model';

@Component({
  standalone: true,
  selector: 'jhi-autocarejob-detail',
  templateUrl: './autocarejob-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocarejobDetailComponent {
  autocarejob = input<IAutocarejob | null>(null);

  previousState(): void {
    window.history.back();
  }
}
