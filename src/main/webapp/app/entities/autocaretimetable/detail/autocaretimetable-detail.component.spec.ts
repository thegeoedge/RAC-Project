import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocaretimetableDetailComponent } from './autocaretimetable-detail.component';

describe('Autocaretimetable Management Detail Component', () => {
  let comp: AutocaretimetableDetailComponent;
  let fixture: ComponentFixture<AutocaretimetableDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocaretimetableDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocaretimetableDetailComponent,
              resolve: { autocaretimetable: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocaretimetableDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocaretimetableDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocaretimetable on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocaretimetableDetailComponent);

      // THEN
      expect(instance.autocaretimetable()).toEqual(expect.objectContaining({ id: 123 }));
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
