import { TestBed } from '@angular/core/testing';
import { OverhaulService } from './overhaul.service';

describe('OverhaulService', () => {
	let service: OverhaulService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(OverhaulService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
