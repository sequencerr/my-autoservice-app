import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarOwner } from 'src/model/CarOwner';
import { Order } from 'src/model/Order';

@Injectable({
	providedIn: 'root'
})
export class CarOwnerService {
	private apiBaseUrl = 'http://localhost:8080/api/car-owners';

	constructor(private http: HttpClient) {}

	create() {
		return this.http.post<CarOwner>(this.apiBaseUrl, undefined);
	}

	addCar(carOwnerId: number, carId: number) {
		const url = `${this.apiBaseUrl}/${carOwnerId}/add-car?carId=${carId}`;
		return this.http.put<CarOwner>(url, undefined);
	}

	getOrders(carOwnerId: number) {
		const url = `${this.apiBaseUrl}/${carOwnerId}/orders`;
		return this.http.get<Order[]>(url);
	}
}
