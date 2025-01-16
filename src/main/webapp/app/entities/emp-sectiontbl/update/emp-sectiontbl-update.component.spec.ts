import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { EmpSectiontblService } from '../service/emp-sectiontbl.service';
import { IEmpSectiontbl } from '../emp-sectiontbl.model';
import { EmpSectiontblFormService } from './emp-sectiontbl-form.service';

import { EmpSectiontblUpdateComponent } from './emp-sectiontbl-update.component';

describe('EmpSectiontbl Management Update Component', () => {
  let comp: EmpSectiontblUpdateComponent;
  let fixture: ComponentFixture<EmpSectiontblUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let empSectiontblFormService: EmpSectiontblFormService;
  let empSectiontblService: EmpSectiontblService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, EmpSectiontblUpdateComponent],
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
      .overrideTemplate(EmpSectiontblUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EmpSectiontblUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    empSectiontblFormService = TestBed.inject(EmpSectiontblFormService);
    empSectiontblService = TestBed.inject(EmpSectiontblService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const empSectiontbl: IEmpSectiontbl = { id: 456 };

      activatedRoute.data = of({ empSectiontbl });
      comp.ngOnInit();

      expect(comp.empSectiontbl).toEqual(empSectiontbl);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpSectiontbl>>();
      const empSectiontbl = { id: 123 };
      jest.spyOn(empSectiontblFormService, 'getEmpSectiontbl').mockReturnValue(empSectiontbl);
      jest.spyOn(empSectiontblService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empSectiontbl });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: empSectiontbl }));
      saveSubject.complete();

      // THEN
      expect(empSectiontblFormService.getEmpSectiontbl).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(empSectiontblService.update).toHaveBeenCalledWith(expect.objectContaining(empSectiontbl));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpSectiontbl>>();
      const empSectiontbl = { id: 123 };
      jest.spyOn(empSectiontblFormService, 'getEmpSectiontbl').mockReturnValue({ id: null });
      jest.spyOn(empSectiontblService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empSectiontbl: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: empSectiontbl }));
      saveSubject.complete();

      // THEN
      expect(empSectiontblFormService.getEmpSectiontbl).toHaveBeenCalled();
      expect(empSectiontblService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpSectiontbl>>();
      const empSectiontbl = { id: 123 };
      jest.spyOn(empSectiontblService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empSectiontbl });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(empSectiontblService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
