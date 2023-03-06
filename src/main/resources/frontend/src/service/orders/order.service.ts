import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order, OrderStatus } from 'src/model/Order';

@Injectable({
	providedIn: 'root'
})
export class OrderService {
	private apiBaseUrl = 'http://localhost:8080/api/orders';

	constructor(private http: HttpClient) {}

	create(order: Partial<Order>) {
		return this.http.post<Order>(this.apiBaseUrl, order);
	}

	update(order: Partial<Order>) {
		const url = `${this.apiBaseUrl}/${order.id}`;
		return this.http.put<Order>(url, order);
	}

	addOverhaul(orderId: number, overhaulId: number) {
		const url = `${this.apiBaseUrl}/${orderId}/add-overhaul?overhaulId=${overhaulId}`;
		return this.http.put<string>(url, undefined);
	}

	addDetail(orderId: number, detailId: number) {
		const url = `${this.apiBaseUrl}/${orderId}/add-detail?detailId=${detailId}`;
		return this.http.put<string>(url, undefined);
	}

	updateStatus(orderId: number, orderStatus: keyof typeof OrderStatus) {
		const url = `${this.apiBaseUrl}/${orderId}/status?status=${orderStatus}`;
		return this.http.put<string>(url, undefined);
	}

	getPrice(id: number) {
		const url = `${this.apiBaseUrl}/${id}/price`;
		return this.http.get<number>(url);
	}
}
