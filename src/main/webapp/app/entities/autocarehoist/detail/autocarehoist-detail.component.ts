import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocarehoist } from '../autocarehoist.model';

@Component({
  standalone: true,
  selector: 'jhi-autocarehoist-detail',
  templateUrl: './autocarehoist-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocarehoistDetailComponent {
  autocarehoist = input<IAutocarehoist | null>(null);

  previousState(): void {
    window.history.back();
  }
}
