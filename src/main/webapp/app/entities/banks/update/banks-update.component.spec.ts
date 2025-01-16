import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { BanksService } from '../service/banks.service';
import { IBanks } from '../banks.model';
import { BanksFormService } from './banks-form.service';

import { BanksUpdateComponent } from './banks-update.component';

describe('Banks Management Update Component', () => {
  let comp: BanksUpdateComponent;
  let fixture: ComponentFixture<BanksUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let banksFormService: BanksFormService;
  let banksService: BanksService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, BanksUpdateComponent],
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
      .overrideTemplate(BanksUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BanksUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    banksFormService = TestBed.inject(BanksFormService);
    banksService = TestBed.inject(BanksService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const banks: IBanks = { id: 456 };

      activatedRoute.data = of({ banks });
      comp.ngOnInit();

      expect(comp.banks).toEqual(banks);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBanks>>();
      const banks = { id: 123 };
      jest.spyOn(banksFormService, 'getBanks').mockReturnValue(banks);
      jest.spyOn(banksService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ banks });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: banks }));
      saveSubject.complete();

      // THEN
      expect(banksFormService.getBanks).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(banksService.update).toHaveBeenCalledWith(expect.objectContaining(banks));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBanks>>();
      const banks = { id: 123 };
      jest.spyOn(banksFormService, 'getBanks').mockReturnValue({ id: null });
      jest.spyOn(banksService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ banks: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: banks }));
      saveSubject.complete();

      // THEN
      expect(banksFormService.getBanks).toHaveBeenCalled();
      expect(banksService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBanks>>();
      const banks = { id: 123 };
      jest.spyOn(banksService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ banks });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(banksService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
