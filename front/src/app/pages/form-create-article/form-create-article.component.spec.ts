import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCreateArticleComponent } from './form-create-article.component';

describe('FormCreateArticleComponent', () => {
  let component: FormCreateArticleComponent;
  let fixture: ComponentFixture<FormCreateArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormCreateArticleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormCreateArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
