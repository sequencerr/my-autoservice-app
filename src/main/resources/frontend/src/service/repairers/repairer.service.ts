import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from 'src/model/Order';
import { Repairer } from 'src/model/Repairer';

@Injectable({
	providedIn: 'root'
})
export class RepairerService {
	private apiBaseUrl = 'http://localhost:8080/api/repairers';

	constructor(private http: HttpClient) {}

	create(repairer: Partial<Repairer>) {
		return this.http.post<Repairer>(this.apiBaseUrl, repairer);
	}

	update(repairer: Partial<Repairer>) {
		const url = `${this.apiBaseUrl}/${repairer.id}`;
		return this.http.put<Repairer>(url, repairer);
	}

	getCompletedOrders(repairerId: number) {
		const url = `${this.apiBaseUrl}/${repairerId}/completed-orders`;
		return this.http.get<Order[]>(url);
	}

	getSalaryAndPay(repairerId: number) {
		const url = `${this.apiBaseUrl}/${repairerId}/pay-salary`;
		return this.http.get<number>(url);
	}
}
