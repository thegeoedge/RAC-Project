import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { TaxesService } from '../service/taxes.service';
import { ITaxes } from '../taxes.model';
import { TaxesFormService } from './taxes-form.service';

import { TaxesUpdateComponent } from './taxes-update.component';

describe('Taxes Management Update Component', () => {
  let comp: TaxesUpdateComponent;
  let fixture: ComponentFixture<TaxesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let taxesFormService: TaxesFormService;
  let taxesService: TaxesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, TaxesUpdateComponent],
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
      .overrideTemplate(TaxesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TaxesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    taxesFormService = TestBed.inject(TaxesFormService);
    taxesService = TestBed.inject(TaxesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const taxes: ITaxes = { id: 456 };

      activatedRoute.data = of({ taxes });
      comp.ngOnInit();

      expect(comp.taxes).toEqual(taxes);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaxes>>();
      const taxes = { id: 123 };
      jest.spyOn(taxesFormService, 'getTaxes').mockReturnValue(taxes);
      jest.spyOn(taxesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taxes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: taxes }));
      saveSubject.complete();

      // THEN
      expect(taxesFormService.getTaxes).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(taxesService.update).toHaveBeenCalledWith(expect.objectContaining(taxes));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaxes>>();
      const taxes = { id: 123 };
      jest.spyOn(taxesFormService, 'getTaxes').mockReturnValue({ id: null });
      jest.spyOn(taxesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taxes: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: taxes }));
      saveSubject.complete();

      // THEN
      expect(taxesFormService.getTaxes).toHaveBeenCalled();
      expect(taxesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaxes>>();
      const taxes = { id: 123 };
      jest.spyOn(taxesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taxes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(taxesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
