import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { EmpSectiontblDetailComponent } from './emp-sectiontbl-detail.component';

describe('EmpSectiontbl Management Detail Component', () => {
  let comp: EmpSectiontblDetailComponent;
  let fixture: ComponentFixture<EmpSectiontblDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpSectiontblDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: EmpSectiontblDetailComponent,
              resolve: { empSectiontbl: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(EmpSectiontblDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpSectiontblDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load empSectiontbl on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', EmpSectiontblDetailComponent);

      // THEN
      expect(instance.empSectiontbl()).toEqual(expect.objectContaining({ id: 123 }));
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
