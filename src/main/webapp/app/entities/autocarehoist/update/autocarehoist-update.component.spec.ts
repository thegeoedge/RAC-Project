import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocarehoistService } from '../service/autocarehoist.service';
import { IAutocarehoist } from '../autocarehoist.model';
import { AutocarehoistFormService } from './autocarehoist-form.service';

import { AutocarehoistUpdateComponent } from './autocarehoist-update.component';

describe('Autocarehoist Management Update Component', () => {
  let comp: AutocarehoistUpdateComponent;
  let fixture: ComponentFixture<AutocarehoistUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocarehoistFormService: AutocarehoistFormService;
  let autocarehoistService: AutocarehoistService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocarehoistUpdateComponent],
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
      .overrideTemplate(AutocarehoistUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocarehoistUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocarehoistFormService = TestBed.inject(AutocarehoistFormService);
    autocarehoistService = TestBed.inject(AutocarehoistService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocarehoist: IAutocarehoist = { id: 456 };

      activatedRoute.data = of({ autocarehoist });
      comp.ngOnInit();

      expect(comp.autocarehoist).toEqual(autocarehoist);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarehoist>>();
      const autocarehoist = { id: 123 };
      jest.spyOn(autocarehoistFormService, 'getAutocarehoist').mockReturnValue(autocarehoist);
      jest.spyOn(autocarehoistService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarehoist });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarehoist }));
      saveSubject.complete();

      // THEN
      expect(autocarehoistFormService.getAutocarehoist).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocarehoistService.update).toHaveBeenCalledWith(expect.objectContaining(autocarehoist));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarehoist>>();
      const autocarehoist = { id: 123 };
      jest.spyOn(autocarehoistFormService, 'getAutocarehoist').mockReturnValue({ id: null });
      jest.spyOn(autocarehoistService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarehoist: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarehoist }));
      saveSubject.complete();

      // THEN
      expect(autocarehoistFormService.getAutocarehoist).toHaveBeenCalled();
      expect(autocarehoistService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarehoist>>();
      const autocarehoist = { id: 123 };
      jest.spyOn(autocarehoistService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarehoist });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocarehoistService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
