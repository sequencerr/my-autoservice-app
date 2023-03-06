import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepairersComponent } from './repairers.component';

describe('RepairersComponent', () => {
  let component: RepairersComponent;
  let fixture: ComponentFixture<RepairersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RepairersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepairersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
