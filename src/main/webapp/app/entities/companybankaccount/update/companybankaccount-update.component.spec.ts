import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { CompanybankaccountService } from '../service/companybankaccount.service';
import { ICompanybankaccount } from '../companybankaccount.model';
import { CompanybankaccountFormService } from './companybankaccount-form.service';

import { CompanybankaccountUpdateComponent } from './companybankaccount-update.component';

describe('Companybankaccount Management Update Component', () => {
  let comp: CompanybankaccountUpdateComponent;
  let fixture: ComponentFixture<CompanybankaccountUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let companybankaccountFormService: CompanybankaccountFormService;
  let companybankaccountService: CompanybankaccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, CompanybankaccountUpdateComponent],
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
      .overrideTemplate(CompanybankaccountUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CompanybankaccountUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    companybankaccountFormService = TestBed.inject(CompanybankaccountFormService);
    companybankaccountService = TestBed.inject(CompanybankaccountService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const companybankaccount: ICompanybankaccount = { id: 456 };

      activatedRoute.data = of({ companybankaccount });
      comp.ngOnInit();

      expect(comp.companybankaccount).toEqual(companybankaccount);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICompanybankaccount>>();
      const companybankaccount = { id: 123 };
      jest.spyOn(companybankaccountFormService, 'getCompanybankaccount').mockReturnValue(companybankaccount);
      jest.spyOn(companybankaccountService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ companybankaccount });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: companybankaccount }));
      saveSubject.complete();

      // THEN
      expect(companybankaccountFormService.getCompanybankaccount).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(companybankaccountService.update).toHaveBeenCalledWith(expect.objectContaining(companybankaccount));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICompanybankaccount>>();
      const companybankaccount = { id: 123 };
      jest.spyOn(companybankaccountFormService, 'getCompanybankaccount').mockReturnValue({ id: null });
      jest.spyOn(companybankaccountService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ companybankaccount: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: companybankaccount }));
      saveSubject.complete();

      // THEN
      expect(companybankaccountFormService.getCompanybankaccount).toHaveBeenCalled();
      expect(companybankaccountService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICompanybankaccount>>();
      const companybankaccount = { id: 123 };
      jest.spyOn(companybankaccountService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ companybankaccount });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(companybankaccountService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
