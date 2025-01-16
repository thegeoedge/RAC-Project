import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { BillingserviceoptionvaluesService } from '../service/billingserviceoptionvalues.service';
import { IBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';
import { BillingserviceoptionvaluesFormService } from './billingserviceoptionvalues-form.service';

import { BillingserviceoptionvaluesUpdateComponent } from './billingserviceoptionvalues-update.component';

describe('Billingserviceoptionvalues Management Update Component', () => {
  let comp: BillingserviceoptionvaluesUpdateComponent;
  let fixture: ComponentFixture<BillingserviceoptionvaluesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let billingserviceoptionvaluesFormService: BillingserviceoptionvaluesFormService;
  let billingserviceoptionvaluesService: BillingserviceoptionvaluesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, BillingserviceoptionvaluesUpdateComponent],
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
      .overrideTemplate(BillingserviceoptionvaluesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BillingserviceoptionvaluesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    billingserviceoptionvaluesFormService = TestBed.inject(BillingserviceoptionvaluesFormService);
    billingserviceoptionvaluesService = TestBed.inject(BillingserviceoptionvaluesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const billingserviceoptionvalues: IBillingserviceoptionvalues = { id: 456 };

      activatedRoute.data = of({ billingserviceoptionvalues });
      comp.ngOnInit();

      expect(comp.billingserviceoptionvalues).toEqual(billingserviceoptionvalues);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBillingserviceoptionvalues>>();
      const billingserviceoptionvalues = { id: 123 };
      jest.spyOn(billingserviceoptionvaluesFormService, 'getBillingserviceoptionvalues').mockReturnValue(billingserviceoptionvalues);
      jest.spyOn(billingserviceoptionvaluesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ billingserviceoptionvalues });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: billingserviceoptionvalues }));
      saveSubject.complete();

      // THEN
      expect(billingserviceoptionvaluesFormService.getBillingserviceoptionvalues).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(billingserviceoptionvaluesService.update).toHaveBeenCalledWith(expect.objectContaining(billingserviceoptionvalues));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBillingserviceoptionvalues>>();
      const billingserviceoptionvalues = { id: 123 };
      jest.spyOn(billingserviceoptionvaluesFormService, 'getBillingserviceoptionvalues').mockReturnValue({ id: null });
      jest.spyOn(billingserviceoptionvaluesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ billingserviceoptionvalues: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: billingserviceoptionvalues }));
      saveSubject.complete();

      // THEN
      expect(billingserviceoptionvaluesFormService.getBillingserviceoptionvalues).toHaveBeenCalled();
      expect(billingserviceoptionvaluesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBillingserviceoptionvalues>>();
      const billingserviceoptionvalues = { id: 123 };
      jest.spyOn(billingserviceoptionvaluesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ billingserviceoptionvalues });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(billingserviceoptionvaluesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
