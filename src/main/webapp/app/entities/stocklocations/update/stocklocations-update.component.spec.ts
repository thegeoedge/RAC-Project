import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { StocklocationsService } from '../service/stocklocations.service';
import { IStocklocations } from '../stocklocations.model';
import { StocklocationsFormService } from './stocklocations-form.service';

import { StocklocationsUpdateComponent } from './stocklocations-update.component';

describe('Stocklocations Management Update Component', () => {
  let comp: StocklocationsUpdateComponent;
  let fixture: ComponentFixture<StocklocationsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let stocklocationsFormService: StocklocationsFormService;
  let stocklocationsService: StocklocationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, StocklocationsUpdateComponent],
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
      .overrideTemplate(StocklocationsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StocklocationsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    stocklocationsFormService = TestBed.inject(StocklocationsFormService);
    stocklocationsService = TestBed.inject(StocklocationsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const stocklocations: IStocklocations = { id: 456 };

      activatedRoute.data = of({ stocklocations });
      comp.ngOnInit();

      expect(comp.stocklocations).toEqual(stocklocations);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStocklocations>>();
      const stocklocations = { id: 123 };
      jest.spyOn(stocklocationsFormService, 'getStocklocations').mockReturnValue(stocklocations);
      jest.spyOn(stocklocationsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ stocklocations });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: stocklocations }));
      saveSubject.complete();

      // THEN
      expect(stocklocationsFormService.getStocklocations).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(stocklocationsService.update).toHaveBeenCalledWith(expect.objectContaining(stocklocations));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStocklocations>>();
      const stocklocations = { id: 123 };
      jest.spyOn(stocklocationsFormService, 'getStocklocations').mockReturnValue({ id: null });
      jest.spyOn(stocklocationsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ stocklocations: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: stocklocations }));
      saveSubject.complete();

      // THEN
      expect(stocklocationsFormService.getStocklocations).toHaveBeenCalled();
      expect(stocklocationsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStocklocations>>();
      const stocklocations = { id: 123 };
      jest.spyOn(stocklocationsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ stocklocations });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(stocklocationsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
