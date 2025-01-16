import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocareappointmenttypeService } from '../service/autocareappointmenttype.service';
import { IAutocareappointmenttype } from '../autocareappointmenttype.model';
import { AutocareappointmenttypeFormService } from './autocareappointmenttype-form.service';

import { AutocareappointmenttypeUpdateComponent } from './autocareappointmenttype-update.component';

describe('Autocareappointmenttype Management Update Component', () => {
  let comp: AutocareappointmenttypeUpdateComponent;
  let fixture: ComponentFixture<AutocareappointmenttypeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocareappointmenttypeFormService: AutocareappointmenttypeFormService;
  let autocareappointmenttypeService: AutocareappointmenttypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocareappointmenttypeUpdateComponent],
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
      .overrideTemplate(AutocareappointmenttypeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocareappointmenttypeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocareappointmenttypeFormService = TestBed.inject(AutocareappointmenttypeFormService);
    autocareappointmenttypeService = TestBed.inject(AutocareappointmenttypeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocareappointmenttype: IAutocareappointmenttype = { id: 456 };

      activatedRoute.data = of({ autocareappointmenttype });
      comp.ngOnInit();

      expect(comp.autocareappointmenttype).toEqual(autocareappointmenttype);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareappointmenttype>>();
      const autocareappointmenttype = { id: 123 };
      jest.spyOn(autocareappointmenttypeFormService, 'getAutocareappointmenttype').mockReturnValue(autocareappointmenttype);
      jest.spyOn(autocareappointmenttypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareappointmenttype });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocareappointmenttype }));
      saveSubject.complete();

      // THEN
      expect(autocareappointmenttypeFormService.getAutocareappointmenttype).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocareappointmenttypeService.update).toHaveBeenCalledWith(expect.objectContaining(autocareappointmenttype));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareappointmenttype>>();
      const autocareappointmenttype = { id: 123 };
      jest.spyOn(autocareappointmenttypeFormService, 'getAutocareappointmenttype').mockReturnValue({ id: null });
      jest.spyOn(autocareappointmenttypeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareappointmenttype: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocareappointmenttype }));
      saveSubject.complete();

      // THEN
      expect(autocareappointmenttypeFormService.getAutocareappointmenttype).toHaveBeenCalled();
      expect(autocareappointmenttypeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocareappointmenttype>>();
      const autocareappointmenttype = { id: 123 };
      jest.spyOn(autocareappointmenttypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocareappointmenttype });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocareappointmenttypeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
