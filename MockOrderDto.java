package co.wigroup.order.common.order;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CreateMockOders")
public class MockOrderDto {

	 	private final Long	orderId;
	    private final String	orderReferenceNumber;
	    private Integer orderQuantity;
	    private final Integer orderTotal;
	    private final String  orderTime;
	    private final String profileId;
	    
	    public MockOrderDto(MockOrder mockOrder) {
			super();
			this.orderId = mockOrder.getId();
			this.orderReferenceNumber = mockOrder.getOrderReferenceNumber();
			this.orderQuantity = mockOrder.getOrderQuantity();
			this.orderTotal = mockOrder.getOrderTotal();
			this.orderTime = mockOrder.getOrderTime();
			this.profileId = mockOrder.getProfileId();
		}
	    
		public Integer getOrderQuantity() {
			return orderQuantity;
		}
		public void setOrderQuantity(Integer orderQuantity) {
			this.orderQuantity = orderQuantity;
		}
		public Long getOrderId() {
			return orderId;
		}
		public String getOrderReferenceNumber() {
			return orderReferenceNumber;
		}
		public Integer getOrderTotal() {
			return orderTotal;
		}
		public String getOrderTime() {
			return orderTime;
		}

		public String getProfileId() {
			return profileId;
		}
}
