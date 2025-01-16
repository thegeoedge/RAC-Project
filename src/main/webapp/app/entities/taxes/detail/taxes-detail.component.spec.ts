import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { TaxesDetailComponent } from './taxes-detail.component';

describe('Taxes Management Detail Component', () => {
  let comp: TaxesDetailComponent;
  let fixture: ComponentFixture<TaxesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaxesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: TaxesDetailComponent,
              resolve: { taxes: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TaxesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaxesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load taxes on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TaxesDetailComponent);

      // THEN
      expect(instance.taxes()).toEqual(expect.objectContaining({ id: 123 }));
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
