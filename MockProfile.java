package co.wigroup.order.common.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.wigroup.order.common.repository.Persistable;

@Entity
@Table(name = "mock_profile")
public class MockProfile implements Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2990443083478384759L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
	private Long	id;

	@Basic(optional = true)
    @Column(name = "profile_id")
    private String profileId;
	
	@Basic(optional = true)
    @Column(name = "surname")
    private String	surname;
    
	@Basic(optional = true)
    @Column(name = "primary_contact")
    private String	primaryContact;
    
	@Basic(optional = true)
    @Column(name = "name")
    private String	name;
    
	@Basic(optional = true)
    @Column(name = "order_reference")
    private String	orderReferenceNumber;
	
	@Basic(optional = true)
    @OneToMany(fetch=FetchType.EAGER, mappedBy="mockProfile", cascade=CascadeType.ALL)
    private List<MockProfileItem> profileItems = new ArrayList<MockProfileItem>();

	public List<MockProfileItem> getProfileItems() {
		return profileItems;
	}

	public void setProfileItems(List<MockProfileItem> profileItems) {
		this.profileItems = profileItems;
	}

	public String getOrderReferenceNumber() {
		return orderReferenceNumber;
	}

	public void setOrderReferenceNumber(String orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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


}
