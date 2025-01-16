import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { EmpJobcommissionService } from '../service/emp-jobcommission.service';
import { IEmpJobcommission } from '../emp-jobcommission.model';
import { EmpJobcommissionFormService } from './emp-jobcommission-form.service';

import { EmpJobcommissionUpdateComponent } from './emp-jobcommission-update.component';

describe('EmpJobcommission Management Update Component', () => {
  let comp: EmpJobcommissionUpdateComponent;
  let fixture: ComponentFixture<EmpJobcommissionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let empJobcommissionFormService: EmpJobcommissionFormService;
  let empJobcommissionService: EmpJobcommissionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, EmpJobcommissionUpdateComponent],
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
      .overrideTemplate(EmpJobcommissionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EmpJobcommissionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    empJobcommissionFormService = TestBed.inject(EmpJobcommissionFormService);
    empJobcommissionService = TestBed.inject(EmpJobcommissionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const empJobcommission: IEmpJobcommission = { id: 456 };

      activatedRoute.data = of({ empJobcommission });
      comp.ngOnInit();

      expect(comp.empJobcommission).toEqual(empJobcommission);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpJobcommission>>();
      const empJobcommission = { id: 123 };
      jest.spyOn(empJobcommissionFormService, 'getEmpJobcommission').mockReturnValue(empJobcommission);
      jest.spyOn(empJobcommissionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empJobcommission });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: empJobcommission }));
      saveSubject.complete();

      // THEN
      expect(empJobcommissionFormService.getEmpJobcommission).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(empJobcommissionService.update).toHaveBeenCalledWith(expect.objectContaining(empJobcommission));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpJobcommission>>();
      const empJobcommission = { id: 123 };
      jest.spyOn(empJobcommissionFormService, 'getEmpJobcommission').mockReturnValue({ id: null });
      jest.spyOn(empJobcommissionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empJobcommission: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: empJobcommission }));
      saveSubject.complete();

      // THEN
      expect(empJobcommissionFormService.getEmpJobcommission).toHaveBeenCalled();
      expect(empJobcommissionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpJobcommission>>();
      const empJobcommission = { id: 123 };
      jest.spyOn(empJobcommissionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empJobcommission });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(empJobcommissionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
