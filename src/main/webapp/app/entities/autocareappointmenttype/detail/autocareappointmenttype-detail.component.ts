import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocareappointmenttype } from '../autocareappointmenttype.model';

@Component({
  standalone: true,
  selector: 'jhi-autocareappointmenttype-detail',
  templateUrl: './autocareappointmenttype-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocareappointmenttypeDetailComponent {
  autocareappointmenttype = input<IAutocareappointmenttype | null>(null);

  previousState(): void {
    window.history.back();
  }
}
