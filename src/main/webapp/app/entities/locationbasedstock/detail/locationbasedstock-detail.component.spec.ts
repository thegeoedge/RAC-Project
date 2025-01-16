import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { LocationbasedstockDetailComponent } from './locationbasedstock-detail.component';

describe('Locationbasedstock Management Detail Component', () => {
  let comp: LocationbasedstockDetailComponent;
  let fixture: ComponentFixture<LocationbasedstockDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LocationbasedstockDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: LocationbasedstockDetailComponent,
              resolve: { locationbasedstock: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(LocationbasedstockDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LocationbasedstockDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load locationbasedstock on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', LocationbasedstockDetailComponent);

      // THEN
      expect(instance.locationbasedstock()).toEqual(expect.objectContaining({ id: 123 }));
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
