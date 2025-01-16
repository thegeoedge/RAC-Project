import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AutocarecancelitemoptService } from '../service/autocarecancelitemopt.service';
import { IAutocarecancelitemopt } from '../autocarecancelitemopt.model';
import { AutocarecancelitemoptFormService } from './autocarecancelitemopt-form.service';

import { AutocarecancelitemoptUpdateComponent } from './autocarecancelitemopt-update.component';

describe('Autocarecancelitemopt Management Update Component', () => {
  let comp: AutocarecancelitemoptUpdateComponent;
  let fixture: ComponentFixture<AutocarecancelitemoptUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autocarecancelitemoptFormService: AutocarecancelitemoptFormService;
  let autocarecancelitemoptService: AutocarecancelitemoptService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AutocarecancelitemoptUpdateComponent],
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
      .overrideTemplate(AutocarecancelitemoptUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutocarecancelitemoptUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autocarecancelitemoptFormService = TestBed.inject(AutocarecancelitemoptFormService);
    autocarecancelitemoptService = TestBed.inject(AutocarecancelitemoptService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autocarecancelitemopt: IAutocarecancelitemopt = { id: 456 };

      activatedRoute.data = of({ autocarecancelitemopt });
      comp.ngOnInit();

      expect(comp.autocarecancelitemopt).toEqual(autocarecancelitemopt);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarecancelitemopt>>();
      const autocarecancelitemopt = { id: 123 };
      jest.spyOn(autocarecancelitemoptFormService, 'getAutocarecancelitemopt').mockReturnValue(autocarecancelitemopt);
      jest.spyOn(autocarecancelitemoptService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarecancelitemopt });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarecancelitemopt }));
      saveSubject.complete();

      // THEN
      expect(autocarecancelitemoptFormService.getAutocarecancelitemopt).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autocarecancelitemoptService.update).toHaveBeenCalledWith(expect.objectContaining(autocarecancelitemopt));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarecancelitemopt>>();
      const autocarecancelitemopt = { id: 123 };
      jest.spyOn(autocarecancelitemoptFormService, 'getAutocarecancelitemopt').mockReturnValue({ id: null });
      jest.spyOn(autocarecancelitemoptService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarecancelitemopt: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autocarecancelitemopt }));
      saveSubject.complete();

      // THEN
      expect(autocarecancelitemoptFormService.getAutocarecancelitemopt).toHaveBeenCalled();
      expect(autocarecancelitemoptService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutocarecancelitemopt>>();
      const autocarecancelitemopt = { id: 123 };
      jest.spyOn(autocarecancelitemoptService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autocarecancelitemopt });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autocarecancelitemoptService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
