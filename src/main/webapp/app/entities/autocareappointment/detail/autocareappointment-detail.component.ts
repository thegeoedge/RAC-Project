import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocareappointment } from '../autocareappointment.model';

@Component({
  standalone: true,
  selector: 'jhi-autocareappointment-detail',
  templateUrl: './autocareappointment-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocareappointmentDetailComponent {
  autocareappointment = input<IAutocareappointment | null>(null);

  previousState(): void {
    window.history.back();
  }
}
