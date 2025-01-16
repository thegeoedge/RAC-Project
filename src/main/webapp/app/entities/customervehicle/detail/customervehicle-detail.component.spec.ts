import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { CustomervehicleDetailComponent } from './customervehicle-detail.component';

describe('Customervehicle Management Detail Component', () => {
  let comp: CustomervehicleDetailComponent;
  let fixture: ComponentFixture<CustomervehicleDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomervehicleDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CustomervehicleDetailComponent,
              resolve: { customervehicle: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CustomervehicleDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomervehicleDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load customervehicle on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CustomervehicleDetailComponent);

      // THEN
      expect(instance.customervehicle()).toEqual(expect.objectContaining({ id: 123 }));
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
