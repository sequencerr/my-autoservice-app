import { Component } from '@angular/core';
import { Order } from 'src/model/Order';
import { Repairer } from 'src/model/Repairer';
import { RepairerService } from 'src/service/repairers/repairer.service';
import { FormComponent } from '../form.component';

@Component({
	selector: 'app-repairers',
	templateUrl: './repairers.component.html',
	styleUrls: ['./repairers.component.scss']
})
export class RepairersComponent extends FormComponent {
	formModelCreate: Partial<Repairer> = {};
	formModelUpdate: Partial<Repairer> = {};
	formModelGetOrders: Partial<Repairer> = {};
	formModelGetSalary: Partial<Repairer> = {};
	ordersDisplay?: Order[];
	salaryDisplay?: number;

	constructor(private repairerService: RepairerService) {
		super();
	}

	create() {
		this.repairerService.create(this.formModelCreate).subscribe();
	}

	update() {
		this.repairerService.update(this.formModelUpdate).subscribe();
	}

	getOrders() {
		const { id } = this.formModelCreate;
		this.repairerService.getCompletedOrders(id!).subscribe({
			next: a => (this.ordersDisplay = a),
			error: () => (this.ordersDisplay = undefined)
		});
	}

	getSalary() {
		const { id } = this.formModelGetSalary;
		this.repairerService.getSalaryAndPay(id!).subscribe({
			next: e => (this.salaryDisplay = e),
			error: () => (this.salaryDisplay = undefined)
		});
	}
}
