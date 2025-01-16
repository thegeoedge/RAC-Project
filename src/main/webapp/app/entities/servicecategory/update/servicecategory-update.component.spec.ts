import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { ServicecategoryService } from '../service/servicecategory.service';
import { IServicecategory } from '../servicecategory.model';
import { ServicecategoryFormService } from './servicecategory-form.service';

import { ServicecategoryUpdateComponent } from './servicecategory-update.component';

describe('Servicecategory Management Update Component', () => {
  let comp: ServicecategoryUpdateComponent;
  let fixture: ComponentFixture<ServicecategoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let servicecategoryFormService: ServicecategoryFormService;
  let servicecategoryService: ServicecategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ServicecategoryUpdateComponent],
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
      .overrideTemplate(ServicecategoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ServicecategoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    servicecategoryFormService = TestBed.inject(ServicecategoryFormService);
    servicecategoryService = TestBed.inject(ServicecategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const servicecategory: IServicecategory = { id: 456 };

      activatedRoute.data = of({ servicecategory });
      comp.ngOnInit();

      expect(comp.servicecategory).toEqual(servicecategory);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicecategory>>();
      const servicecategory = { id: 123 };
      jest.spyOn(servicecategoryFormService, 'getServicecategory').mockReturnValue(servicecategory);
      jest.spyOn(servicecategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicecategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicecategory }));
      saveSubject.complete();

      // THEN
      expect(servicecategoryFormService.getServicecategory).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(servicecategoryService.update).toHaveBeenCalledWith(expect.objectContaining(servicecategory));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicecategory>>();
      const servicecategory = { id: 123 };
      jest.spyOn(servicecategoryFormService, 'getServicecategory').mockReturnValue({ id: null });
      jest.spyOn(servicecategoryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicecategory: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicecategory }));
      saveSubject.complete();

      // THEN
      expect(servicecategoryFormService.getServicecategory).toHaveBeenCalled();
      expect(servicecategoryService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicecategory>>();
      const servicecategory = { id: 123 };
      jest.spyOn(servicecategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicecategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(servicecategoryService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
