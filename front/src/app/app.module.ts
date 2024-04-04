import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './pages/register/register.component';
import { ListPostsComponent } from './pages/list-posts/list-posts.component';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { MeComponent } from './pages/me/me.component';
import { DetailPostsComponent } from './pages/detail-posts/detail-posts.component';
import { HeaderComponent } from './pages/header/header.component';
import { ListTopicsComponent } from './pages/list-topics/list-topics.component';

const materialModule = [
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatSnackBarModule,
  MatToolbarModule,
]

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, RegisterComponent, ListPostsComponent, MeComponent, DetailPostsComponent, HeaderComponent, ListTopicsComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule, 
    CommonModule,
    ...materialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
