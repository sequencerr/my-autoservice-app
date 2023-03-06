export class FormComponent {
	idPattern = /[1-9]\d*/;
	pricePattern = /[1-9]\d*(\.\d+)?/;

	handleIdInput(event: Event) {
		this.handleInput(event.target, this.idPattern);
	}

	handlePriceInput(event: Event) {
		this.handleInput(event.target, this.pricePattern);
	}

	private handleInput(target: EventTarget | null, pattern: RegExp) {
		const input = target as HTMLInputElement;
		const cleared = input.value.match(pattern);
		input.value = cleared ? cleared[0] : '';
	}
}
