import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { HoisttypeService } from '../service/hoisttype.service';
import { IHoisttype } from '../hoisttype.model';
import { HoisttypeFormService } from './hoisttype-form.service';

import { HoisttypeUpdateComponent } from './hoisttype-update.component';

describe('Hoisttype Management Update Component', () => {
  let comp: HoisttypeUpdateComponent;
  let fixture: ComponentFixture<HoisttypeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hoisttypeFormService: HoisttypeFormService;
  let hoisttypeService: HoisttypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, HoisttypeUpdateComponent],
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
      .overrideTemplate(HoisttypeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HoisttypeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hoisttypeFormService = TestBed.inject(HoisttypeFormService);
    hoisttypeService = TestBed.inject(HoisttypeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hoisttype: IHoisttype = { id: 456 };

      activatedRoute.data = of({ hoisttype });
      comp.ngOnInit();

      expect(comp.hoisttype).toEqual(hoisttype);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHoisttype>>();
      const hoisttype = { id: 123 };
      jest.spyOn(hoisttypeFormService, 'getHoisttype').mockReturnValue(hoisttype);
      jest.spyOn(hoisttypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoisttype });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hoisttype }));
      saveSubject.complete();

      // THEN
      expect(hoisttypeFormService.getHoisttype).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(hoisttypeService.update).toHaveBeenCalledWith(expect.objectContaining(hoisttype));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHoisttype>>();
      const hoisttype = { id: 123 };
      jest.spyOn(hoisttypeFormService, 'getHoisttype').mockReturnValue({ id: null });
      jest.spyOn(hoisttypeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoisttype: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hoisttype }));
      saveSubject.complete();

      // THEN
      expect(hoisttypeFormService.getHoisttype).toHaveBeenCalled();
      expect(hoisttypeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHoisttype>>();
      const hoisttype = { id: 123 };
      jest.spyOn(hoisttypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoisttype });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hoisttypeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
