import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocarejobService } from '../service/autocarejob.service';
import { IAutocarejob } from '../autocarejob.model';
import { AutocarejobFormService } from './autocarejob-form.service';

import { AutocarejobUpdateComponent } from './autocarejob-update.component';

describe('Autocarejob Management Update Component', () => {
  let comp: AutocarejobUpdateComponent;
  let fixture: ComponentFixture<AutocarejobUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocarejobFormService: AutocarejobFormService;
  let autocarejobService: AutocarejobService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocarejobUpdateComponent],
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
      .overrideTemplate(AutocarejobUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocarejobUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocarejobFormService = TestBed.inject(AutocarejobFormService);
    autocarejobService = TestBed.inject(AutocarejobService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocarejob: IAutocarejob = { id: 456 };

      activatedRoute.data = of({ autocarejob });
      comp.ngOnInit();

      expect(comp.autocarejob).toEqual(autocarejob);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejob>>();
      const autocarejob = { id: 123 };
      jest.spyOn(autocarejobFormService, 'getAutocarejob').mockReturnValue(autocarejob);
      jest.spyOn(autocarejobService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejob });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarejob }));
      saveSubject.complete();

      // THEN
      expect(autocarejobFormService.getAutocarejob).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocarejobService.update).toHaveBeenCalledWith(expect.objectContaining(autocarejob));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejob>>();
      const autocarejob = { id: 123 };
      jest.spyOn(autocarejobFormService, 'getAutocarejob').mockReturnValue({ id: null });
      jest.spyOn(autocarejobService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejob: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarejob }));
      saveSubject.complete();

      // THEN
      expect(autocarejobFormService.getAutocarejob).toHaveBeenCalled();
      expect(autocarejobService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarejob>>();
      const autocarejob = { id: 123 };
      jest.spyOn(autocarejobService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarejob });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocarejobService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
