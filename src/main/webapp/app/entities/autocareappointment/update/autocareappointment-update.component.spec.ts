import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocareappointmentService } from '../service/autocareappointment.service';
import { IAutocareappointment } from '../autocareappointment.model';
import { AutocareappointmentFormService } from './autocareappointment-form.service';

import { AutocareappointmentUpdateComponent } from './autocareappointment-update.component';

describe('Autocareappointment Management Update Component', () => {
  let comp: AutocareappointmentUpdateComponent;
  let fixture: ComponentFixture<AutocareappointmentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocareappointmentFormService: AutocareappointmentFormService;
  let autocareappointmentService: AutocareappointmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocareappointmentUpdateComponent],
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
      .overrideTemplate(AutocareappointmentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocareappointmentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocareappointmentFormService = TestBed.inject(AutocareappointmentFormService);
    autocareappointmentService = TestBed.inject(AutocareappointmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocareappointment: IAutocareappointment = { id: 456 };

      activatedRoute.data = of({ autocareappointment });
      comp.ngOnInit();

      expect(comp.autocareappointment).toEqual(autocareappointment);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareappointment>>();
      const autocareappointment = { id: 123 };
      jest.spyOn(autocareappointmentFormService, 'getAutocareappointment').mockReturnValue(autocareappointment);
      jest.spyOn(autocareappointmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareappointment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocareappointment }));
      saveSubject.complete();

      // THEN
      expect(autocareappointmentFormService.getAutocareappointment).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocareappointmentService.update).toHaveBeenCalledWith(expect.objectContaining(autocareappointment));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareappointment>>();
      const autocareappointment = { id: 123 };
      jest.spyOn(autocareappointmentFormService, 'getAutocareappointment').mockReturnValue({ id: null });
      jest.spyOn(autocareappointmentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareappointment: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocareappointment }));
      saveSubject.complete();

      // THEN
      expect(autocareappointmentFormService.getAutocareappointment).toHaveBeenCalled();
      expect(autocareappointmentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareappointment>>();
      const autocareappointment = { id: 123 };
      jest.spyOn(autocareappointmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareappointment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocareappointmentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
