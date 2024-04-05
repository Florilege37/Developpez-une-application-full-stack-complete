import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailPostsComponent } from 'src/app/features/mdd/components/detail-posts/detail-posts.component';
import { FormCreateArticleComponent } from 'src/app/features/mdd/components/form-create-article/form-create-article.component';
import { ListPostsComponent } from 'src/app/features/mdd/components/list-posts/list-posts.component';
import { ListTopicsComponent } from 'src/app/features/mdd/components/list-topics/list-topics.component';
import { MeComponent } from 'src/app/features/mdd/components/me/me.component';

const routes: Routes = [
  { title: 'Articles', path: '', component: ListPostsComponent},
  { title: 'Me', path: 'me', component: MeComponent},
  { title: 'Detail', path: 'posts/:id', component: DetailPostsComponent},
  { title: 'Themes', path: 'topics', component: ListTopicsComponent},
  { title: 'Créer article', path: 'createArticle', component: FormCreateArticleComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MddRoutingModule {
}
