import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { SalesorderService } from '../service/salesorder.service';
import { ISalesorder } from '../salesorder.model';
import { SalesorderFormService } from './salesorder-form.service';

import { SalesorderUpdateComponent } from './salesorder-update.component';

describe('Salesorder Management Update Component', () => {
  let comp: SalesorderUpdateComponent;
  let fixture: ComponentFixture<SalesorderUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let salesorderFormService: SalesorderFormService;
  let salesorderService: SalesorderService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, SalesorderUpdateComponent],
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
      .overrideTemplate(SalesorderUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SalesorderUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    salesorderFormService = TestBed.inject(SalesorderFormService);
    salesorderService = TestBed.inject(SalesorderService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const salesorder: ISalesorder = { id: 456 };

      activatedRoute.data = of({ salesorder });
      comp.ngOnInit();

      expect(comp.salesorder).toEqual(salesorder);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISalesorder>>();
      const salesorder = { id: 123 };
      jest.spyOn(salesorderFormService, 'getSalesorder').mockReturnValue(salesorder);
      jest.spyOn(salesorderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ salesorder });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: salesorder }));
      saveSubject.complete();

      // THEN
      expect(salesorderFormService.getSalesorder).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(salesorderService.update).toHaveBeenCalledWith(expect.objectContaining(salesorder));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISalesorder>>();
      const salesorder = { id: 123 };
      jest.spyOn(salesorderFormService, 'getSalesorder').mockReturnValue({ id: null });
      jest.spyOn(salesorderService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ salesorder: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: salesorder }));
      saveSubject.complete();

      // THEN
      expect(salesorderFormService.getSalesorder).toHaveBeenCalled();
      expect(salesorderService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISalesorder>>();
      const salesorder = { id: 123 };
      jest.spyOn(salesorderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ salesorder });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(salesorderService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
