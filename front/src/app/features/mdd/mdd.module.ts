import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { DetailPostsComponent } from 'src/app/features/mdd/components/detail-posts/detail-posts.component';
import { FormCreateArticleComponent } from 'src/app/features/mdd/components/form-create-article/form-create-article.component';
import { ListPostsComponent } from 'src/app/features/mdd/components/list-posts/list-posts.component';
import { ListTopicsComponent } from 'src/app/features/mdd/components/list-topics/list-topics.component';
import { MeComponent } from 'src/app/features/mdd/components/me/me.component';
import { MddRoutingModule } from './mdd-routing.module';
import { HeaderComponent } from './components/header/header.component';



@NgModule({
  declarations: [
    ListPostsComponent,
    DetailPostsComponent,
    MeComponent,
    ListTopicsComponent,
    FormCreateArticleComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MddRoutingModule,
  ]
})
export class MddModule { }
