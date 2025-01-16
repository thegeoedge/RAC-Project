jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { VehiclecategoryService } from '../service/vehiclecategory.service';

import { VehiclecategoryDeleteDialogComponent } from './vehiclecategory-delete-dialog.component';

describe('Vehiclecategory Management Delete Component', () => {
  let comp: VehiclecategoryDeleteDialogComponent;
  let fixture: ComponentFixture<VehiclecategoryDeleteDialogComponent>;
  let service: VehiclecategoryService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, VehiclecategoryDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(VehiclecategoryDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(VehiclecategoryDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(VehiclecategoryService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
