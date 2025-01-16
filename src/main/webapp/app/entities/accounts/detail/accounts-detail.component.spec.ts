import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AccountsDetailComponent } from './accounts-detail.component';

describe('Accounts Management Detail Component', () => {
  let comp: AccountsDetailComponent;
  let fixture: ComponentFixture<AccountsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountsDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AccountsDetailComponent,
              resolve: { accounts: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AccountsDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load accounts on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AccountsDetailComponent);

      // THEN
      expect(instance.accounts()).toEqual(expect.objectContaining({ id: 123 }));
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
