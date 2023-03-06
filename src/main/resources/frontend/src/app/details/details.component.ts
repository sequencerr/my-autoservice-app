import { Component } from '@angular/core';
import { Detail } from 'src/model/Detail';
import { DetailService } from 'src/service/details/detail.service';
import { FormComponent } from '../form.component';

@Component({
	selector: 'app-details',
	templateUrl: './details.component.html',
	styleUrls: ['./details.component.scss']
})
export class DetailsComponent extends FormComponent {
	formModelCreate: Partial<Detail> = {};
	formModelUpdate: Partial<Detail> = {};

	constructor(private detailService: DetailService) {
		super();
	}

	create() {
		this.detailService.create(this.formModelCreate).subscribe();
	}

	update() {
		this.detailService.update(this.formModelUpdate).subscribe();
	}
}
