import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { StocklocationsDetailComponent } from './stocklocations-detail.component';

describe('Stocklocations Management Detail Component', () => {
  let comp: StocklocationsDetailComponent;
  let fixture: ComponentFixture<StocklocationsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StocklocationsDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: StocklocationsDetailComponent,
              resolve: { stocklocations: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(StocklocationsDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StocklocationsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load stocklocations on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', StocklocationsDetailComponent);

      // THEN
      expect(instance.stocklocations()).toEqual(expect.objectContaining({ id: 123 }));
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
