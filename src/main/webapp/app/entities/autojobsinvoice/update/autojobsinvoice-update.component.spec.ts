import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutojobsinvoiceService } from '../service/autojobsinvoice.service';
import { IAutojobsinvoice } from '../autojobsinvoice.model';
import { AutojobsinvoiceFormService } from './autojobsinvoice-form.service';

import { AutojobsinvoiceUpdateComponent } from './autojobsinvoice-update.component';

describe('Autojobsinvoice Management Update Component', () => {
  let comp: AutojobsinvoiceUpdateComponent;
  let fixture: ComponentFixture<AutojobsinvoiceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autojobsinvoiceFormService: AutojobsinvoiceFormService;
  let autojobsinvoiceService: AutojobsinvoiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutojobsinvoiceUpdateComponent],
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
      .overrideTemplate(AutojobsinvoiceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutojobsinvoiceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autojobsinvoiceFormService = TestBed.inject(AutojobsinvoiceFormService);
    autojobsinvoiceService = TestBed.inject(AutojobsinvoiceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autojobsinvoice: IAutojobsinvoice = { id: 456 };

      activatedRoute.data = of({ autojobsinvoice });
      comp.ngOnInit();

      expect(comp.autojobsinvoice).toEqual(autojobsinvoice);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutojobsinvoice>>();
      const autojobsinvoice = { id: 123 };
      jest.spyOn(autojobsinvoiceFormService, 'getAutojobsinvoice').mockReturnValue(autojobsinvoice);
      jest.spyOn(autojobsinvoiceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autojobsinvoice });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autojobsinvoice }));
      saveSubject.complete();

      // THEN
      expect(autojobsinvoiceFormService.getAutojobsinvoice).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autojobsinvoiceService.update).toHaveBeenCalledWith(expect.objectContaining(autojobsinvoice));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutojobsinvoice>>();
      const autojobsinvoice = { id: 123 };
      jest.spyOn(autojobsinvoiceFormService, 'getAutojobsinvoice').mockReturnValue({ id: null });
      jest.spyOn(autojobsinvoiceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autojobsinvoice: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autojobsinvoice }));
      saveSubject.complete();

      // THEN
      expect(autojobsinvoiceFormService.getAutojobsinvoice).toHaveBeenCalled();
      expect(autojobsinvoiceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutojobsinvoice>>();
      const autojobsinvoice = { id: 123 };
      jest.spyOn(autojobsinvoiceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autojobsinvoice });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autojobsinvoiceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
