import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { InventorybatchesService } from '../service/inventorybatches.service';
import { IInventorybatches } from '../inventorybatches.model';
import { InventorybatchesFormService } from './inventorybatches-form.service';

import { InventorybatchesUpdateComponent } from './inventorybatches-update.component';

describe('Inventorybatches Management Update Component', () => {
  let comp: InventorybatchesUpdateComponent;
  let fixture: ComponentFixture<InventorybatchesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let inventorybatchesFormService: InventorybatchesFormService;
  let inventorybatchesService: InventorybatchesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, InventorybatchesUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(InventorybatchesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(InventorybatchesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    inventorybatchesFormService = TestBed.inject(InventorybatchesFormService);
    inventorybatchesService = TestBed.inject(InventorybatchesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const inventorybatches: IInventorybatches = { id: 456 };

      activatedRoute.data = of({ inventorybatches });
      comp.ngOnInit();

      expect(comp.inventorybatches).toEqual(inventorybatches);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInventorybatches>>();
      const inventorybatches = { id: 123 };
      jest.spyOn(inventorybatchesFormService, 'getInventorybatches').mockReturnValue(inventorybatches);
      jest.spyOn(inventorybatchesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ inventorybatches });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: inventorybatches }));
      saveSubject.complete();

      // THEN
      expect(inventorybatchesFormService.getInventorybatches).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(inventorybatchesService.update).toHaveBeenCalledWith(expect.objectContaining(inventorybatches));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInventorybatches>>();
      const inventorybatches = { id: 123 };
      jest.spyOn(inventorybatchesFormService, 'getInventorybatches').mockReturnValue({ id: null });
      jest.spyOn(inventorybatchesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ inventorybatches: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: inventorybatches }));
      saveSubject.complete();

      // THEN
      expect(inventorybatchesFormService.getInventorybatches).toHaveBeenCalled();
      expect(inventorybatchesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInventorybatches>>();
      const inventorybatches = { id: 123 };
      jest.spyOn(inventorybatchesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ inventorybatches });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(inventorybatchesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
