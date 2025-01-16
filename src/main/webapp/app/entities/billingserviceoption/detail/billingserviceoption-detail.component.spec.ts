import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { BillingserviceoptionDetailComponent } from './billingserviceoption-detail.component';

describe('Billingserviceoption Management Detail Component', () => {
  let comp: BillingserviceoptionDetailComponent;
  let fixture: ComponentFixture<BillingserviceoptionDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BillingserviceoptionDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: BillingserviceoptionDetailComponent,
              resolve: { billingserviceoption: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(BillingserviceoptionDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BillingserviceoptionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load billingserviceoption on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', BillingserviceoptionDetailComponent);

      // THEN
      expect(instance.billingserviceoption()).toEqual(expect.objectContaining({ id: 123 }));
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
