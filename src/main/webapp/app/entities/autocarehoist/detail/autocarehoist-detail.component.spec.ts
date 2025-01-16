import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocarehoistDetailComponent } from './autocarehoist-detail.component';

describe('Autocarehoist Management Detail Component', () => {
  let comp: AutocarehoistDetailComponent;
  let fixture: ComponentFixture<AutocarehoistDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocarehoistDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocarehoistDetailComponent,
              resolve: { autocarehoist: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocarehoistDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocarehoistDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocarehoist on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocarehoistDetailComponent);

      // THEN
      expect(instance.autocarehoist()).toEqual(expect.objectContaining({ id: 123 }));
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
