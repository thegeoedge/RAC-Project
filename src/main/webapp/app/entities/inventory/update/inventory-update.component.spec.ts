import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { InventoryService } from '../service/inventory.service';
import { IInventory } from '../inventory.model';
import { InventoryFormService } from './inventory-form.service';

import { InventoryUpdateComponent } from './inventory-update.component';

describe('Inventory Management Update Component', () => {
  let comp: InventoryUpdateComponent;
  let fixture: ComponentFixture<InventoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let inventoryFormService: InventoryFormService;
  let inventoryService: InventoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, InventoryUpdateComponent],
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
      .overrideTemplate(InventoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(InventoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    inventoryFormService = TestBed.inject(InventoryFormService);
    inventoryService = TestBed.inject(InventoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const inventory: IInventory = { id: 456 };

      activatedRoute.data = of({ inventory });
      comp.ngOnInit();

      expect(comp.inventory).toEqual(inventory);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInventory>>();
      const inventory = { id: 123 };
      jest.spyOn(inventoryFormService, 'getInventory').mockReturnValue(inventory);
      jest.spyOn(inventoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ inventory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: inventory }));
      saveSubject.complete();

      // THEN
      expect(inventoryFormService.getInventory).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(inventoryService.update).toHaveBeenCalledWith(expect.objectContaining(inventory));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInventory>>();
      const inventory = { id: 123 };
      jest.spyOn(inventoryFormService, 'getInventory').mockReturnValue({ id: null });
      jest.spyOn(inventoryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ inventory: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: inventory }));
      saveSubject.complete();

      // THEN
      expect(inventoryFormService.getInventory).toHaveBeenCalled();
      expect(inventoryService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInventory>>();
      const inventory = { id: 123 };
      jest.spyOn(inventoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ inventory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(inventoryService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
