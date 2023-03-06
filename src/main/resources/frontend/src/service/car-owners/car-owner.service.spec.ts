import { TestBed } from '@angular/core/testing';
import { CarOwnerService } from './car-owner.service';

describe('CarOwnerService', () => {
	let service: CarOwnerService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(CarOwnerService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
