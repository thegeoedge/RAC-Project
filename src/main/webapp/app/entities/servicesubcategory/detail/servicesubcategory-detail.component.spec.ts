import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ServicesubcategoryDetailComponent } from './servicesubcategory-detail.component';

describe('Servicesubcategory Management Detail Component', () => {
  let comp: ServicesubcategoryDetailComponent;
  let fixture: ComponentFixture<ServicesubcategoryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicesubcategoryDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ServicesubcategoryDetailComponent,
              resolve: { servicesubcategory: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ServicesubcategoryDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicesubcategoryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load servicesubcategory on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ServicesubcategoryDetailComponent);

      // THEN
      expect(instance.servicesubcategory()).toEqual(expect.objectContaining({ id: 123 }));
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
