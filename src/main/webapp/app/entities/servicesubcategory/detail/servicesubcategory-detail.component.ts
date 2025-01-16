import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IServicesubcategory } from '../servicesubcategory.model';

@Component({
  standalone: true,
  selector: 'jhi-servicesubcategory-detail',
  templateUrl: './servicesubcategory-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ServicesubcategoryDetailComponent {
  servicesubcategory = input<IServicesubcategory | null>(null);

  previousState(): void {
    window.history.back();
  }
}
