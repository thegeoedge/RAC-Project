import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocareservicemillages } from '../autocareservicemillages.model';

@Component({
  standalone: true,
  selector: 'jhi-autocareservicemillages-detail',
  templateUrl: './autocareservicemillages-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocareservicemillagesDetailComponent {
  autocareservicemillages = input<IAutocareservicemillages | null>(null);

  previousState(): void {
    window.history.back();
  }
}
