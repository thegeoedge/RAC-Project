import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AutocarejobinimagesDetailComponent } from './autocarejobinimages-detail.component';

describe('Autocarejobinimages Management Detail Component', () => {
  let comp: AutocarejobinimagesDetailComponent;
  let fixture: ComponentFixture<AutocarejobinimagesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutocarejobinimagesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AutocarejobinimagesDetailComponent,
              resolve: { autocarejobinimages: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AutocarejobinimagesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocarejobinimagesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autocarejobinimages on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AutocarejobinimagesDetailComponent);

      // THEN
      expect(instance.autocarejobinimages()).toEqual(expect.objectContaining({ id: 123 }));
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
