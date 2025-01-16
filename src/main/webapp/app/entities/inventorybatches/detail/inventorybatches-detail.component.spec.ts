import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { InventorybatchesDetailComponent } from './inventorybatches-detail.component';

describe('Inventorybatches Management Detail Component', () => {
  let comp: InventorybatchesDetailComponent;
  let fixture: ComponentFixture<InventorybatchesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InventorybatchesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: InventorybatchesDetailComponent,
              resolve: { inventorybatches: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(InventorybatchesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InventorybatchesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load inventorybatches on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', InventorybatchesDetailComponent);

      // THEN
      expect(instance.inventorybatches()).toEqual(expect.objectContaining({ id: 123 }));
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
