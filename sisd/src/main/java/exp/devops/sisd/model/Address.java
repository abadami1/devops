/**
 * 
 */
package exp.devops.sisd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author abadami
 *
 */
@Entity
@Table(name="address")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3474716773871013244L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String street1;
	
	@Column
	private String street2;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String zip;
	
	@Column(nullable = false)
	private String country;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * @param street1 the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(this.getId()).toHashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEquals = false;
		if(o == this)
			return true;
		if(o != null && o instanceof Address){
			Address other = (Address)o;
			isEquals = new EqualsBuilder()
			.append(this.street1, other.street1)
			.append(this.zip, other.zip)
			.isEquals();
		}
		return isEquals;
	}
	
}
