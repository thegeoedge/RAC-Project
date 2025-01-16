import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { BankbranchDetailComponent } from './bankbranch-detail.component';

describe('Bankbranch Management Detail Component', () => {
  let comp: BankbranchDetailComponent;
  let fixture: ComponentFixture<BankbranchDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankbranchDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: BankbranchDetailComponent,
              resolve: { bankbranch: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(BankbranchDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankbranchDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load bankbranch on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', BankbranchDetailComponent);

      // THEN
      expect(instance.bankbranch()).toEqual(expect.objectContaining({ id: 123 }));
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
