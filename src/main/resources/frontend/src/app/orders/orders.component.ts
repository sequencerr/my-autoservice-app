import { Component } from '@angular/core';
import { Order, OrderStatus } from 'src/model/Order';
import { OrderService } from 'src/service/orders/order.service';
import { FormComponent } from '../form.component';

@Component({
	selector: 'app-orders',
	templateUrl: './orders.component.html',
	styleUrls: ['./orders.component.scss']
})
export class OrdersComponent extends FormComponent {
	statuses = Object.values(OrderStatus)
		.filter(v => typeof v == 'string')
		.slice(1) as readonly string[];
	formModelCreate: Partial<Order> = {};
	formModelUpdate: Partial<Order> = {};
	formModelAddOverhaul: { orderId?: number; overhaulId?: number } = {};
	formModelAddDetail: { orderId?: number; detailId?: number } = {};
	formModelGetPrice: Partial<Order> = {};
	priceDisplay?: number;

	constructor(private orderService: OrderService) {
		super();
	}

	create() {
		this.orderService.create(this.formModelCreate).subscribe();
	}

	update() {
		const { id, description, status, carId } = this.formModelUpdate;
		if (status !== undefined)
			this.orderService
				.updateStatus(id!, OrderStatus[status!] as keyof typeof OrderStatus)
				.subscribe();
		if (description !== undefined || carId !== undefined)
			this.orderService.update(this.formModelUpdate).subscribe();
	}

	addOverhaul() {
		const { orderId, overhaulId } = this.formModelAddOverhaul;
		this.orderService.addOverhaul(orderId!, overhaulId!).subscribe();
	}

	addDetail() {
		const { orderId, detailId } = this.formModelAddDetail;
		this.orderService.addDetail(orderId!, detailId!).subscribe();
	}

	getPrice() {
		this.orderService.getPrice(this.formModelGetPrice.id!).subscribe({
			error: () => (this.priceDisplay = undefined),
			next: price => (this.priceDisplay = price)
		});
	}
}
