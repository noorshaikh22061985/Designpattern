package co.wigroup.order.common.order;

public class MockAcceptDto {

	private final boolean success;

	public MockAcceptDto(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
}
