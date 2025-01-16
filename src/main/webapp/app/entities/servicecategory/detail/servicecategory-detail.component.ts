import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IServicecategory } from '../servicecategory.model';

@Component({
  standalone: true,
  selector: 'jhi-servicecategory-detail',
  templateUrl: './servicecategory-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ServicecategoryDetailComponent {
  servicecategory = input<IServicecategory | null>(null);

  previousState(): void {
    window.history.back();
  }
}
