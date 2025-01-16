import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { PaymenttermService } from '../service/paymentterm.service';
import { IPaymentterm } from '../paymentterm.model';
import { PaymenttermFormService } from './paymentterm-form.service';

import { PaymenttermUpdateComponent } from './paymentterm-update.component';

describe('Paymentterm Management Update Component', () => {
  let comp: PaymenttermUpdateComponent;
  let fixture: ComponentFixture<PaymenttermUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let paymenttermFormService: PaymenttermFormService;
  let paymenttermService: PaymenttermService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, PaymenttermUpdateComponent],
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
      .overrideTemplate(PaymenttermUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PaymenttermUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    paymenttermFormService = TestBed.inject(PaymenttermFormService);
    paymenttermService = TestBed.inject(PaymenttermService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const paymentterm: IPaymentterm = { id: 456 };

      activatedRoute.data = of({ paymentterm });
      comp.ngOnInit();

      expect(comp.paymentterm).toEqual(paymentterm);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPaymentterm>>();
      const paymentterm = { id: 123 };
      jest.spyOn(paymenttermFormService, 'getPaymentterm').mockReturnValue(paymentterm);
      jest.spyOn(paymenttermService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ paymentterm });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: paymentterm }));
      saveSubject.complete();

      // THEN
      expect(paymenttermFormService.getPaymentterm).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(paymenttermService.update).toHaveBeenCalledWith(expect.objectContaining(paymentterm));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPaymentterm>>();
      const paymentterm = { id: 123 };
      jest.spyOn(paymenttermFormService, 'getPaymentterm').mockReturnValue({ id: null });
      jest.spyOn(paymenttermService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ paymentterm: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: paymentterm }));
      saveSubject.complete();

      // THEN
      expect(paymenttermFormService.getPaymentterm).toHaveBeenCalled();
      expect(paymenttermService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPaymentterm>>();
      const paymentterm = { id: 123 };
      jest.spyOn(paymenttermService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ paymentterm });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(paymenttermService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
