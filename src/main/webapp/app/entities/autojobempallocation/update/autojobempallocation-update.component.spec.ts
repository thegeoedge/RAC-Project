import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutojobempallocationService } from '../service/autojobempallocation.service';
import { IAutojobempallocation } from '../autojobempallocation.model';
import { AutojobempallocationFormService } from './autojobempallocation-form.service';

import { AutojobempallocationUpdateComponent } from './autojobempallocation-update.component';

describe('Autojobempallocation Management Update Component', () => {
  let comp: AutojobempallocationUpdateComponent;
  let fixture: ComponentFixture<AutojobempallocationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autojobempallocationFormService: AutojobempallocationFormService;
  let autojobempallocationService: AutojobempallocationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutojobempallocationUpdateComponent],
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
      .overrideTemplate(AutojobempallocationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutojobempallocationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autojobempallocationFormService = TestBed.inject(AutojobempallocationFormService);
    autojobempallocationService = TestBed.inject(AutojobempallocationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autojobempallocation: IAutojobempallocation = { id: 456 };

      activatedRoute.data = of({ autojobempallocation });
      comp.ngOnInit();

      expect(comp.autojobempallocation).toEqual(autojobempallocation);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutojobempallocation>>();
      const autojobempallocation = { id: 123 };
      jest.spyOn(autojobempallocationFormService, 'getAutojobempallocation').mockReturnValue(autojobempallocation);
      jest.spyOn(autojobempallocationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autojobempallocation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autojobempallocation }));
      saveSubject.complete();

      // THEN
      expect(autojobempallocationFormService.getAutojobempallocation).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autojobempallocationService.update).toHaveBeenCalledWith(expect.objectContaining(autojobempallocation));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutojobempallocation>>();
      const autojobempallocation = { id: 123 };
      jest.spyOn(autojobempallocationFormService, 'getAutojobempallocation').mockReturnValue({ id: null });
      jest.spyOn(autojobempallocationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autojobempallocation: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autojobempallocation }));
      saveSubject.complete();

      // THEN
      expect(autojobempallocationFormService.getAutojobempallocation).toHaveBeenCalled();
      expect(autojobempallocationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutojobempallocation>>();
      const autojobempallocation = { id: 123 };
      jest.spyOn(autojobempallocationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autojobempallocation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autojobempallocationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
