import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { INextserviceinstructions } from '../nextserviceinstructions.model';

@Component({
  standalone: true,
  selector: 'jhi-nextserviceinstructions-detail',
  templateUrl: './nextserviceinstructions-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class NextserviceinstructionsDetailComponent {
  nextserviceinstructions = input<INextserviceinstructions | null>(null);

  previousState(): void {
    window.history.back();
  }
}
