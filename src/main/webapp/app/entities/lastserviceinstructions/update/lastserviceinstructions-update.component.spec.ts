import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { LastserviceinstructionsService } from '../service/lastserviceinstructions.service';
import { ILastserviceinstructions } from '../lastserviceinstructions.model';
import { LastserviceinstructionsFormService } from './lastserviceinstructions-form.service';

import { LastserviceinstructionsUpdateComponent } from './lastserviceinstructions-update.component';

describe('Lastserviceinstructions Management Update Component', () => {
  let comp: LastserviceinstructionsUpdateComponent;
  let fixture: ComponentFixture<LastserviceinstructionsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let lastserviceinstructionsFormService: LastserviceinstructionsFormService;
  let lastserviceinstructionsService: LastserviceinstructionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, LastserviceinstructionsUpdateComponent],
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
      .overrideTemplate(LastserviceinstructionsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LastserviceinstructionsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    lastserviceinstructionsFormService = TestBed.inject(LastserviceinstructionsFormService);
    lastserviceinstructionsService = TestBed.inject(LastserviceinstructionsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const lastserviceinstructions: ILastserviceinstructions = { id: 456 };

      activatedRoute.data = of({ lastserviceinstructions });
      comp.ngOnInit();

      expect(comp.lastserviceinstructions).toEqual(lastserviceinstructions);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILastserviceinstructions>>();
      const lastserviceinstructions = { id: 123 };
      jest.spyOn(lastserviceinstructionsFormService, 'getLastserviceinstructions').mockReturnValue(lastserviceinstructions);
      jest.spyOn(lastserviceinstructionsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ lastserviceinstructions });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: lastserviceinstructions }));
      saveSubject.complete();

      // THEN
      expect(lastserviceinstructionsFormService.getLastserviceinstructions).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(lastserviceinstructionsService.update).toHaveBeenCalledWith(expect.objectContaining(lastserviceinstructions));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILastserviceinstructions>>();
      const lastserviceinstructions = { id: 123 };
      jest.spyOn(lastserviceinstructionsFormService, 'getLastserviceinstructions').mockReturnValue({ id: null });
      jest.spyOn(lastserviceinstructionsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ lastserviceinstructions: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: lastserviceinstructions }));
      saveSubject.complete();

      // THEN
      expect(lastserviceinstructionsFormService.getLastserviceinstructions).toHaveBeenCalled();
      expect(lastserviceinstructionsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILastserviceinstructions>>();
      const lastserviceinstructions = { id: 123 };
      jest.spyOn(lastserviceinstructionsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ lastserviceinstructions });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(lastserviceinstructionsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
