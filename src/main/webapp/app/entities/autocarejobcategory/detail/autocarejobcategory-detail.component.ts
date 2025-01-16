import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocarejobcategory } from '../autocarejobcategory.model';

@Component({
  standalone: true,
  selector: 'jhi-autocarejobcategory-detail',
  templateUrl: './autocarejobcategory-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocarejobcategoryDetailComponent {
  autocarejobcategory = input<IAutocarejobcategory | null>(null);

  previousState(): void {
    window.history.back();
  }
}
