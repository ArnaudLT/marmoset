import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarmosetExplorerComponent } from './marmoset-explorer.component';

describe('MarmosetExplorerComponent', () => {
  let component: MarmosetExplorerComponent;
  let fixture: ComponentFixture<MarmosetExplorerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarmosetExplorerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarmosetExplorerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
