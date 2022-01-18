package de.northcodes.course.jsfspring.model;

public enum OrderState {
	
	INCOMPLETE("An incomplete order happens when a shopper reached the payment page, but did not complete the transaction."),
	PENDING("Customer started the checkout process, but did not complete it."),
	SHIPPED("Order has been shipped, but receipt has not been confirmed; seller has used the Ship Items action."),
	PARTIALLY_SHIPPED("Only some items in the order have been shipped, due to some products being pre-order only or other reasons."),
	REFUNDED("Seller has used the Refund action."),
	CANCELLED("Seller has cancelled an order, due to a stock inconsistency or other reasons."),
	DECLINED("Seller has marked the order as declined for lack of manual payment, or other reasons."),
	AWAITING_PAYMENT("Customer has completed checkout process, but payment has yet to be confirmed."),
	AWAITING_PICKUP("Order has been pulled, and is awaiting customer pickup from a seller-specified location."),
	AWAITING_SHIPMENT("Order has been pulled and packaged, and is awaiting collection from a shipping provider."),
	COMPLETED("Client has paid for their digital product and their file(s) are available for download."),
	AWAITING_FULFILLMENT("Customer has completed the checkout process and payment has been confirmed."),
	MANUAL_VERIFICATION_REQUIRED("Order on hold while some aspect needs to be manually confirmed."),
	DISPUTED("Customer has initiated a dispute resolution process for the PayPal transaction that paid for the order."),
	PARTIALLY_REFUNDED("Seller has partially refunded the order.");

	private final String description;
	
	private OrderState(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
