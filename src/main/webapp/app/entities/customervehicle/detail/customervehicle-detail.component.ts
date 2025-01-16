import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICustomervehicle } from '../customervehicle.model';

@Component({
  standalone: true,
  selector: 'jhi-customervehicle-detail',
  templateUrl: './customervehicle-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CustomervehicleDetailComponent {
  customervehicle = input<ICustomervehicle | null>(null);

  previousState(): void {
    window.history.back();
  }
}
