package co.wigroup.order.common.order;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import co.wigroup.order.common.repository.Persistable;

@Entity
@Table(name = "mockorders")
public class MockOrder implements Persistable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -801071106513717795L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
	private Long	id;
    
    @Basic(optional = true)
    @Column(name = "order_reference")
    private String	orderReferenceNumber;
    
	@Basic(optional = true)
    @Column(name = "profile_id")
    private String profileId;
    
    @Basic(optional = true)
    @Column(name = "order_quantity")
    private Integer orderQuantity;
    
    @Basic(optional = true)
    @Column(name = "order_total")
    private Integer orderTotal;
    
    @Basic(optional = false)
    @Column(name = "order_time")
    private String orderTime;
    
	public String getOrderReferenceNumber() {
		return orderReferenceNumber;
	}
	public void setOrderReferenceNumber(String orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}
	public Integer getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public Integer getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
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
	    
	    /**
	     * This function will retrieve the order time
	     *
	     * @access public
	     * @return String orderTime
	     * @return void
	     */
	    public String getOrderTime()
	    {
	        return orderTime;
	    }

	    /**
	     * This function will set order time
	     *
	     * @access public
	     * @param  orderTime The time an order is due
	     * @throws IllegalArgumentException
	     * @return void
	     */
	    public void setOrderTime(String orderTime)
	    {
	        if (orderTime == null)
	        {
	            throw new IllegalArgumentException("order time should not be null");
	        }
	        else
	        {
	            this.orderTime = orderTime;
	        }
	    }



}
