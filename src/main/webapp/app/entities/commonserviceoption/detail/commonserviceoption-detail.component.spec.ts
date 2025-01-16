import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { CommonserviceoptionDetailComponent } from './commonserviceoption-detail.component';

describe('Commonserviceoption Management Detail Component', () => {
  let comp: CommonserviceoptionDetailComponent;
  let fixture: ComponentFixture<CommonserviceoptionDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommonserviceoptionDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CommonserviceoptionDetailComponent,
              resolve: { commonserviceoption: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CommonserviceoptionDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonserviceoptionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load commonserviceoption on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CommonserviceoptionDetailComponent);

      // THEN
      expect(instance.commonserviceoption()).toEqual(expect.objectContaining({ id: 123 }));
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
