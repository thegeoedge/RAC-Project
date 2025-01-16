import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { VehicletypeService } from '../service/vehicletype.service';
import { IVehicletype } from '../vehicletype.model';
import { VehicletypeFormService } from './vehicletype-form.service';

import { VehicletypeUpdateComponent } from './vehicletype-update.component';

describe('Vehicletype Management Update Component', () => {
  let comp: VehicletypeUpdateComponent;
  let fixture: ComponentFixture<VehicletypeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let vehicletypeFormService: VehicletypeFormService;
  let vehicletypeService: VehicletypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, VehicletypeUpdateComponent],
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
      .overrideTemplate(VehicletypeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VehicletypeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    vehicletypeFormService = TestBed.inject(VehicletypeFormService);
    vehicletypeService = TestBed.inject(VehicletypeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const vehicletype: IVehicletype = { id: 456 };

      activatedRoute.data = of({ vehicletype });
      comp.ngOnInit();

      expect(comp.vehicletype).toEqual(vehicletype);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehicletype>>();
      const vehicletype = { id: 123 };
      jest.spyOn(vehicletypeFormService, 'getVehicletype').mockReturnValue(vehicletype);
      jest.spyOn(vehicletypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehicletype });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehicletype }));
      saveSubject.complete();

      // THEN
      expect(vehicletypeFormService.getVehicletype).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(vehicletypeService.update).toHaveBeenCalledWith(expect.objectContaining(vehicletype));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehicletype>>();
      const vehicletype = { id: 123 };
      jest.spyOn(vehicletypeFormService, 'getVehicletype').mockReturnValue({ id: null });
      jest.spyOn(vehicletypeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehicletype: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehicletype }));
      saveSubject.complete();

      // THEN
      expect(vehicletypeFormService.getVehicletype).toHaveBeenCalled();
      expect(vehicletypeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehicletype>>();
      const vehicletype = { id: 123 };
      jest.spyOn(vehicletypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehicletype });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(vehicletypeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
