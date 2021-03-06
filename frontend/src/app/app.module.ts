import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule, Http, RequestOptions } from '@angular/http';
import { RouterModule, Routes } from "@angular/router";
import { AuthHttp, AuthConfig } from 'angular2-jwt';

import { AppComponent } from './app.component';
import { PizzaListComponent } from './pizza/pizza-list.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderComponent } from './order/order.component';
import { CustomerComponent } from './customer/customer.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { OrderListComponent } from './order/order-list.component';
import { EditCustomerComponent } from './customer/edit-customer.component';
import { OrderDetailComponent } from './order/order-detail.component';
import { PizzaEditComponent } from './admin/pizza-edit/pizza-edit.component';
import { IngredientEditComponent } from './admin/ingredient-edit/ingredient-edit.component';
import { OrderEditComponent } from './admin/order-edit/order-edit.component';

import { PizzaService } from './pizza/pizza.service';
import { ShoppingCartService } from './shopping-cart/shopping-cart.service'
import { AuthenticationService } from './authentication/authentication.service';
import { CustomerService } from './customer/customer.service';
import { OrderService } from './order/order.service';
import { IngredientService } from './admin/ingredient-edit/ingredient.service';

import { UserGuard } from './authentication/user-guard.service';
import { AdminGuard } from './authentication/admin-guard.service';

import { OrderDetailsResolver } from './order/order-details-resolver';
import { PizzaEditResolver } from './admin/pizza-edit/pizza-edit-resolver';
import { IngredientsResolver } from './admin/pizza-edit/ingredients-resolver';

import { TabViewModule } from 'primeng/primeng';
import { DialogModule } from 'primeng/primeng';
import { MenubarModule, MenuItem } from 'primeng/primeng';
import { MessagesModule } from 'primeng/primeng';
import { DataListModule } from 'primeng/primeng';
import { StepsModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { TooltipModule } from 'primeng/primeng';
import { InputTextModule } from 'primeng/primeng';
import { SpinnerModule } from 'primeng/primeng';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';
import { PickListModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';


const appRoutes: Routes = [
  { path: 'pizza', component: PizzaListComponent },
  { path: 'order', component: OrderComponent },
  { path: 'user/orders', component: OrderListComponent, canActivate: [UserGuard] },
  { path: 'user/order/:id', component: OrderDetailComponent, canActivate: [UserGuard], resolve: {order: OrderDetailsResolver} },
  { path: 'user/customer', component: EditCustomerComponent, canActivate: [UserGuard] },
  { path: 'admin/orders', component: OrderEditComponent, canActivate: [AdminGuard] },
  { path: 'admin/pizza/:id', component: PizzaEditComponent, canActivate: [AdminGuard], resolve: {pizza: PizzaEditResolver, ingredients: IngredientsResolver} },
  { path: 'admin/ingredients', component: IngredientEditComponent, canActivate: [AdminGuard] },
  { path: '', redirectTo: '/pizza', pathMatch: 'full' }
];

export function authHttpServiceFactory(http: Http, options: RequestOptions) {
  return new AuthHttp(new AuthConfig({
    noJwtError: true,
    globalHeaders: [{'Content-Type': 'application/json'}]
  }), http, options);
}

@NgModule({
  declarations: [
    AppComponent,
    PizzaListComponent,
    ShoppingCartComponent,
    OrderComponent,
    CustomerComponent,
    LoginComponent,
    RegisterComponent,
    OrderListComponent,
    EditCustomerComponent,
    OrderDetailComponent,
    PizzaEditComponent,
    IngredientEditComponent,
    OrderEditComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    MenubarModule,
    DataListModule,
    MessagesModule,
    StepsModule,
    DialogModule,
    ButtonModule,
    TooltipModule,
    InputTextModule,
    SpinnerModule,
    DataTableModule,
    GrowlModule,
    PickListModule,
    CheckboxModule
  ],
  providers: [PizzaService, ShoppingCartService, AuthenticationService, CustomerService,
  {
    provide: AuthHttp,
    useFactory: authHttpServiceFactory,
    deps: [Http, RequestOptions]
  }, UserGuard, AdminGuard, OrderService, OrderDetailsResolver, IngredientService, PizzaEditResolver, IngredientsResolver],
  bootstrap: [AppComponent]
})
export class AppModule { }
