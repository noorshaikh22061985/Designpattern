package co.wigroup.order.common.order;

public class MockRejectDto {

	private final boolean success;

	public MockRejectDto(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
}
