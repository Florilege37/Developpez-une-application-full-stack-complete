import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailPostsComponent } from './detail-posts.component';

describe('DetailPostsComponent', () => {
  let component: DetailPostsComponent;
  let fixture: ComponentFixture<DetailPostsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailPostsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
