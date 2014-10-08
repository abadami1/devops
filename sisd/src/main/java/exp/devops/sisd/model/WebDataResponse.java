package exp.devops.sisd.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * 
 * @author abadami
 *
 */
public class WebDataResponse implements WebResponse {

	private boolean success = false;
	
	private String message;
	
	private Object responsedata;
	
	private int total;
	@Override
	public boolean getSuccess() {
		return success;
	}
	@Override
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public int getTotal() {
		return total;
	}
	@Override
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public void setMessage(String msg) {
		this.message = msg;
	}
	@Override
	public Object getResponseData() {
		return responsedata;
	}
	@Override
	public void setResponseData(Object d) {
		this.responsedata = d;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hb = new HashCodeBuilder()
		.append(this.getSuccess())
		.append(this.getMessage());
		if(this.getResponseData() != null){
			hb.append(this.getResponseData().hashCode()).hashCode();
		}			
		return hb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEquals = false;
		if(obj == this){
			return true;
		}
		if(obj != null && obj instanceof WebDataResponse){
			WebDataResponse other = (WebDataResponse)obj;
			EqualsBuilder eb =  new EqualsBuilder().append(this.getSuccess(), other.getSuccess())
						.append(this.getMessage(), other.getMessage());
			if(this.getResponseData() != null){
				eb.append(this.getResponseData().hashCode(), 
						other.getResponseData().hashCode()).isEquals();
			}
			isEquals = eb.isEquals();
		}
		return isEquals;
	}
}
