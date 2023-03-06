export interface Order {
	id: number;
	description: string;
	price: number;
	acceptationDate: Date;
	completionDate: Date;
	status: OrderStatus;
	carId: number;
	overhaulIds: number[];
	detailIds: number[];
}

export enum OrderStatus {
	ACCEPTED,
	IN_PROGRESS,
	COMPLETED_SUCCESSFULLY,
	COMPLETED_UNSUCCESSFULLY,
	PAID
}
