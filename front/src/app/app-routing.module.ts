import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/auth/components/home/home.component';
import { LoginComponent } from './features/auth/components/login/login.component';
import { RegisterComponent } from './features/auth/components/register/register.component';
import { ListPostsComponent } from './features/mdd/components/list-posts/list-posts.component';
import { MeComponent } from './features/mdd/components/me/me.component';
import { DetailPostsComponent } from './features/mdd/components/detail-posts/detail-posts.component';
import { ListTopicsComponent } from './features/mdd/components/list-topics/list-topics.component';
import { FormCreateArticleComponent } from './features/mdd/components/form-create-article/form-create-article.component';
import { UnauthGuard } from './guards/unauth.guard';
import { AuthGuard } from './guards/auth.guard';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {
    path: '',
    canActivate: [UnauthGuard],
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },
  {path: 'posts',
  canActivate: [AuthGuard],
  loadChildren: () => import('./features/mdd/mdd.module').then(m => m.MddModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
