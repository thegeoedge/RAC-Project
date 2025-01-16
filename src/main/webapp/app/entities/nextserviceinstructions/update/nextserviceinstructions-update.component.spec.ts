import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { NextserviceinstructionsService } from '../service/nextserviceinstructions.service';
import { INextserviceinstructions } from '../nextserviceinstructions.model';
import { NextserviceinstructionsFormService } from './nextserviceinstructions-form.service';

import { NextserviceinstructionsUpdateComponent } from './nextserviceinstructions-update.component';

describe('Nextserviceinstructions Management Update Component', () => {
  let comp: NextserviceinstructionsUpdateComponent;
  let fixture: ComponentFixture<NextserviceinstructionsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let nextserviceinstructionsFormService: NextserviceinstructionsFormService;
  let nextserviceinstructionsService: NextserviceinstructionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, NextserviceinstructionsUpdateComponent],
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
      .overrideTemplate(NextserviceinstructionsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NextserviceinstructionsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    nextserviceinstructionsFormService = TestBed.inject(NextserviceinstructionsFormService);
    nextserviceinstructionsService = TestBed.inject(NextserviceinstructionsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const nextserviceinstructions: INextserviceinstructions = { id: 456 };

      activatedRoute.data = of({ nextserviceinstructions });
      comp.ngOnInit();

      expect(comp.nextserviceinstructions).toEqual(nextserviceinstructions);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INextserviceinstructions>>();
      const nextserviceinstructions = { id: 123 };
      jest.spyOn(nextserviceinstructionsFormService, 'getNextserviceinstructions').mockReturnValue(nextserviceinstructions);
      jest.spyOn(nextserviceinstructionsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nextserviceinstructions });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: nextserviceinstructions }));
      saveSubject.complete();

      // THEN
      expect(nextserviceinstructionsFormService.getNextserviceinstructions).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(nextserviceinstructionsService.update).toHaveBeenCalledWith(expect.objectContaining(nextserviceinstructions));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INextserviceinstructions>>();
      const nextserviceinstructions = { id: 123 };
      jest.spyOn(nextserviceinstructionsFormService, 'getNextserviceinstructions').mockReturnValue({ id: null });
      jest.spyOn(nextserviceinstructionsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nextserviceinstructions: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: nextserviceinstructions }));
      saveSubject.complete();

      // THEN
      expect(nextserviceinstructionsFormService.getNextserviceinstructions).toHaveBeenCalled();
      expect(nextserviceinstructionsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INextserviceinstructions>>();
      const nextserviceinstructions = { id: 123 };
      jest.spyOn(nextserviceinstructionsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nextserviceinstructions });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(nextserviceinstructionsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
