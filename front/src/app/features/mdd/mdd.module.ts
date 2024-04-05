import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { DetailPostsComponent } from 'src/app/features/mdd/components/detail-posts/detail-posts.component';
import { FormCreateArticleComponent } from 'src/app/features/mdd/components/form-create-article/form-create-article.component';
import { ListPostsComponent } from 'src/app/features/mdd/components/list-posts/list-posts.component';
import { ListTopicsComponent } from 'src/app/features/mdd/components/list-topics/list-topics.component';
import { MeComponent } from 'src/app/features/mdd/components/me/me.component';
import { MddRoutingModule } from './mdd-routing.module';
import { HeaderComponent } from '../core/components/header/header.component';
import { CoreModule } from '../core/core.module';



@NgModule({
  declarations: [
    ListPostsComponent,
    DetailPostsComponent,
    MeComponent,
    ListTopicsComponent,
    FormCreateArticleComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MddRoutingModule,
    CoreModule
  ]
})
export class MddModule { }
