package co.wigroup.order.common.order;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.wigroup.order.common.repository.Persistable;

@Entity
@Table(name = "mock_profile_items")
public class MockProfileItem implements Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2559011302438878921L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
	private Long	id;
	
	@Basic(optional = false)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="order_id", nullable=false)
    private MockProfile mockProfile;
	
	@Basic(optional = true)
    @Column(name = "item_id")
    private String	itemId;
	
	@Basic(optional = true)
    @Column(name = "product_sku")
    private String	productSku;
	
	@Basic(optional = true)
    @Column(name = "quantity")
    private Integer quantity;
	
	@Basic(optional = true)
    @Column(name = "line_number")
    private Integer lineNumber;
	
	 public MockProfileItem() {}
	 

	    public MockProfileItem(MockProfile mockProfile, MockProfileProducts item) {
	        this.mockProfile        = mockProfile;
	        this.itemId = item.getItemId();
	        this.lineNumber = item.getLineNumber();
	        this.productSku = item.getSku();
	        this.quantity = item.getQuantity();
	        
	    }

	
	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
     * This function will retrieve the order id.
     *
     * @access public
     * @return Integer id
     */
    public Long getOrderId()
    {
        return mockProfile.getId();
    }

    /**
     * This function will set an order id
     *
     * @access public
     * @param  orderId An order unique identifier
     * @throws IllegalArgumentException
     * @return void
     */
    public void setOrderId(Long orderId)
    {
        if (orderId == null)
        {
            throw new IllegalArgumentException("orderId parameter should not be null");
        }
        else
        {
            this.mockProfile = (this.mockProfile == null) ? new MockProfile() : this.mockProfile;
            this.mockProfile.setId(orderId);
        }
    }
    
    /**
     * @return the Mock Profile
     */
    public MockProfile getMockProfile() {
        return mockProfile;
    }

    /**
     * @param mockProfile the mockprofile to set
     */
    public void setMockProfile(MockProfile mockProfile) {
        this.mockProfile = mockProfile;
    }


	@Override
    public Long getId()
    {
        return id;
    }

    /**
     * This function will set an order id
     *
     * @access public
     * @param  id An order unique identifier
     * @throws IllegalArgumentException
     * @return void
     */
    public void setId(Long id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException("id parameter should not be null");
        }
        else
        {
            this.id = id;
        }
    }

}
