import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { SalesinvoiceDetailComponent } from './salesinvoice-detail.component';

describe('Salesinvoice Management Detail Component', () => {
  let comp: SalesinvoiceDetailComponent;
  let fixture: ComponentFixture<SalesinvoiceDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SalesinvoiceDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: SalesinvoiceDetailComponent,
              resolve: { salesinvoice: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(SalesinvoiceDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesinvoiceDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load salesinvoice on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', SalesinvoiceDetailComponent);

      // THEN
      expect(instance.salesinvoice()).toEqual(expect.objectContaining({ id: 123 }));
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
