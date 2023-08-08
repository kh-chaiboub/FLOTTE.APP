import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupIconMarkerComponent } from './popup-icon-marker.component';

describe('PopupIconMarkerComponent', () => {
  let component: PopupIconMarkerComponent;
  let fixture: ComponentFixture<PopupIconMarkerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopupIconMarkerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupIconMarkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
