import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Detail } from 'src/model/Detail';

@Injectable({
	providedIn: 'root'
})
export class DetailService {
	private apiBaseUrl = 'http://localhost:8080/api/details';

	constructor(private http: HttpClient) {}

	create(detail: Partial<Detail>) {
		return this.http.post<Detail>(this.apiBaseUrl, detail);
	}

	update(detail: Partial<Detail>) {
		const url = `${this.apiBaseUrl}/${detail.id}`;
		return this.http.put<Detail>(url, detail);
	}
}
