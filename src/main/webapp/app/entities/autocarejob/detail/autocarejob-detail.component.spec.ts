import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocarejobDetailComponent } from './autocarejob-detail.component';

describe('Autocarejob Management Detail Component', () => {
  let comp: AutocarejobDetailComponent;
  let fixture: ComponentFixture<AutocarejobDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocarejobDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocarejobDetailComponent,
              resolve: { autocarejob: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocarejobDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocarejobDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocarejob on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocarejobDetailComponent);

      // THEN
      expect(instance.autocarejob()).toEqual(expect.objectContaining({ id: 123 }));
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
