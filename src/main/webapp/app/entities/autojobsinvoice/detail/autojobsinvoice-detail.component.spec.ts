import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutojobsinvoiceDetailComponent } from './autojobsinvoice-detail.component';

describe('Autojobsinvoice Management Detail Component', () => {
  let comp: AutojobsinvoiceDetailComponent;
  let fixture: ComponentFixture<AutojobsinvoiceDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutojobsinvoiceDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutojobsinvoiceDetailComponent,
              resolve: { autojobsinvoice: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutojobsinvoiceDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutojobsinvoiceDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autojobsinvoice on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutojobsinvoiceDetailComponent);

      // THEN
      expect(instance.autojobsinvoice()).toEqual(expect.objectContaining({ id: 123 }));
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
