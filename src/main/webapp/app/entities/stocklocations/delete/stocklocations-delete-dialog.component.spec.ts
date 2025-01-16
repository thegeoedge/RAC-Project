jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { StocklocationsService } from '../service/stocklocations.service';

import { StocklocationsDeleteDialogComponent } from './stocklocations-delete-dialog.component';

describe('Stocklocations Management Delete Component', () => {
  let comp: StocklocationsDeleteDialogComponent;
  let fixture: ComponentFixture<StocklocationsDeleteDialogComponent>;
  let service: StocklocationsService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, StocklocationsDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(StocklocationsDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(StocklocationsDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(StocklocationsService);
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
