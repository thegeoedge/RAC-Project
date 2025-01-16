import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { BankbranchService } from '../service/bankbranch.service';
import { IBankbranch } from '../bankbranch.model';
import { BankbranchFormService } from './bankbranch-form.service';

import { BankbranchUpdateComponent } from './bankbranch-update.component';

describe('Bankbranch Management Update Component', () => {
  let comp: BankbranchUpdateComponent;
  let fixture: ComponentFixture<BankbranchUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let bankbranchFormService: BankbranchFormService;
  let bankbranchService: BankbranchService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, BankbranchUpdateComponent],
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
      .overrideTemplate(BankbranchUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BankbranchUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    bankbranchFormService = TestBed.inject(BankbranchFormService);
    bankbranchService = TestBed.inject(BankbranchService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const bankbranch: IBankbranch = { id: 456 };

      activatedRoute.data = of({ bankbranch });
      comp.ngOnInit();

      expect(comp.bankbranch).toEqual(bankbranch);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBankbranch>>();
      const bankbranch = { id: 123 };
      jest.spyOn(bankbranchFormService, 'getBankbranch').mockReturnValue(bankbranch);
      jest.spyOn(bankbranchService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bankbranch });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bankbranch }));
      saveSubject.complete();

      // THEN
      expect(bankbranchFormService.getBankbranch).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(bankbranchService.update).toHaveBeenCalledWith(expect.objectContaining(bankbranch));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBankbranch>>();
      const bankbranch = { id: 123 };
      jest.spyOn(bankbranchFormService, 'getBankbranch').mockReturnValue({ id: null });
      jest.spyOn(bankbranchService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bankbranch: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bankbranch }));
      saveSubject.complete();

      // THEN
      expect(bankbranchFormService.getBankbranch).toHaveBeenCalled();
      expect(bankbranchService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBankbranch>>();
      const bankbranch = { id: 123 };
      jest.spyOn(bankbranchService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bankbranch });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(bankbranchService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
