import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IVehiclebrandmodel } from '../vehiclebrandmodel.model';

@Component({
  standalone: true,
  selector: 'jhi-vehiclebrandmodel-detail',
  templateUrl: './vehiclebrandmodel-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class VehiclebrandmodelDetailComponent {
  vehiclebrandmodel = input<IVehiclebrandmodel | null>(null);

  previousState(): void {
    window.history.back();
  }
}
