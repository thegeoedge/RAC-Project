import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocarecompanyService } from '../service/autocarecompany.service';
import { IAutocarecompany } from '../autocarecompany.model';
import { AutocarecompanyFormService } from './autocarecompany-form.service';

import { AutocarecompanyUpdateComponent } from './autocarecompany-update.component';

describe('Autocarecompany Management Update Component', () => {
  let comp: AutocarecompanyUpdateComponent;
  let fixture: ComponentFixture<AutocarecompanyUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocarecompanyFormService: AutocarecompanyFormService;
  let autocarecompanyService: AutocarecompanyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocarecompanyUpdateComponent],
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
      .overrideTemplate(AutocarecompanyUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocarecompanyUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocarecompanyFormService = TestBed.inject(AutocarecompanyFormService);
    autocarecompanyService = TestBed.inject(AutocarecompanyService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocarecompany: IAutocarecompany = { id: 456 };

      activatedRoute.data = of({ autocarecompany });
      comp.ngOnInit();

      expect(comp.autocarecompany).toEqual(autocarecompany);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarecompany>>();
      const autocarecompany = { id: 123 };
      jest.spyOn(autocarecompanyFormService, 'getAutocarecompany').mockReturnValue(autocarecompany);
      jest.spyOn(autocarecompanyService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarecompany });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarecompany }));
      saveSubject.complete();

      // THEN
      expect(autocarecompanyFormService.getAutocarecompany).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocarecompanyService.update).toHaveBeenCalledWith(expect.objectContaining(autocarecompany));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarecompany>>();
      const autocarecompany = { id: 123 };
      jest.spyOn(autocarecompanyFormService, 'getAutocarecompany').mockReturnValue({ id: null });
      jest.spyOn(autocarecompanyService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarecompany: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarecompany }));
      saveSubject.complete();

      // THEN
      expect(autocarecompanyFormService.getAutocarecompany).toHaveBeenCalled();
      expect(autocarecompanyService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarecompany>>();
      const autocarecompany = { id: 123 };
      jest.spyOn(autocarecompanyService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarecompany });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocarecompanyService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
