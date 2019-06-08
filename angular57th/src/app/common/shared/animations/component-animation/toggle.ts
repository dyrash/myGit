import {
    trigger,
    transition,
    style,
    query,
    group,
    animateChild,
    animate,
    keyframes,
    state,
  } from '@angular/animations';
  
  export const toggle =
trigger('toggle', [
    state('show', style({
      opacity: 1,height:300
    })),
    state('hide',   style({
      height:10,  opacity: 0,
    })),
    transition('show => hide', animate('600ms ease-out')),
    transition('hide => show', animate('1000ms ease-in'))
  ])
