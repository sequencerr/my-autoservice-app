import { Component } from '@angular/core';
import { CarOwner } from 'src/model/CarOwner';
import { Order } from 'src/model/Order';
import { CarOwnerService } from 'src/service/car-owners/car-owner.service';
import { FormComponent } from '../form.component';

@Component({
	selector: 'app-car-owners',
	templateUrl: './car-owners.component.html',
	styleUrls: ['./car-owners.component.scss']
})
export class CarOwnersComponent extends FormComponent {
	formModelAddCar: { carOwnerId?: number; carId?: number } = {};
	formModelGetOrders: Partial<CarOwner> = {};
	ordersDisplay?: Order[];

	constructor(private carOwnerService: CarOwnerService) {
		super();
	}

	create() {
		this.carOwnerService.create().subscribe({
			next: o => alert(`Created car owner with id: ${o.id}`),
			error: () => alert('Unable to create new car owner')
		});
	}

	addCar() {
		const { carOwnerId, carId } = this.formModelAddCar;
		this.carOwnerService.addCar(carOwnerId!, carId!).subscribe();
	}

	getOrders() {
		const { id } = this.formModelGetOrders;
		this.carOwnerService.getOrders(id!).subscribe();
	}
}
