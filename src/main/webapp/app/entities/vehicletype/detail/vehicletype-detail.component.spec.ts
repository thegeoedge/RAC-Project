import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { VehicletypeDetailComponent } from './vehicletype-detail.component';

describe('Vehicletype Management Detail Component', () => {
  let comp: VehicletypeDetailComponent;
  let fixture: ComponentFixture<VehicletypeDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicletypeDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: VehicletypeDetailComponent,
              resolve: { vehicletype: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(VehicletypeDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicletypeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load vehicletype on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', VehicletypeDetailComponent);

      // THEN
      expect(instance.vehicletype()).toEqual(expect.objectContaining({ id: 123 }));
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
