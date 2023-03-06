import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routes';
import { CarOwnersComponent } from './car-owners/car-owners.component';
import { CarsComponent } from './cars/cars.component';
import { DetailsComponent } from './details/details.component';
import { OrdersComponent } from './orders/orders.component';
import { OverhaulsComponent } from './overhauls/overhauls.component';
import { RepairersComponent } from './repairers/repairers.component';

@NgModule({
	declarations: [
		AppComponent,
		OrdersComponent,
		CarOwnersComponent,
		CarsComponent,
		DetailsComponent,
		OverhaulsComponent,
		RepairersComponent
	],
	imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {}
