import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { CoreModule } from '../core/core.module';
import { HomeComponent } from './components/home/home.component';

const materialModules = [
  MatButtonModule,
  MatFormFieldModule,
  MatIconModule
]

@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    AuthRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CoreModule,
    ...materialModules
  ],
  exports: [
    ...materialModules
  ]
})
export class AuthModule { }
