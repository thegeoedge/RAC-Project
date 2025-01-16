import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutojobempallocationDetailComponent } from './autojobempallocation-detail.component';

describe('Autojobempallocation Management Detail Component', () => {
  let comp: AutojobempallocationDetailComponent;
  let fixture: ComponentFixture<AutojobempallocationDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutojobempallocationDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutojobempallocationDetailComponent,
              resolve: { autojobempallocation: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutojobempallocationDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutojobempallocationDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autojobempallocation on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutojobempallocationDetailComponent);

      // THEN
      expect(instance.autojobempallocation()).toEqual(expect.objectContaining({ id: 123 }));
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
