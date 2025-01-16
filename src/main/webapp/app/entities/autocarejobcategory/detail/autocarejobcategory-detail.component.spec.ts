import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocarejobcategoryDetailComponent } from './autocarejobcategory-detail.component';

describe('Autocarejobcategory Management Detail Component', () => {
  let comp: AutocarejobcategoryDetailComponent;
  let fixture: ComponentFixture<AutocarejobcategoryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocarejobcategoryDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocarejobcategoryDetailComponent,
              resolve: { autocarejobcategory: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocarejobcategoryDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocarejobcategoryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocarejobcategory on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocarejobcategoryDetailComponent);

      // THEN
      expect(instance.autocarejobcategory()).toEqual(expect.objectContaining({ id: 123 }));
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
