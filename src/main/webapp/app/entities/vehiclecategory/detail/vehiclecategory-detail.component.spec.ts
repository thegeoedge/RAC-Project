import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { VehiclecategoryDetailComponent } from './vehiclecategory-detail.component';

describe('Vehiclecategory Management Detail Component', () => {
  let comp: VehiclecategoryDetailComponent;
  let fixture: ComponentFixture<VehiclecategoryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiclecategoryDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: VehiclecategoryDetailComponent,
              resolve: { vehiclecategory: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(VehiclecategoryDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclecategoryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load vehiclecategory on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', VehiclecategoryDetailComponent);

      // THEN
      expect(instance.vehiclecategory()).toEqual(expect.objectContaining({ id: 123 }));
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
