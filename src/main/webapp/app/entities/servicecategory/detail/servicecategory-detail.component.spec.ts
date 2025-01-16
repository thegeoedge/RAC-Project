import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ServicecategoryDetailComponent } from './servicecategory-detail.component';

describe('Servicecategory Management Detail Component', () => {
  let comp: ServicecategoryDetailComponent;
  let fixture: ComponentFixture<ServicecategoryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicecategoryDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ServicecategoryDetailComponent,
              resolve: { servicecategory: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ServicecategoryDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicecategoryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load servicecategory on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ServicecategoryDetailComponent);

      // THEN
      expect(instance.servicecategory()).toEqual(expect.objectContaining({ id: 123 }));
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
