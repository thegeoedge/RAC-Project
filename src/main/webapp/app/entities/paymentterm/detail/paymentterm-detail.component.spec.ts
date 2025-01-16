import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { PaymenttermDetailComponent } from './paymentterm-detail.component';

describe('Paymentterm Management Detail Component', () => {
  let comp: PaymenttermDetailComponent;
  let fixture: ComponentFixture<PaymenttermDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaymenttermDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: PaymenttermDetailComponent,
              resolve: { paymentterm: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(PaymenttermDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymenttermDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load paymentterm on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', PaymenttermDetailComponent);

      // THEN
      expect(instance.paymentterm()).toEqual(expect.objectContaining({ id: 123 }));
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
