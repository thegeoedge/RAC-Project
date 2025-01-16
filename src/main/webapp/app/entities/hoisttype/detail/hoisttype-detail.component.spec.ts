import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { HoisttypeDetailComponent } from './hoisttype-detail.component';

describe('Hoisttype Management Detail Component', () => {
  let comp: HoisttypeDetailComponent;
  let fixture: ComponentFixture<HoisttypeDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HoisttypeDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: HoisttypeDetailComponent,
              resolve: { hoisttype: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(HoisttypeDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HoisttypeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load hoisttype on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', HoisttypeDetailComponent);

      // THEN
      expect(instance.hoisttype()).toEqual(expect.objectContaining({ id: 123 }));
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
