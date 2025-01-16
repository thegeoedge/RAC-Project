import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { WorkshopworklistService } from '../service/workshopworklist.service';
import { IWorkshopworklist } from '../workshopworklist.model';
import { WorkshopworklistFormService } from './workshopworklist-form.service';

import { WorkshopworklistUpdateComponent } from './workshopworklist-update.component';

describe('Workshopworklist Management Update Component', () => {
  let comp: WorkshopworklistUpdateComponent;
  let fixture: ComponentFixture<WorkshopworklistUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let workshopworklistFormService: WorkshopworklistFormService;
  let workshopworklistService: WorkshopworklistService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, WorkshopworklistUpdateComponent],
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
      .overrideTemplate(WorkshopworklistUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(WorkshopworklistUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    workshopworklistFormService = TestBed.inject(WorkshopworklistFormService);
    workshopworklistService = TestBed.inject(WorkshopworklistService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const workshopworklist: IWorkshopworklist = { id: 456 };

      activatedRoute.data = of({ workshopworklist });
      comp.ngOnInit();

      expect(comp.workshopworklist).toEqual(workshopworklist);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkshopworklist>>();
      const workshopworklist = { id: 123 };
      jest.spyOn(workshopworklistFormService, 'getWorkshopworklist').mockReturnValue(workshopworklist);
      jest.spyOn(workshopworklistService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workshopworklist });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: workshopworklist }));
      saveSubject.complete();

      // THEN
      expect(workshopworklistFormService.getWorkshopworklist).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(workshopworklistService.update).toHaveBeenCalledWith(expect.objectContaining(workshopworklist));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkshopworklist>>();
      const workshopworklist = { id: 123 };
      jest.spyOn(workshopworklistFormService, 'getWorkshopworklist').mockReturnValue({ id: null });
      jest.spyOn(workshopworklistService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workshopworklist: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: workshopworklist }));
      saveSubject.complete();

      // THEN
      expect(workshopworklistFormService.getWorkshopworklist).toHaveBeenCalled();
      expect(workshopworklistService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IWorkshopworklist>>();
      const workshopworklist = { id: 123 };
      jest.spyOn(workshopworklistService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ workshopworklist });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(workshopworklistService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
