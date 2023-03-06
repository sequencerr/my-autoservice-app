import { Component } from '@angular/core';
import { Overhaul } from 'src/model/Overhaul';
import { OverhaulService } from 'src/service/overhauls/overhaul.service';
import { FormComponent } from '../form.component';

@Component({
	selector: 'app-overhauls',
	templateUrl: './overhauls.component.html',
	styleUrls: ['./overhauls.component.scss']
})
export class OverhaulsComponent extends FormComponent {
	formModelCreate: Partial<Overhaul> = {};
	formModelUpdate: Partial<Overhaul> = {};

	constructor(private overhaulService: OverhaulService) {
		super();
	}

	create() {
		this.overhaulService.create(this.formModelCreate).subscribe();
	}

	update() {
		const { id, isPaid } = this.formModelUpdate;
		if (isPaid !== undefined) {
			this.overhaulService.updateStatus(id!, isPaid!).subscribe();
			this.formModelUpdate.isPaid = undefined;
		}
		this.overhaulService.update(this.formModelUpdate).subscribe();
	}
}
