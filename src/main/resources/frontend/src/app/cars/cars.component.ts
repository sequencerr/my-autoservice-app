import { Component } from '@angular/core';
import { Car } from 'src/model/Car';
import { CarService } from 'src/service/cars/car.service';
import { FormComponent } from '../form.component';

@Component({
	selector: 'app-cars',
	templateUrl: './cars.component.html',
	styleUrls: ['./cars.component.scss']
})
export class CarsComponent extends FormComponent {
	formModelCreate: Partial<Car> = {};
	formModelUpdate: Partial<Car> = {};

	constructor(private carService: CarService) {
		super();
	}

	create() {
		this.carService.create(this.formModelCreate).subscribe();
	}

	update() {
		this.carService.update(this.formModelUpdate).subscribe();
	}
}
