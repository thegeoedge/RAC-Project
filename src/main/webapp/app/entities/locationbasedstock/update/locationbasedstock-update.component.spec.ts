import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { LocationbasedstockService } from '../service/locationbasedstock.service';
import { ILocationbasedstock } from '../locationbasedstock.model';
import { LocationbasedstockFormService } from './locationbasedstock-form.service';

import { LocationbasedstockUpdateComponent } from './locationbasedstock-update.component';

describe('Locationbasedstock Management Update Component', () => {
  let comp: LocationbasedstockUpdateComponent;
  let fixture: ComponentFixture<LocationbasedstockUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let locationbasedstockFormService: LocationbasedstockFormService;
  let locationbasedstockService: LocationbasedstockService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, LocationbasedstockUpdateComponent],
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
      .overrideTemplate(LocationbasedstockUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LocationbasedstockUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    locationbasedstockFormService = TestBed.inject(LocationbasedstockFormService);
    locationbasedstockService = TestBed.inject(LocationbasedstockService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const locationbasedstock: ILocationbasedstock = { id: 456 };

      activatedRoute.data = of({ locationbasedstock });
      comp.ngOnInit();

      expect(comp.locationbasedstock).toEqual(locationbasedstock);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILocationbasedstock>>();
      const locationbasedstock = { id: 123 };
      jest.spyOn(locationbasedstockFormService, 'getLocationbasedstock').mockReturnValue(locationbasedstock);
      jest.spyOn(locationbasedstockService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ locationbasedstock });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: locationbasedstock }));
      saveSubject.complete();

      // THEN
      expect(locationbasedstockFormService.getLocationbasedstock).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(locationbasedstockService.update).toHaveBeenCalledWith(expect.objectContaining(locationbasedstock));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILocationbasedstock>>();
      const locationbasedstock = { id: 123 };
      jest.spyOn(locationbasedstockFormService, 'getLocationbasedstock').mockReturnValue({ id: null });
      jest.spyOn(locationbasedstockService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ locationbasedstock: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: locationbasedstock }));
      saveSubject.complete();

      // THEN
      expect(locationbasedstockFormService.getLocationbasedstock).toHaveBeenCalled();
      expect(locationbasedstockService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILocationbasedstock>>();
      const locationbasedstock = { id: 123 };
      jest.spyOn(locationbasedstockService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ locationbasedstock });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(locationbasedstockService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
