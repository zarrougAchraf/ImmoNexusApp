import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-advertising-add-edit',
  templateUrl: './advertising-add-edit.component.html',
  styleUrls: ['./advertising-add-edit.component.css']
})
export class AdvertisingAddEditComponent implements OnInit {

  public advertisingForm!: FormGroup
  constructor(public dialogRef: MatDialogRef<AdvertisingAddEditComponent>) { }

  ngOnInit(): void {
    this.initForm()
  }


  initForm() {
    this.advertisingForm = new FormGroup({
      title: new FormControl(),
      description: new FormControl(),
      nbrVuesCible: new FormControl(),
      coutParJour: new FormControl(),
      coutParVueCible: new FormControl(),
      image: new FormControl(),
      endDate: new FormControl(),
      startDate: new FormControl(),
      socityName: new FormControl(),
    });
    this.advertisingForm.valueChanges.subscribe(
      data => console.log(data)
    )
  }

  confirm() {
    this.dialogRef.close(this.advertisingForm.value)
  }
}
