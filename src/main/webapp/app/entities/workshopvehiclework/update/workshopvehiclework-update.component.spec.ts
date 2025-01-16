import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { WorkshopvehicleworkService } from '../service/workshopvehiclework.service';
import { IWorkshopvehiclework } from '../workshopvehiclework.model';
import { WorkshopvehicleworkFormService } from './workshopvehiclework-form.service';

import { WorkshopvehicleworkUpdateComponent } from './workshopvehiclework-update.component';

describe('Workshopvehiclework Management Update Component', () => {
  let comp: WorkshopvehicleworkUpdateComponent;
  let fixture: ComponentFixture<WorkshopvehicleworkUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let workshopvehicleworkFormService: WorkshopvehicleworkFormService;
  let workshopvehicleworkService: WorkshopvehicleworkService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, WorkshopvehicleworkUpdateComponent],
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
      .overrideTemplate(WorkshopvehicleworkUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(WorkshopvehicleworkUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    workshopvehicleworkFormService = TestBed.inject(WorkshopvehicleworkFormService);
    workshopvehicleworkService = TestBed.inject(WorkshopvehicleworkService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const workshopvehiclework: IWorkshopvehiclework = { id: 456 };

      activatedRoute.data = of({ workshopvehiclework });
      comp.ngOnInit();

      expect(comp.workshopvehiclework).toEqual(workshopvehiclework);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkshopvehiclework>>();
      const workshopvehiclework = { id: 123 };
      jest.spyOn(workshopvehicleworkFormService, 'getWorkshopvehiclework').mockReturnValue(workshopvehiclework);
      jest.spyOn(workshopvehicleworkService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workshopvehiclework });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: workshopvehiclework }));
      saveSubject.complete();

      // THEN
      expect(workshopvehicleworkFormService.getWorkshopvehiclework).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(workshopvehicleworkService.update).toHaveBeenCalledWith(expect.objectContaining(workshopvehiclework));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkshopvehiclework>>();
      const workshopvehiclework = { id: 123 };
      jest.spyOn(workshopvehicleworkFormService, 'getWorkshopvehiclework').mockReturnValue({ id: null });
      jest.spyOn(workshopvehicleworkService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workshopvehiclework: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: workshopvehiclework }));
      saveSubject.complete();

      // THEN
      expect(workshopvehicleworkFormService.getWorkshopvehiclework).toHaveBeenCalled();
      expect(workshopvehicleworkService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkshopvehiclework>>();
      const workshopvehiclework = { id: 123 };
      jest.spyOn(workshopvehicleworkService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workshopvehiclework });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(workshopvehicleworkService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
