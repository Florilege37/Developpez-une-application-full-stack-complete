import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './features/auth/components/home/home.component';
import { LoginComponent } from './features/auth/components/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './features/auth/components/register/register.component';
import { ListPostsComponent } from './features/mdd/components/list-posts/list-posts.component';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { MeComponent } from './features/mdd/components/me/me.component';
import { DetailPostsComponent } from './features/mdd/components/detail-posts/detail-posts.component';
import { HeaderComponent } from './features/mdd/components/header/header.component';
import { ListTopicsComponent } from './features/mdd/components/list-topics/list-topics.component';
import { FormCreateArticleComponent } from './features/mdd/components/form-create-article/form-create-article.component';
import { MddModule } from './features/mdd/mdd.module';
import { AuthModule } from './features/auth/auth.module';

const materialModule = [
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatSnackBarModule,
  MatToolbarModule,
]

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MddModule,
    AuthModule,
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
