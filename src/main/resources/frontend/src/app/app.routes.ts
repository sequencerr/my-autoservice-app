import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarOwnersComponent } from './car-owners/car-owners.component';
import { CarsComponent } from './cars/cars.component';
import { DetailsComponent } from './details/details.component';
import { OrdersComponent } from './orders/orders.component';
import { RepairersComponent } from './repairers/repairers.component';

const routes: Routes = [
	{ path: 'car-owners', component: CarOwnersComponent },
	{ path: 'cars', component: CarsComponent },
	{ path: 'details', component: DetailsComponent },
	{ path: 'orders', component: OrdersComponent },
	{ path: 'overhauls', component: DetailsComponent },
	{ path: 'repairers', component: RepairersComponent },
	{ path: '**', redirectTo: '' }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {}
