import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    var sun = document.getElementById('sun');
    var earth = document.getElementById('earth');
    var moon = document.getElementById('moon');
    // 문서 객체의 스타일 속성을 변경합니다.
    sun.style.position = 'absolute';
    earth.style.position = 'absolute';
    moon.style.position = 'absolute';
    var left:number=750;
    sun.style.left = left + 'px';
    sun.style.top = 200 + 'px';
    // 변수를 선언합니다.
    var earthAngle = 0;
    var moonAngle = 0;
    // 애니메이션을 시작합니다.
    setInterval(function () {
        // 각도로 지구와 달의 좌표를 구합니다.
        var earthLeft = left + 150 * Math.cos(earthAngle);
        var earthTop = 200 + 150 * Math.sin(earthAngle);
        var moonLeft = earthLeft + 50 * Math.cos(moonAngle);
        var moonTop = earthTop + 50 * Math.sin(moonAngle);
        // 위치를 이동합니다.
        earth.style.left = earthLeft + 'px';
        earth.style.top = earthTop + 'px';
        moon.style.left = moonLeft + 'px';
        moon.style.top = moonTop + 'px';
        // 각도를 변경합니다.
        earthAngle += 0.1;
        moonAngle += 0.3;
    }, 1000 / 30);
  }

}
