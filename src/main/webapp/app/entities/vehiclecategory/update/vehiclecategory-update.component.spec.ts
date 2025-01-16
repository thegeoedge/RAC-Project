import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { VehiclecategoryService } from '../service/vehiclecategory.service';
import { IVehiclecategory } from '../vehiclecategory.model';
import { VehiclecategoryFormService } from './vehiclecategory-form.service';

import { VehiclecategoryUpdateComponent } from './vehiclecategory-update.component';

describe('Vehiclecategory Management Update Component', () => {
  let comp: VehiclecategoryUpdateComponent;
  let fixture: ComponentFixture<VehiclecategoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let vehiclecategoryFormService: VehiclecategoryFormService;
  let vehiclecategoryService: VehiclecategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, VehiclecategoryUpdateComponent],
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
      .overrideTemplate(VehiclecategoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VehiclecategoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    vehiclecategoryFormService = TestBed.inject(VehiclecategoryFormService);
    vehiclecategoryService = TestBed.inject(VehiclecategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const vehiclecategory: IVehiclecategory = { id: 456 };

      activatedRoute.data = of({ vehiclecategory });
      comp.ngOnInit();

      expect(comp.vehiclecategory).toEqual(vehiclecategory);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclecategory>>();
      const vehiclecategory = { id: 123 };
      jest.spyOn(vehiclecategoryFormService, 'getVehiclecategory').mockReturnValue(vehiclecategory);
      jest.spyOn(vehiclecategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclecategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehiclecategory }));
      saveSubject.complete();

      // THEN
      expect(vehiclecategoryFormService.getVehiclecategory).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(vehiclecategoryService.update).toHaveBeenCalledWith(expect.objectContaining(vehiclecategory));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclecategory>>();
      const vehiclecategory = { id: 123 };
      jest.spyOn(vehiclecategoryFormService, 'getVehiclecategory').mockReturnValue({ id: null });
      jest.spyOn(vehiclecategoryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclecategory: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehiclecategory }));
      saveSubject.complete();

      // THEN
      expect(vehiclecategoryFormService.getVehiclecategory).toHaveBeenCalled();
      expect(vehiclecategoryService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclecategory>>();
      const vehiclecategory = { id: 123 };
      jest.spyOn(vehiclecategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclecategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(vehiclecategoryService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
