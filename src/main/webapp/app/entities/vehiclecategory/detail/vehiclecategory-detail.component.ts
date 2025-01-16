import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IVehiclecategory } from '../vehiclecategory.model';

@Component({
  standalone: true,
  selector: 'jhi-vehiclecategory-detail',
  templateUrl: './vehiclecategory-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class VehiclecategoryDetailComponent {
  vehiclecategory = input<IVehiclecategory | null>(null);

  previousState(): void {
    window.history.back();
  }
}
