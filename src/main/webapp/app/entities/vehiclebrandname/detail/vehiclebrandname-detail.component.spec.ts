import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { VehiclebrandnameDetailComponent } from './vehiclebrandname-detail.component';

describe('Vehiclebrandname Management Detail Component', () => {
  let comp: VehiclebrandnameDetailComponent;
  let fixture: ComponentFixture<VehiclebrandnameDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiclebrandnameDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: VehiclebrandnameDetailComponent,
              resolve: { vehiclebrandname: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(VehiclebrandnameDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclebrandnameDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load vehiclebrandname on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', VehiclebrandnameDetailComponent);

      // THEN
      expect(instance.vehiclebrandname()).toEqual(expect.objectContaining({ id: 123 }));
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
