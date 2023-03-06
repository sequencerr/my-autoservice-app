import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverhaulsComponent } from './overhauls.component';

describe('OverhaulsComponent', () => {
  let component: OverhaulsComponent;
  let fixture: ComponentFixture<OverhaulsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OverhaulsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OverhaulsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
