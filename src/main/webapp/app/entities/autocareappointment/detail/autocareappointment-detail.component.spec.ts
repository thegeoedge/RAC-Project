import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocareappointmentDetailComponent } from './autocareappointment-detail.component';

describe('Autocareappointment Management Detail Component', () => {
  let comp: AutocareappointmentDetailComponent;
  let fixture: ComponentFixture<AutocareappointmentDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocareappointmentDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocareappointmentDetailComponent,
              resolve: { autocareappointment: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocareappointmentDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocareappointmentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocareappointment on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocareappointmentDetailComponent);

      // THEN
      expect(instance.autocareappointment()).toEqual(expect.objectContaining({ id: 123 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
