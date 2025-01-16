import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IVehiclebrandname } from '../vehiclebrandname.model';

@Component({
  standalone: true,
  selector: 'jhi-vehiclebrandname-detail',
  templateUrl: './vehiclebrandname-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class VehiclebrandnameDetailComponent {
  vehiclebrandname = input<IVehiclebrandname | null>(null);

  previousState(): void {
    window.history.back();
  }
}
