import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { CustomervehicleService } from '../service/customervehicle.service';
import { ICustomervehicle } from '../customervehicle.model';
import { CustomervehicleFormService } from './customervehicle-form.service';

import { CustomervehicleUpdateComponent } from './customervehicle-update.component';

describe('Customervehicle Management Update Component', () => {
  let comp: CustomervehicleUpdateComponent;
  let fixture: ComponentFixture<CustomervehicleUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let customervehicleFormService: CustomervehicleFormService;
  let customervehicleService: CustomervehicleService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, CustomervehicleUpdateComponent],
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
      .overrideTemplate(CustomervehicleUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CustomervehicleUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    customervehicleFormService = TestBed.inject(CustomervehicleFormService);
    customervehicleService = TestBed.inject(CustomervehicleService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const customervehicle: ICustomervehicle = { id: 456 };

      activatedRoute.data = of({ customervehicle });
      comp.ngOnInit();

      expect(comp.customervehicle).toEqual(customervehicle);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomervehicle>>();
      const customervehicle = { id: 123 };
      jest.spyOn(customervehicleFormService, 'getCustomervehicle').mockReturnValue(customervehicle);
      jest.spyOn(customervehicleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ customervehicle });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: customervehicle }));
      saveSubject.complete();

      // THEN
      expect(customervehicleFormService.getCustomervehicle).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(customervehicleService.update).toHaveBeenCalledWith(expect.objectContaining(customervehicle));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomervehicle>>();
      const customervehicle = { id: 123 };
      jest.spyOn(customervehicleFormService, 'getCustomervehicle').mockReturnValue({ id: null });
      jest.spyOn(customervehicleService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ customervehicle: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: customervehicle }));
      saveSubject.complete();

      // THEN
      expect(customervehicleFormService.getCustomervehicle).toHaveBeenCalled();
      expect(customervehicleService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomervehicle>>();
      const customervehicle = { id: 123 };
      jest.spyOn(customervehicleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ customervehicle });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(customervehicleService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
