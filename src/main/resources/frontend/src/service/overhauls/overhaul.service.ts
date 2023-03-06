import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Overhaul } from 'src/model/Overhaul';

@Injectable({
	providedIn: 'root'
})
export class OverhaulService {
	private apiBaseUrl = 'http://localhost:8080/api/overhauls';

	constructor(private http: HttpClient) {}

	create(overhaul: Partial<Overhaul>) {
		return this.http.post<Overhaul>(this.apiBaseUrl, overhaul);
	}

	update(overhaul: Partial<Overhaul>) {
		const url = `${this.apiBaseUrl}/${overhaul.id}`;
		return this.http.put<Overhaul>(url, overhaul);
	}

	updateStatus(overhaulId: number, isPaid: boolean) {
		const url = `${this.apiBaseUrl}/${overhaulId}/isPaid?isPaid=${isPaid}`;
		return this.http.put<string>(url, undefined);
	}
}
