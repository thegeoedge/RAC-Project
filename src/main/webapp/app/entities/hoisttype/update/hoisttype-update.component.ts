import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IHoisttype } from '../hoisttype.model';
import { HoisttypeService } from '../service/hoisttype.service';
import { HoisttypeFormService, HoisttypeFormGroup } from './hoisttype-form.service';

@Component({
  standalone: true,
  selector: 'jhi-hoisttype-update',
  templateUrl: './hoisttype-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HoisttypeUpdateComponent implements OnInit {
  isSaving = false;
  hoisttype: IHoisttype | null = null;

  protected hoisttypeService = inject(HoisttypeService);
  protected hoisttypeFormService = inject(HoisttypeFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: HoisttypeFormGroup = this.hoisttypeFormService.createHoisttypeFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hoisttype }) => {
      this.hoisttype = hoisttype;
      if (hoisttype) {
        this.updateForm(hoisttype);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hoisttype = this.hoisttypeFormService.getHoisttype(this.editForm);
    if (hoisttype.id !== null) {
      this.subscribeToSaveResponse(this.hoisttypeService.update(hoisttype));
    } else {
      this.subscribeToSaveResponse(this.hoisttypeService.create(hoisttype));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHoisttype>>): void {
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

  protected updateForm(hoisttype: IHoisttype): void {
    this.hoisttype = hoisttype;
    this.hoisttypeFormService.resetForm(this.editForm, hoisttype);
  }
}
