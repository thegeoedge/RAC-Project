import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutojobempallocation } from '../autojobempallocation.model';

@Component({
  standalone: true,
  selector: 'jhi-autojobempallocation-detail',
  templateUrl: './autojobempallocation-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutojobempallocationDetailComponent {
  autojobempallocation = input<IAutojobempallocation | null>(null);

  previousState(): void {
    window.history.back();
  }
}
