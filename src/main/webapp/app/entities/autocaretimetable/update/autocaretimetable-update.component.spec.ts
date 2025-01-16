import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocaretimetableService } from '../service/autocaretimetable.service';
import { IAutocaretimetable } from '../autocaretimetable.model';
import { AutocaretimetableFormService } from './autocaretimetable-form.service';

import { AutocaretimetableUpdateComponent } from './autocaretimetable-update.component';

describe('Autocaretimetable Management Update Component', () => {
  let comp: AutocaretimetableUpdateComponent;
  let fixture: ComponentFixture<AutocaretimetableUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocaretimetableFormService: AutocaretimetableFormService;
  let autocaretimetableService: AutocaretimetableService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocaretimetableUpdateComponent],
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
      .overrideTemplate(AutocaretimetableUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocaretimetableUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocaretimetableFormService = TestBed.inject(AutocaretimetableFormService);
    autocaretimetableService = TestBed.inject(AutocaretimetableService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocaretimetable: IAutocaretimetable = { id: 456 };

      activatedRoute.data = of({ autocaretimetable });
      comp.ngOnInit();

      expect(comp.autocaretimetable).toEqual(autocaretimetable);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocaretimetable>>();
      const autocaretimetable = { id: 123 };
      jest.spyOn(autocaretimetableFormService, 'getAutocaretimetable').mockReturnValue(autocaretimetable);
      jest.spyOn(autocaretimetableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocaretimetable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocaretimetable }));
      saveSubject.complete();

      // THEN
      expect(autocaretimetableFormService.getAutocaretimetable).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocaretimetableService.update).toHaveBeenCalledWith(expect.objectContaining(autocaretimetable));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocaretimetable>>();
      const autocaretimetable = { id: 123 };
      jest.spyOn(autocaretimetableFormService, 'getAutocaretimetable').mockReturnValue({ id: null });
      jest.spyOn(autocaretimetableService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocaretimetable: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocaretimetable }));
      saveSubject.complete();

      // THEN
      expect(autocaretimetableFormService.getAutocaretimetable).toHaveBeenCalled();
      expect(autocaretimetableService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocaretimetable>>();
      const autocaretimetable = { id: 123 };
      jest.spyOn(autocaretimetableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocaretimetable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocaretimetableService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
