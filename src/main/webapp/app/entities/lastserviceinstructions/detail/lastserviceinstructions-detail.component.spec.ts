import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { LastserviceinstructionsDetailComponent } from './lastserviceinstructions-detail.component';

describe('Lastserviceinstructions Management Detail Component', () => {
  let comp: LastserviceinstructionsDetailComponent;
  let fixture: ComponentFixture<LastserviceinstructionsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LastserviceinstructionsDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: LastserviceinstructionsDetailComponent,
              resolve: { lastserviceinstructions: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(LastserviceinstructionsDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LastserviceinstructionsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load lastserviceinstructions on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', LastserviceinstructionsDetailComponent);

      // THEN
      expect(instance.lastserviceinstructions()).toEqual(expect.objectContaining({ id: 123 }));
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
