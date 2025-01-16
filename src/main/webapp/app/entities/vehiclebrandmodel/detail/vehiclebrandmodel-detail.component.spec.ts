import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { VehiclebrandmodelDetailComponent } from './vehiclebrandmodel-detail.component';

describe('Vehiclebrandmodel Management Detail Component', () => {
  let comp: VehiclebrandmodelDetailComponent;
  let fixture: ComponentFixture<VehiclebrandmodelDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiclebrandmodelDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: VehiclebrandmodelDetailComponent,
              resolve: { vehiclebrandmodel: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(VehiclebrandmodelDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclebrandmodelDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load vehiclebrandmodel on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', VehiclebrandmodelDetailComponent);

      // THEN
      expect(instance.vehiclebrandmodel()).toEqual(expect.objectContaining({ id: 123 }));
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
