/**
 * 
 */
package exp.devops.sisd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author abadami
 *
 */
@Entity
@Table(name="event")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6762274710027488673L;
	
	public static final String TIME_FORMAT = "HH:mm MM-dd-yyyy";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	@DateTimeFormat(pattern=TIME_FORMAT)
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	@DateTimeFormat(pattern=TIME_FORMAT)
	private Date endTime;
	
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER, targetEntity=Address.class)
	private Address address;
	
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER, targetEntity=Person.class)
	private Person organizer;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the organizer
	 */
	public Person getOrganizer() {
		return organizer;
	}

	/**
	 * @param organizer the organizer to set
	 */
	public void setOrganizer(Person organizer) {
		this.organizer = organizer;
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
		if(o != null && o instanceof Event){
			Event other = (Event)o;
			isEquals = new EqualsBuilder()
			.append(this.getName(), other.getName())
			.append(this.getStartTime(), other.getStartTime())
			.append(this.getEndTime(), other.getEndTime())
			.isEquals();
		}
		return isEquals;
	}
	
}
