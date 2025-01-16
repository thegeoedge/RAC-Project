import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { WorkshopworklistDetailComponent } from './workshopworklist-detail.component';

describe('Workshopworklist Management Detail Component', () => {
  let comp: WorkshopworklistDetailComponent;
  let fixture: ComponentFixture<WorkshopworklistDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkshopworklistDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: WorkshopworklistDetailComponent,
              resolve: { workshopworklist: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(WorkshopworklistDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkshopworklistDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load workshopworklist on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', WorkshopworklistDetailComponent);

      // THEN
      expect(instance.workshopworklist()).toEqual(expect.objectContaining({ id: 123 }));
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
