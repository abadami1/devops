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
@Table(name="person")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1188662748036168216L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		if(o != null && o instanceof Person){
			Person other = (Person)o;
			isEquals = new EqualsBuilder()
			.append(this.email, other.email)
			.append(this.firstName, other.firstName)
			.isEquals();
		}
		return isEquals;
	}
}
