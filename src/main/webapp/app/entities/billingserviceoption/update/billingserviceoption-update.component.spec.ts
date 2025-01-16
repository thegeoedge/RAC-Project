import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { BillingserviceoptionService } from '../service/billingserviceoption.service';
import { IBillingserviceoption } from '../billingserviceoption.model';
import { BillingserviceoptionFormService } from './billingserviceoption-form.service';

import { BillingserviceoptionUpdateComponent } from './billingserviceoption-update.component';

describe('Billingserviceoption Management Update Component', () => {
  let comp: BillingserviceoptionUpdateComponent;
  let fixture: ComponentFixture<BillingserviceoptionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let billingserviceoptionFormService: BillingserviceoptionFormService;
  let billingserviceoptionService: BillingserviceoptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, BillingserviceoptionUpdateComponent],
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
      .overrideTemplate(BillingserviceoptionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BillingserviceoptionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    billingserviceoptionFormService = TestBed.inject(BillingserviceoptionFormService);
    billingserviceoptionService = TestBed.inject(BillingserviceoptionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const billingserviceoption: IBillingserviceoption = { id: 456 };

      activatedRoute.data = of({ billingserviceoption });
      comp.ngOnInit();

      expect(comp.billingserviceoption).toEqual(billingserviceoption);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBillingserviceoption>>();
      const billingserviceoption = { id: 123 };
      jest.spyOn(billingserviceoptionFormService, 'getBillingserviceoption').mockReturnValue(billingserviceoption);
      jest.spyOn(billingserviceoptionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ billingserviceoption });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: billingserviceoption }));
      saveSubject.complete();

      // THEN
      expect(billingserviceoptionFormService.getBillingserviceoption).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(billingserviceoptionService.update).toHaveBeenCalledWith(expect.objectContaining(billingserviceoption));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBillingserviceoption>>();
      const billingserviceoption = { id: 123 };
      jest.spyOn(billingserviceoptionFormService, 'getBillingserviceoption').mockReturnValue({ id: null });
      jest.spyOn(billingserviceoptionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ billingserviceoption: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: billingserviceoption }));
      saveSubject.complete();

      // THEN
      expect(billingserviceoptionFormService.getBillingserviceoption).toHaveBeenCalled();
      expect(billingserviceoptionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBillingserviceoption>>();
      const billingserviceoption = { id: 123 };
      jest.spyOn(billingserviceoptionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ billingserviceoption });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(billingserviceoptionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
