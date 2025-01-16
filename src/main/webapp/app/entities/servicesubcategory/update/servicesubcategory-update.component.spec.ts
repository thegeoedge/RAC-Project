import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { ServicesubcategoryService } from '../service/servicesubcategory.service';
import { IServicesubcategory } from '../servicesubcategory.model';
import { ServicesubcategoryFormService } from './servicesubcategory-form.service';

import { ServicesubcategoryUpdateComponent } from './servicesubcategory-update.component';

describe('Servicesubcategory Management Update Component', () => {
  let comp: ServicesubcategoryUpdateComponent;
  let fixture: ComponentFixture<ServicesubcategoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let servicesubcategoryFormService: ServicesubcategoryFormService;
  let servicesubcategoryService: ServicesubcategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ServicesubcategoryUpdateComponent],
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
      .overrideTemplate(ServicesubcategoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ServicesubcategoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    servicesubcategoryFormService = TestBed.inject(ServicesubcategoryFormService);
    servicesubcategoryService = TestBed.inject(ServicesubcategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const servicesubcategory: IServicesubcategory = { id: 456 };

      activatedRoute.data = of({ servicesubcategory });
      comp.ngOnInit();

      expect(comp.servicesubcategory).toEqual(servicesubcategory);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicesubcategory>>();
      const servicesubcategory = { id: 123 };
      jest.spyOn(servicesubcategoryFormService, 'getServicesubcategory').mockReturnValue(servicesubcategory);
      jest.spyOn(servicesubcategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicesubcategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicesubcategory }));
      saveSubject.complete();

      // THEN
      expect(servicesubcategoryFormService.getServicesubcategory).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(servicesubcategoryService.update).toHaveBeenCalledWith(expect.objectContaining(servicesubcategory));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicesubcategory>>();
      const servicesubcategory = { id: 123 };
      jest.spyOn(servicesubcategoryFormService, 'getServicesubcategory').mockReturnValue({ id: null });
      jest.spyOn(servicesubcategoryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicesubcategory: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicesubcategory }));
      saveSubject.complete();

      // THEN
      expect(servicesubcategoryFormService.getServicesubcategory).toHaveBeenCalled();
      expect(servicesubcategoryService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicesubcategory>>();
      const servicesubcategory = { id: 123 };
      jest.spyOn(servicesubcategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicesubcategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(servicesubcategoryService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
