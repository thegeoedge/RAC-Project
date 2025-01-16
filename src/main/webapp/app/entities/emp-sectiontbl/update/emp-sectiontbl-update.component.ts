import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IEmpSectiontbl } from '../emp-sectiontbl.model';
import { EmpSectiontblService } from '../service/emp-sectiontbl.service';
import { EmpSectiontblFormService, EmpSectiontblFormGroup } from './emp-sectiontbl-form.service';

@Component({
  standalone: true,
  selector: 'jhi-emp-sectiontbl-update',
  templateUrl: './emp-sectiontbl-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class EmpSectiontblUpdateComponent implements OnInit {
  isSaving = false;
  empSectiontbl: IEmpSectiontbl | null = null;

  protected empSectiontblService = inject(EmpSectiontblService);
  protected empSectiontblFormService = inject(EmpSectiontblFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: EmpSectiontblFormGroup = this.empSectiontblFormService.createEmpSectiontblFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ empSectiontbl }) => {
      this.empSectiontbl = empSectiontbl;
      if (empSectiontbl) {
        this.updateForm(empSectiontbl);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const empSectiontbl = this.empSectiontblFormService.getEmpSectiontbl(this.editForm);
    if (empSectiontbl.id !== null) {
      this.subscribeToSaveResponse(this.empSectiontblService.update(empSectiontbl));
    } else {
      this.subscribeToSaveResponse(this.empSectiontblService.create(empSectiontbl));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmpSectiontbl>>): void {
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

  protected updateForm(empSectiontbl: IEmpSectiontbl): void {
    this.empSectiontbl = empSectiontbl;
    this.empSectiontblFormService.resetForm(this.editForm, empSectiontbl);
  }
}
