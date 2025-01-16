import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocarejob.test-samples';

import { AutocarejobFormService } from './autocarejob-form.service';

describe('Autocarejob Form Service', () => {
  let service: AutocarejobFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocarejobFormService);
  });

  describe('Service methods', () => {
    describe('createAutocarejobFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocarejobFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobnumber: expect.any(Object),
            vehicleid: expect.any(Object),
            vehiclenumber: expect.any(Object),
            millage: expect.any(Object),
            nextmillage: expect.any(Object),
            nextservicedate: expect.any(Object),
            vehicletypeid: expect.any(Object),
            jobtypeid: expect.any(Object),
            jobtypename: expect.any(Object),
            jobopenby: expect.any(Object),
            jobopentime: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            specialrquirments: expect.any(Object),
            specialinstructions: expect.any(Object),
            remarks: expect.any(Object),
            nextserviceinstructions: expect.any(Object),
            lastserviceinstructions: expect.any(Object),
            isadvisorchecked: expect.any(Object),
            isempallocated: expect.any(Object),
            jobclosetime: expect.any(Object),
            isjobclose: expect.any(Object),
            isfeedback: expect.any(Object),
            feedbackstatusid: expect.any(Object),
            customername: expect.any(Object),
            customertel: expect.any(Object),
            customerid: expect.any(Object),
            advisorfinalcheck: expect.any(Object),
            jobdate: expect.any(Object),
            iscompanyservice: expect.any(Object),
            freeservicenumber: expect.any(Object),
            companyid: expect.any(Object),
            updatetocustomer: expect.any(Object),
            nextgearoilmilage: expect.any(Object),
            isjobinvoiced: expect.any(Object),
            iswaiting: expect.any(Object),
            iscustomercomment: expect.any(Object),
            imagefolder: expect.any(Object),
            frontimage: expect.any(Object),
            leftimage: expect.any(Object),
            rightimage: expect.any(Object),
            backimage: expect.any(Object),
            dashboardimage: expect.any(Object),
          }),
        );
      });

      it('passing IAutocarejob should create a new form with FormGroup', () => {
        const formGroup = service.createAutocarejobFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobnumber: expect.any(Object),
            vehicleid: expect.any(Object),
            vehiclenumber: expect.any(Object),
            millage: expect.any(Object),
            nextmillage: expect.any(Object),
            nextservicedate: expect.any(Object),
            vehicletypeid: expect.any(Object),
            jobtypeid: expect.any(Object),
            jobtypename: expect.any(Object),
            jobopenby: expect.any(Object),
            jobopentime: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            specialrquirments: expect.any(Object),
            specialinstructions: expect.any(Object),
            remarks: expect.any(Object),
            nextserviceinstructions: expect.any(Object),
            lastserviceinstructions: expect.any(Object),
            isadvisorchecked: expect.any(Object),
            isempallocated: expect.any(Object),
            jobclosetime: expect.any(Object),
            isjobclose: expect.any(Object),
            isfeedback: expect.any(Object),
            feedbackstatusid: expect.any(Object),
            customername: expect.any(Object),
            customertel: expect.any(Object),
            customerid: expect.any(Object),
            advisorfinalcheck: expect.any(Object),
            jobdate: expect.any(Object),
            iscompanyservice: expect.any(Object),
            freeservicenumber: expect.any(Object),
            companyid: expect.any(Object),
            updatetocustomer: expect.any(Object),
            nextgearoilmilage: expect.any(Object),
            isjobinvoiced: expect.any(Object),
            iswaiting: expect.any(Object),
            iscustomercomment: expect.any(Object),
            imagefolder: expect.any(Object),
            frontimage: expect.any(Object),
            leftimage: expect.any(Object),
            rightimage: expect.any(Object),
            backimage: expect.any(Object),
            dashboardimage: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocarejob', () => {
      it('should return NewAutocarejob for default Autocarejob initial value', () => {
        const formGroup = service.createAutocarejobFormGroup(sampleWithNewData);

        const autocarejob = service.getAutocarejob(formGroup) as any;

        expect(autocarejob).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocarejob for empty Autocarejob initial value', () => {
        const formGroup = service.createAutocarejobFormGroup();

        const autocarejob = service.getAutocarejob(formGroup) as any;

        expect(autocarejob).toMatchObject({});
      });

      it('should return IAutocarejob', () => {
        const formGroup = service.createAutocarejobFormGroup(sampleWithRequiredData);

        const autocarejob = service.getAutocarejob(formGroup) as any;

        expect(autocarejob).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocarejob should not enable id FormControl', () => {
        const formGroup = service.createAutocarejobFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocarejob should disable id FormControl', () => {
        const formGroup = service.createAutocarejobFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
