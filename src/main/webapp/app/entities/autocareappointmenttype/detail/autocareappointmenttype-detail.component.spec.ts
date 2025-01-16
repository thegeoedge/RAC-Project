import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocareappointmenttypeDetailComponent } from './autocareappointmenttype-detail.component';

describe('Autocareappointmenttype Management Detail Component', () => {
  let comp: AutocareappointmenttypeDetailComponent;
  let fixture: ComponentFixture<AutocareappointmenttypeDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocareappointmenttypeDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocareappointmenttypeDetailComponent,
              resolve: { autocareappointmenttype: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocareappointmenttypeDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocareappointmenttypeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocareappointmenttype on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocareappointmenttypeDetailComponent);

      // THEN
      expect(instance.autocareappointmenttype()).toEqual(expect.objectContaining({ id: 123 }));
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
