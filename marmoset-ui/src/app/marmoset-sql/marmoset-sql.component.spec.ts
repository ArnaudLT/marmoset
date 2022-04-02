import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarmosetSqlComponent } from './marmoset-sql.component';

describe('MarmosetSqlComponent', () => {
  let component: MarmosetSqlComponent;
  let fixture: ComponentFixture<MarmosetSqlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarmosetSqlComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarmosetSqlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
