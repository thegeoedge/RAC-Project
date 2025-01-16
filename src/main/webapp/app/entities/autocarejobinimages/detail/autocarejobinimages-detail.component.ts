import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAutocarejobinimages } from '../autocarejobinimages.model';

@Component({
  standalone: true,
  selector: 'jhi-autocarejobinimages-detail',
  templateUrl: './autocarejobinimages-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AutocarejobinimagesDetailComponent {
  autocarejobinimages = input<IAutocarejobinimages | null>(null);

  previousState(): void {
    window.history.back();
  }
}
