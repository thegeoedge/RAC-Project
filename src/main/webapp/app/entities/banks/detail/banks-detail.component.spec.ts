import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { BanksDetailComponent } from './banks-detail.component';

describe('Banks Management Detail Component', () => {
  let comp: BanksDetailComponent;
  let fixture: ComponentFixture<BanksDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BanksDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: BanksDetailComponent,
              resolve: { banks: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(BanksDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BanksDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load banks on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', BanksDetailComponent);

      // THEN
      expect(instance.banks()).toEqual(expect.objectContaining({ id: 123 }));
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
