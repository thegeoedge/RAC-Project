import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocareservicemillagesService } from '../service/autocareservicemillages.service';
import { IAutocareservicemillages } from '../autocareservicemillages.model';
import { AutocareservicemillagesFormService } from './autocareservicemillages-form.service';

import { AutocareservicemillagesUpdateComponent } from './autocareservicemillages-update.component';

describe('Autocareservicemillages Management Update Component', () => {
  let comp: AutocareservicemillagesUpdateComponent;
  let fixture: ComponentFixture<AutocareservicemillagesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocareservicemillagesFormService: AutocareservicemillagesFormService;
  let autocareservicemillagesService: AutocareservicemillagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocareservicemillagesUpdateComponent],
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
      .overrideTemplate(AutocareservicemillagesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocareservicemillagesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocareservicemillagesFormService = TestBed.inject(AutocareservicemillagesFormService);
    autocareservicemillagesService = TestBed.inject(AutocareservicemillagesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocareservicemillages: IAutocareservicemillages = { id: 456 };

      activatedRoute.data = of({ autocareservicemillages });
      comp.ngOnInit();

      expect(comp.autocareservicemillages).toEqual(autocareservicemillages);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareservicemillages>>();
      const autocareservicemillages = { id: 123 };
      jest.spyOn(autocareservicemillagesFormService, 'getAutocareservicemillages').mockReturnValue(autocareservicemillages);
      jest.spyOn(autocareservicemillagesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareservicemillages });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocareservicemillages }));
      saveSubject.complete();

      // THEN
      expect(autocareservicemillagesFormService.getAutocareservicemillages).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocareservicemillagesService.update).toHaveBeenCalledWith(expect.objectContaining(autocareservicemillages));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareservicemillages>>();
      const autocareservicemillages = { id: 123 };
      jest.spyOn(autocareservicemillagesFormService, 'getAutocareservicemillages').mockReturnValue({ id: null });
      jest.spyOn(autocareservicemillagesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareservicemillages: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocareservicemillages }));
      saveSubject.complete();

      // THEN
      expect(autocareservicemillagesFormService.getAutocareservicemillages).toHaveBeenCalled();
      expect(autocareservicemillagesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareservicemillages>>();
      const autocareservicemillages = { id: 123 };
      jest.spyOn(autocareservicemillagesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareservicemillages });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocareservicemillagesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
