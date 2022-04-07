package co.wigroup.order.common.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CreateMockProfile")
public class MockProfileDto {

	private final Long	mockProfileId;
	private final String profileId;
	private final String	surname;
	private final String	primaryContact;
	private final String	name;
	private final String	orderReference;
	private List<MockProfileItemDto> products = new ArrayList<>();

	public MockProfileDto(MockProfile mockProfile) {
		this.mockProfileId = mockProfile.getId();
		this.profileId = mockProfile.getProfileId();
		this.surname = mockProfile.getSurname();
		this.primaryContact = mockProfile.getPrimaryContact();
		this.name = mockProfile.getName();
		
		//generate random number
		Random rand = new Random(); 
		Integer rand_int1 = rand.nextInt(1000);
		String orderRefNum = rand_int1.toString();
		this.orderReference = orderRefNum;
		List<MockProfileItem> list = mockProfile.getProfileItems();
		for(MockProfileItem item : list) {
			MockProfileItemDto itemDto = new MockProfileItemDto();
			itemDto.setItemId(item.getItemId());
			itemDto.setLineNumber(item.getLineNumber());
			itemDto.setProductSku(item.getProductSku());
			itemDto.setQuantity(item.getQuantity());
			this.products.add(itemDto);
		}
	}
	
	
	public Long getMockProfileId() {
		return mockProfileId;
	}
	public String getProfileId() {
		return profileId;
	}
	public String getSurname() {
		return surname;
	}
	public String getPrimaryContact() {
		return primaryContact;
	}
	public String getName() {
		return name;
	}

	//public List<MockProfileItem> getProfileItems() {
		//return profileItems;
	//}
	public String getOrderReferenceNumber() {
		return orderReference;
	}
	 
}
