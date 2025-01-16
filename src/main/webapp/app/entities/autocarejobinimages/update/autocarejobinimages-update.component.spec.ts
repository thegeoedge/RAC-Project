import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocarejobinimagesService } from '../service/autocarejobinimages.service';
import { IAutocarejobinimages } from '../autocarejobinimages.model';
import { AutocarejobinimagesFormService } from './autocarejobinimages-form.service';

import { AutocarejobinimagesUpdateComponent } from './autocarejobinimages-update.component';

describe('Autocarejobinimages Management Update Component', () => {
  let comp: AutocarejobinimagesUpdateComponent;
  let fixture: ComponentFixture<AutocarejobinimagesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocarejobinimagesFormService: AutocarejobinimagesFormService;
  let autocarejobinimagesService: AutocarejobinimagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocarejobinimagesUpdateComponent],
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
      .overrideTemplate(AutocarejobinimagesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocarejobinimagesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocarejobinimagesFormService = TestBed.inject(AutocarejobinimagesFormService);
    autocarejobinimagesService = TestBed.inject(AutocarejobinimagesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocarejobinimages: IAutocarejobinimages = { id: 456 };

      activatedRoute.data = of({ autocarejobinimages });
      comp.ngOnInit();

      expect(comp.autocarejobinimages).toEqual(autocarejobinimages);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejobinimages>>();
      const autocarejobinimages = { id: 123 };
      jest.spyOn(autocarejobinimagesFormService, 'getAutocarejobinimages').mockReturnValue(autocarejobinimages);
      jest.spyOn(autocarejobinimagesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejobinimages });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarejobinimages }));
      saveSubject.complete();

      // THEN
      expect(autocarejobinimagesFormService.getAutocarejobinimages).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocarejobinimagesService.update).toHaveBeenCalledWith(expect.objectContaining(autocarejobinimages));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejobinimages>>();
      const autocarejobinimages = { id: 123 };
      jest.spyOn(autocarejobinimagesFormService, 'getAutocarejobinimages').mockReturnValue({ id: null });
      jest.spyOn(autocarejobinimagesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejobinimages: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarejobinimages }));
      saveSubject.complete();

      // THEN
      expect(autocarejobinimagesFormService.getAutocarejobinimages).toHaveBeenCalled();
      expect(autocarejobinimagesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejobinimages>>();
      const autocarejobinimages = { id: 123 };
      jest.spyOn(autocarejobinimagesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejobinimages });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocarejobinimagesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
