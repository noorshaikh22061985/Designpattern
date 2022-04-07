package co.wigroup.order.common.order;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "GetMockProducts")
public class MockProductDto {

	 	private final String	skuId;
	    private final String	skuName;
	    private final String  skuDescription;
	    
	    public String getSkuId() {
			return skuId;
		}

		public String getSkuName() {
			return skuName;
		}

		public String getSkuDescription() {
			return skuDescription;
		}

		public MockProductDto(MockProduct mockProduct) {
			super();
			this.skuId = mockProduct.getSku();
			this.skuName = mockProduct.getName();
			this.skuDescription = mockProduct.getDescription();
	    
		}
}
