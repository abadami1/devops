package exp.devops.sisd.model;

/**
 * General Interface definition for all Web Responses. 
 * 
 * @author abadami
 *
 */
public interface WebResponse {

	public boolean getSuccess();
	
	public void setSuccess(boolean success);
	
	public String getMessage();
	
	public void setMessage(String msg);
	
	public Object getResponseData();
	
	public void setResponseData(Object data);
	
	public int getTotal();
	
	public void setTotal(int size);

}