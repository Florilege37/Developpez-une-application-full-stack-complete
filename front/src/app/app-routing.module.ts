import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ListPostsComponent } from './pages/list-posts/list-posts.component';
import { MeComponent } from './pages/me/me.component';
import { DetailPostsComponent } from './pages/detail-posts/detail-posts.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { title: 'Login', path: 'login', component: LoginComponent },
  { title: 'Register', path: 'register', component: RegisterComponent},
  { title: 'Articles', path: 'posts', component: ListPostsComponent},
  { title: 'Me', path: 'me', component: MeComponent},
  { title: 'Detail', path: 'posts/:id', component: DetailPostsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
