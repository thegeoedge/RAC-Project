import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { VehiclebrandmodelService } from '../service/vehiclebrandmodel.service';
import { IVehiclebrandmodel } from '../vehiclebrandmodel.model';
import { VehiclebrandmodelFormService } from './vehiclebrandmodel-form.service';

import { VehiclebrandmodelUpdateComponent } from './vehiclebrandmodel-update.component';

describe('Vehiclebrandmodel Management Update Component', () => {
  let comp: VehiclebrandmodelUpdateComponent;
  let fixture: ComponentFixture<VehiclebrandmodelUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let vehiclebrandmodelFormService: VehiclebrandmodelFormService;
  let vehiclebrandmodelService: VehiclebrandmodelService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, VehiclebrandmodelUpdateComponent],
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
      .overrideTemplate(VehiclebrandmodelUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VehiclebrandmodelUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    vehiclebrandmodelFormService = TestBed.inject(VehiclebrandmodelFormService);
    vehiclebrandmodelService = TestBed.inject(VehiclebrandmodelService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const vehiclebrandmodel: IVehiclebrandmodel = { id: 456 };

      activatedRoute.data = of({ vehiclebrandmodel });
      comp.ngOnInit();

      expect(comp.vehiclebrandmodel).toEqual(vehiclebrandmodel);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclebrandmodel>>();
      const vehiclebrandmodel = { id: 123 };
      jest.spyOn(vehiclebrandmodelFormService, 'getVehiclebrandmodel').mockReturnValue(vehiclebrandmodel);
      jest.spyOn(vehiclebrandmodelService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclebrandmodel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehiclebrandmodel }));
      saveSubject.complete();

      // THEN
      expect(vehiclebrandmodelFormService.getVehiclebrandmodel).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(vehiclebrandmodelService.update).toHaveBeenCalledWith(expect.objectContaining(vehiclebrandmodel));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclebrandmodel>>();
      const vehiclebrandmodel = { id: 123 };
      jest.spyOn(vehiclebrandmodelFormService, 'getVehiclebrandmodel').mockReturnValue({ id: null });
      jest.spyOn(vehiclebrandmodelService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclebrandmodel: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehiclebrandmodel }));
      saveSubject.complete();

      // THEN
      expect(vehiclebrandmodelFormService.getVehiclebrandmodel).toHaveBeenCalled();
      expect(vehiclebrandmodelService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclebrandmodel>>();
      const vehiclebrandmodel = { id: 123 };
      jest.spyOn(vehiclebrandmodelService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclebrandmodel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(vehiclebrandmodelService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
