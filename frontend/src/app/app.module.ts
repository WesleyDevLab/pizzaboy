import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule, Http, RequestOptions } from '@angular/http';
import { RouterModule, Routes } from "@angular/router";
import { AuthHttp, AuthConfig } from 'angular2-jwt';

import { AppComponent } from './app.component';
import { PizzaDetailComponent } from './pizza/pizza-detail.component';
import { PizzaListComponent } from './pizza/pizza-list.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderComponent } from './order/order.component';
import { CustomerComponent } from './customer/customer.component';

import { PizzaService } from './pizza/pizza.service';
import { ShoppingCartService } from './shopping-cart/shopping-cart.service'

import { TabsModule } from 'ng2-bootstrap/tabs';
import { ModalModule } from 'ng2-bootstrap/modal';
import { DropdownModule } from 'ng2-bootstrap/dropdown';

import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { AuthenticationService } from './authentication/authentication.service';


const appRoutes: Routes = [
  { path: 'pizza/:id', component: PizzaDetailComponent },
  { path: 'pizza', component: PizzaListComponent },
  { path: 'order', component: OrderComponent },
  { path: '', redirectTo: '/pizza', pathMatch: 'full' }
];

export function authHttpServiceFactory(http: Http, options: RequestOptions) {
  return new AuthHttp(new AuthConfig({
    noJwtError: true
  }), http, options);
}

@NgModule({
  declarations: [
    AppComponent,
    PizzaDetailComponent,
    PizzaListComponent,
    ShoppingCartComponent,
    OrderComponent,
    CustomerComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    TabsModule.forRoot(),
    ModalModule.forRoot(),
    DropdownModule.forRoot()
  ],
  providers: [PizzaService, ShoppingCartService, AuthenticationService,
  {
    provide: AuthHttp,
    useFactory: authHttpServiceFactory,
    deps: [Http, RequestOptions]
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
