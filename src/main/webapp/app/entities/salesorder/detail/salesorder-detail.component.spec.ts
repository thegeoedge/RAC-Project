import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { SalesorderDetailComponent } from './salesorder-detail.component';

describe('Salesorder Management Detail Component', () => {
  let comp: SalesorderDetailComponent;
  let fixture: ComponentFixture<SalesorderDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SalesorderDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: SalesorderDetailComponent,
              resolve: { salesorder: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(SalesorderDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesorderDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load salesorder on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', SalesorderDetailComponent);

      // THEN
      expect(instance.salesorder()).toEqual(expect.objectContaining({ id: 123 }));
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
