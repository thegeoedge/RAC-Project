import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocarejobcategoryService } from '../service/autocarejobcategory.service';
import { IAutocarejobcategory } from '../autocarejobcategory.model';
import { AutocarejobcategoryFormService } from './autocarejobcategory-form.service';

import { AutocarejobcategoryUpdateComponent } from './autocarejobcategory-update.component';

describe('Autocarejobcategory Management Update Component', () => {
  let comp: AutocarejobcategoryUpdateComponent;
  let fixture: ComponentFixture<AutocarejobcategoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocarejobcategoryFormService: AutocarejobcategoryFormService;
  let autocarejobcategoryService: AutocarejobcategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocarejobcategoryUpdateComponent],
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
      .overrideTemplate(AutocarejobcategoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocarejobcategoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocarejobcategoryFormService = TestBed.inject(AutocarejobcategoryFormService);
    autocarejobcategoryService = TestBed.inject(AutocarejobcategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocarejobcategory: IAutocarejobcategory = { id: 456 };

      activatedRoute.data = of({ autocarejobcategory });
      comp.ngOnInit();

      expect(comp.autocarejobcategory).toEqual(autocarejobcategory);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejobcategory>>();
      const autocarejobcategory = { id: 123 };
      jest.spyOn(autocarejobcategoryFormService, 'getAutocarejobcategory').mockReturnValue(autocarejobcategory);
      jest.spyOn(autocarejobcategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejobcategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarejobcategory }));
      saveSubject.complete();

      // THEN
      expect(autocarejobcategoryFormService.getAutocarejobcategory).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocarejobcategoryService.update).toHaveBeenCalledWith(expect.objectContaining(autocarejobcategory));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejobcategory>>();
      const autocarejobcategory = { id: 123 };
      jest.spyOn(autocarejobcategoryFormService, 'getAutocarejobcategory').mockReturnValue({ id: null });
      jest.spyOn(autocarejobcategoryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejobcategory: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarejobcategory }));
      saveSubject.complete();

      // THEN
      expect(autocarejobcategoryFormService.getAutocarejobcategory).toHaveBeenCalled();
      expect(autocarejobcategoryService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejobcategory>>();
      const autocarejobcategory = { id: 123 };
      jest.spyOn(autocarejobcategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejobcategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocarejobcategoryService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
