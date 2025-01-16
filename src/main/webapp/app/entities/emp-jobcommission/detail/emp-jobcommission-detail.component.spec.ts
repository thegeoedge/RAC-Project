import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { EmpJobcommissionDetailComponent } from './emp-jobcommission-detail.component';

describe('EmpJobcommission Management Detail Component', () => {
  let comp: EmpJobcommissionDetailComponent;
  let fixture: ComponentFixture<EmpJobcommissionDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpJobcommissionDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: EmpJobcommissionDetailComponent,
              resolve: { empJobcommission: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(EmpJobcommissionDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpJobcommissionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load empJobcommission on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', EmpJobcommissionDetailComponent);

      // THEN
      expect(instance.empJobcommission()).toEqual(expect.objectContaining({ id: 123 }));
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
