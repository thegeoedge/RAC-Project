import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IVehicletype } from '../vehicletype.model';

@Component({
  standalone: true,
  selector: 'jhi-vehicletype-detail',
  templateUrl: './vehicletype-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class VehicletypeDetailComponent {
  vehicletype = input<IVehicletype | null>(null);

  previousState(): void {
    window.history.back();
  }
}
