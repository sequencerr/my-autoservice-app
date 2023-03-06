import { TestBed } from '@angular/core/testing';
import { RepairerService } from './repairer.service';

describe('RepairerService', () => {
	let service: RepairerService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(RepairerService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
