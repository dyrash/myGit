import { Component, OnInit } from '@angular/core';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-double-date-picker',
  templateUrl: './double-date-picker.component.html',
  styleUrls: ['./double-date-picker.component.css']
})
export class DoubleDatePickerComponent {}
  
//   hoveredDate: NgbDate;

//   fromDate: NgbDate;
//   toDate: NgbDate;

//   constructor() { }

//   ngOnInit() {
//   }
// //달력 function
// onDateSelection(date: NgbDate) {
//   if (!this.fromDate && !this.toDate) {
//     this.fromDate = date;
//   } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
//     this.toDate = date;
//   } else {
//     this.toDate = null;
//     this.fromDate = date;
//   }
//   // console.log(this.fromDate.year+this.fromDate.month+this.fromDate.day);
//   // console.log(this.toDate.year+this.toDate.month+this.toDate.day);
// }

// isHovered(date: NgbDate) {
//   return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
// }

// isInside(date: NgbDate) {
//   return date.after(this.fromDate) && date.before(this.toDate);
// }

// isRange(date: NgbDate) {

//   return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);

// } //달력 function 종료

// }
