import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IEmpJobcommission } from '../emp-jobcommission.model';
import { EmpJobcommissionService } from '../service/emp-jobcommission.service';
import { EmpJobcommissionFormService, EmpJobcommissionFormGroup } from './emp-jobcommission-form.service';

@Component({
  standalone: true,
  selector: 'jhi-emp-jobcommission-update',
  templateUrl: './emp-jobcommission-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class EmpJobcommissionUpdateComponent implements OnInit {
  isSaving = false;
  empJobcommission: IEmpJobcommission | null = null;

  protected empJobcommissionService = inject(EmpJobcommissionService);
  protected empJobcommissionFormService = inject(EmpJobcommissionFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: EmpJobcommissionFormGroup = this.empJobcommissionFormService.createEmpJobcommissionFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ empJobcommission }) => {
      this.empJobcommission = empJobcommission;
      if (empJobcommission) {
        this.updateForm(empJobcommission);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const empJobcommission = this.empJobcommissionFormService.getEmpJobcommission(this.editForm);
    if (empJobcommission.id !== null) {
      this.subscribeToSaveResponse(this.empJobcommissionService.update(empJobcommission));
    } else {
      this.subscribeToSaveResponse(this.empJobcommissionService.create(empJobcommission));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmpJobcommission>>): void {
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

  protected updateForm(empJobcommission: IEmpJobcommission): void {
    this.empJobcommission = empJobcommission;
    this.empJobcommissionFormService.resetForm(this.editForm, empJobcommission);
  }
}
