import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { NextserviceinstructionsDetailComponent } from './nextserviceinstructions-detail.component';

describe('Nextserviceinstructions Management Detail Component', () => {
  let comp: NextserviceinstructionsDetailComponent;
  let fixture: ComponentFixture<NextserviceinstructionsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NextserviceinstructionsDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: NextserviceinstructionsDetailComponent,
              resolve: { nextserviceinstructions: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(NextserviceinstructionsDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NextserviceinstructionsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load nextserviceinstructions on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', NextserviceinstructionsDetailComponent);

      // THEN
      expect(instance.nextserviceinstructions()).toEqual(expect.objectContaining({ id: 123 }));
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
