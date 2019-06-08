import {Component, ElementRef, ViewChild} from '@angular/core';
import { AgGridModule } from 'ag-grid-angular';
@Component({
    selector: 'app-loading-overlay',
    template:`<mat-form-field>
      <input matInput [matDatepicker]="dp3" placeholder="Input disabled" disabled>
      <mat-datepicker-toggle matSuffix [for]="dp3"></mat-datepicker-toggle>
      <mat-datepicker #dp3 disabled="false"></mat-datepicker>
    </mat-form-field>`
})

export class GridDatePickerComponent{
  @ViewChild("dp3", {read: ElementRef}) matDate:ElementRef;
    private date: Date;
    private params: any;
    private picker: any;
 
    agInit(params: any): void {
      // this.picker=flatpickr(this.matDate.nativeElement,{
      //   onChange: this.onDateChanged.bind(this),
      //   wrap: true
      // })
        this.params = params;
    }

    ngAfterViewInit(): void {
      
        this.picker.calendarContainer.classList.add('ag-custom-component-popup');
    }

    ngOnDestroy() {
        console.log(`Destroying DateComponent`);
    }

    onDateChanged(selectedDates) {
        this.date = selectedDates[0] || null;
        this.params.onDateChanged();
    }

    getDate(): Date {
        return this.date;
    }

    setDate(date: Date): void {
       this.date = date || null;
       this.picker.setDate(date);
    }
}

