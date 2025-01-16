import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { CompanybankaccountDetailComponent } from './companybankaccount-detail.component';

describe('Companybankaccount Management Detail Component', () => {
  let comp: CompanybankaccountDetailComponent;
  let fixture: ComponentFixture<CompanybankaccountDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompanybankaccountDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CompanybankaccountDetailComponent,
              resolve: { companybankaccount: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CompanybankaccountDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanybankaccountDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load companybankaccount on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CompanybankaccountDetailComponent);

      // THEN
      expect(instance.companybankaccount()).toEqual(expect.objectContaining({ id: 123 }));
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
