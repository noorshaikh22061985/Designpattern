package co.wigroup.order.common.order;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CreateMockProfileItemDto")
public class MockProfileItemDto {

	private String	itemId;
	private String	productSku;
	private Integer quantity;
	private Integer lineNumber;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getProductSku() {
		return productSku;
	}
	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
}
