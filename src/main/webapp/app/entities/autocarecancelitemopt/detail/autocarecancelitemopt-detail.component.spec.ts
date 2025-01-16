import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocarecancelitemoptDetailComponent } from './autocarecancelitemopt-detail.component';

describe('Autocarecancelitemopt Management Detail Component', () => {
  let comp: AutocarecancelitemoptDetailComponent;
  let fixture: ComponentFixture<AutocarecancelitemoptDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocarecancelitemoptDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocarecancelitemoptDetailComponent,
              resolve: { autocarecancelitemopt: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocarecancelitemoptDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocarecancelitemoptDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocarecancelitemopt on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocarecancelitemoptDetailComponent);

      // THEN
      expect(instance.autocarecancelitemopt()).toEqual(expect.objectContaining({ id: 123 }));
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
