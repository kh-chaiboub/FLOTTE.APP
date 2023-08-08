import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TreeVehiculeMapComponent } from './tree-vehicule-tab-map.component';

describe('TreeVehiculeMapComponent', () => {
  let component: TreeVehiculeMapComponent;
  let fixture: ComponentFixture<TreeVehiculeMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TreeVehiculeMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TreeVehiculeMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
