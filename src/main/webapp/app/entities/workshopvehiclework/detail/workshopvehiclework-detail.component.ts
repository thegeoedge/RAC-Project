import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IWorkshopvehiclework } from '../workshopvehiclework.model';

@Component({
  standalone: true,
  selector: 'jhi-workshopvehiclework-detail',
  templateUrl: './workshopvehiclework-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class WorkshopvehicleworkDetailComponent {
  workshopvehiclework = input<IWorkshopvehiclework | null>(null);

  previousState(): void {
    window.history.back();
  }
}
