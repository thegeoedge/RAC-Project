import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocarecompanyDetailComponent } from './autocarecompany-detail.component';

describe('Autocarecompany Management Detail Component', () => {
  let comp: AutocarecompanyDetailComponent;
  let fixture: ComponentFixture<AutocarecompanyDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocarecompanyDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocarecompanyDetailComponent,
              resolve: { autocarecompany: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocarecompanyDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocarecompanyDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocarecompany on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocarecompanyDetailComponent);

      // THEN
      expect(instance.autocarecompany()).toEqual(expect.objectContaining({ id: 123 }));
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
