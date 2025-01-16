jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { SalesinvoiceService } from '../service/salesinvoice.service';

import { SalesinvoiceDeleteDialogComponent } from './salesinvoice-delete-dialog.component';

describe('Salesinvoice Management Delete Component', () => {
  let comp: SalesinvoiceDeleteDialogComponent;
  let fixture: ComponentFixture<SalesinvoiceDeleteDialogComponent>;
  let service: SalesinvoiceService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, SalesinvoiceDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(SalesinvoiceDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SalesinvoiceDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(SalesinvoiceService);
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
