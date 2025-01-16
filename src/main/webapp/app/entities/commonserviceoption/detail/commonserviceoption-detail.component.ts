import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICommonserviceoption } from '../commonserviceoption.model';

@Component({
  standalone: true,
  selector: 'jhi-commonserviceoption-detail',
  templateUrl: './commonserviceoption-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CommonserviceoptionDetailComponent {
  commonserviceoption = input<ICommonserviceoption | null>(null);

  previousState(): void {
    window.history.back();
  }
}
