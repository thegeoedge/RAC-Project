import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { VehiclebrandnameService } from '../service/vehiclebrandname.service';
import { IVehiclebrandname } from '../vehiclebrandname.model';
import { VehiclebrandnameFormService } from './vehiclebrandname-form.service';

import { VehiclebrandnameUpdateComponent } from './vehiclebrandname-update.component';

describe('Vehiclebrandname Management Update Component', () => {
  let comp: VehiclebrandnameUpdateComponent;
  let fixture: ComponentFixture<VehiclebrandnameUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let vehiclebrandnameFormService: VehiclebrandnameFormService;
  let vehiclebrandnameService: VehiclebrandnameService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, VehiclebrandnameUpdateComponent],
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
      .overrideTemplate(VehiclebrandnameUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VehiclebrandnameUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    vehiclebrandnameFormService = TestBed.inject(VehiclebrandnameFormService);
    vehiclebrandnameService = TestBed.inject(VehiclebrandnameService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const vehiclebrandname: IVehiclebrandname = { id: 456 };

      activatedRoute.data = of({ vehiclebrandname });
      comp.ngOnInit();

      expect(comp.vehiclebrandname).toEqual(vehiclebrandname);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclebrandname>>();
      const vehiclebrandname = { id: 123 };
      jest.spyOn(vehiclebrandnameFormService, 'getVehiclebrandname').mockReturnValue(vehiclebrandname);
      jest.spyOn(vehiclebrandnameService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclebrandname });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehiclebrandname }));
      saveSubject.complete();

      // THEN
      expect(vehiclebrandnameFormService.getVehiclebrandname).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(vehiclebrandnameService.update).toHaveBeenCalledWith(expect.objectContaining(vehiclebrandname));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclebrandname>>();
      const vehiclebrandname = { id: 123 };
      jest.spyOn(vehiclebrandnameFormService, 'getVehiclebrandname').mockReturnValue({ id: null });
      jest.spyOn(vehiclebrandnameService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclebrandname: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vehiclebrandname }));
      saveSubject.complete();

      // THEN
      expect(vehiclebrandnameFormService.getVehiclebrandname).toHaveBeenCalled();
      expect(vehiclebrandnameService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVehiclebrandname>>();
      const vehiclebrandname = { id: 123 };
      jest.spyOn(vehiclebrandnameService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vehiclebrandname });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(vehiclebrandnameService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
