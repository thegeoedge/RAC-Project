import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IBanks } from '../banks.model';
import { BanksService } from '../service/banks.service';
import { BanksFormService, BanksFormGroup } from './banks-form.service';

@Component({
  standalone: true,
  selector: 'jhi-banks-update',
  templateUrl: './banks-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BanksUpdateComponent implements OnInit {
  isSaving = false;
  banks: IBanks | null = null;

  protected banksService = inject(BanksService);
  protected banksFormService = inject(BanksFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: BanksFormGroup = this.banksFormService.createBanksFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banks }) => {
      this.banks = banks;
      if (banks) {
        this.updateForm(banks);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const banks = this.banksFormService.getBanks(this.editForm);
    if (banks.id !== null) {
      this.subscribeToSaveResponse(this.banksService.update(banks));
    } else {
      this.subscribeToSaveResponse(this.banksService.create(banks));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBanks>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(banks: IBanks): void {
    this.banks = banks;
    this.banksFormService.resetForm(this.editForm, banks);
  }
}
