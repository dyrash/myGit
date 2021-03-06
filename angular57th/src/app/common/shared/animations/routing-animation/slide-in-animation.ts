import {
  trigger,
  transition,
  style,
  query,
  group,
  animateChild,
  animate,
  keyframes,
} from '@angular/animations';

export const slider =
  trigger('routeAnimations', [
    transition('* => *', slideTo('right') )
  ]);

function slideTo(direction) {

  const optional = { optional: true };
  return [
    query(':enter, :leave', [
      style({
        position: 'absolute',
        top: 57,
        [direction]: 0,
        width: '84%'
      })
    ], optional),
    query(':enter', [
      style({ [direction]: '-80%'})
    ]),
    group([
      query(':leave', [
        animate('1000ms ease', style({ [direction]: '80%'}))
      ], optional),
      query(':enter', [
        animate('1000ms ease', style({ [direction]: '0%'}))
      ])
    ]),
    // Normalize the page style... Might not be necessary

    // Required only if you have child animations on the page
     //query(':leave', animateChild()),
     //query(':enter', animateChild()),
  ];
}