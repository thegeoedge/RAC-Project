import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocareservicemillagesDetailComponent } from './autocareservicemillages-detail.component';

describe('Autocareservicemillages Management Detail Component', () => {
  let comp: AutocareservicemillagesDetailComponent;
  let fixture: ComponentFixture<AutocareservicemillagesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocareservicemillagesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocareservicemillagesDetailComponent,
              resolve: { autocareservicemillages: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocareservicemillagesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocareservicemillagesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocareservicemillages on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocareservicemillagesDetailComponent);

      // THEN
      expect(instance.autocareservicemillages()).toEqual(expect.objectContaining({ id: 123 }));
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
