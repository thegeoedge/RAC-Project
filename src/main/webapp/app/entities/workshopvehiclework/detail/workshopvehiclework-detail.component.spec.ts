import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { WorkshopvehicleworkDetailComponent } from './workshopvehiclework-detail.component';

describe('Workshopvehiclework Management Detail Component', () => {
  let comp: WorkshopvehicleworkDetailComponent;
  let fixture: ComponentFixture<WorkshopvehicleworkDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkshopvehicleworkDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: WorkshopvehicleworkDetailComponent,
              resolve: { workshopvehiclework: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(WorkshopvehicleworkDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkshopvehicleworkDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load workshopvehiclework on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', WorkshopvehicleworkDetailComponent);

      // THEN
      expect(instance.workshopvehiclework()).toEqual(expect.objectContaining({ id: 123 }));
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
