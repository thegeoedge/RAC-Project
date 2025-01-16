import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAutocareappointmenttype } from '../autocareappointmenttype.model';
import { AutocareappointmenttypeService } from '../service/autocareappointmenttype.service';
import { AutocareappointmenttypeFormService, AutocareappointmenttypeFormGroup } from './autocareappointmenttype-form.service';

@Component({
  standalone: true,
  selector: 'jhi-autocareappointmenttype-update',
  templateUrl: './autocareappointmenttype-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AutocareappointmenttypeUpdateComponent implements OnInit {
  isSaving = false;
  autocareappointmenttype: IAutocareappointmenttype | null = null;

  protected autocareappointmenttypeService = inject(AutocareappointmenttypeService);
  protected autocareappointmenttypeFormService = inject(AutocareappointmenttypeFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AutocareappointmenttypeFormGroup = this.autocareappointmenttypeFormService.createAutocareappointmenttypeFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autocareappointmenttype }) => {
      this.autocareappointmenttype = autocareappointmenttype;
      if (autocareappointmenttype) {
        this.updateForm(autocareappointmenttype);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autocareappointmenttype = this.autocareappointmenttypeFormService.getAutocareappointmenttype(this.editForm);
    if (autocareappointmenttype.id !== null) {
      this.subscribeToSaveResponse(this.autocareappointmenttypeService.update(autocareappointmenttype));
    } else {
      this.subscribeToSaveResponse(this.autocareappointmenttypeService.create(autocareappointmenttype));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutocareappointmenttype>>): void {
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

  protected updateForm(autocareappointmenttype: IAutocareappointmenttype): void {
    this.autocareappointmenttype = autocareappointmenttype;
    this.autocareappointmenttypeFormService.resetForm(this.editForm, autocareappointmenttype);
  }
}
