import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Car } from 'src/model/Car';

@Injectable({
	providedIn: 'root'
})
export class CarService {
	private apiBaseUrl = 'http://localhost:8080/api/cars';

	constructor(private http: HttpClient) {}

	create(car: Partial<Car>) {
		return this.http.post<Car>(this.apiBaseUrl, car);
	}

	update(car: Partial<Car>) {
		const url = `${this.apiBaseUrl}/${car.id}`;
		return this.http.put<Car>(url, car);
	}
}
