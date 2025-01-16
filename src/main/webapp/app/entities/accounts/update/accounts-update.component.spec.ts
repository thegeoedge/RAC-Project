import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { AccountsService } from '../service/accounts.service';
import { IAccounts } from '../accounts.model';
import { AccountsFormService } from './accounts-form.service';

import { AccountsUpdateComponent } from './accounts-update.component';

describe('Accounts Management Update Component', () => {
  let comp: AccountsUpdateComponent;
  let fixture: ComponentFixture<AccountsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let accountsFormService: AccountsFormService;
  let accountsService: AccountsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, AccountsUpdateComponent],
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
      .overrideTemplate(AccountsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AccountsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    accountsFormService = TestBed.inject(AccountsFormService);
    accountsService = TestBed.inject(AccountsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const accounts: IAccounts = { id: 456 };

      activatedRoute.data = of({ accounts });
      comp.ngOnInit();

      expect(comp.accounts).toEqual(accounts);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAccounts>>();
      const accounts = { id: 123 };
      jest.spyOn(accountsFormService, 'getAccounts').mockReturnValue(accounts);
      jest.spyOn(accountsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ accounts });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: accounts }));
      saveSubject.complete();

      // THEN
      expect(accountsFormService.getAccounts).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(accountsService.update).toHaveBeenCalledWith(expect.objectContaining(accounts));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAccounts>>();
      const accounts = { id: 123 };
      jest.spyOn(accountsFormService, 'getAccounts').mockReturnValue({ id: null });
      jest.spyOn(accountsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ accounts: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: accounts }));
      saveSubject.complete();

      // THEN
      expect(accountsFormService.getAccounts).toHaveBeenCalled();
      expect(accountsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAccounts>>();
      const accounts = { id: 123 };
      jest.spyOn(accountsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ accounts });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(accountsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
