import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarmosetOverviewComponent } from './marmoset-overview.component';

describe('MarmosetOverviewComponent', () => {
  let component: MarmosetOverviewComponent;
  let fixture: ComponentFixture<MarmosetOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarmosetOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarmosetOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
