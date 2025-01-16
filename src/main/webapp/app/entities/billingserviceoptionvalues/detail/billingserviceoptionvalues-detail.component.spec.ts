import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { BillingserviceoptionvaluesDetailComponent } from './billingserviceoptionvalues-detail.component';

describe('Billingserviceoptionvalues Management Detail Component', () => {
  let comp: BillingserviceoptionvaluesDetailComponent;
  let fixture: ComponentFixture<BillingserviceoptionvaluesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BillingserviceoptionvaluesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: BillingserviceoptionvaluesDetailComponent,
              resolve: { billingserviceoptionvalues: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(BillingserviceoptionvaluesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BillingserviceoptionvaluesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load billingserviceoptionvalues on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', BillingserviceoptionvaluesDetailComponent);

      // THEN
      expect(instance.billingserviceoptionvalues()).toEqual(expect.objectContaining({ id: 123 }));
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
