package exp.devops.sisd.web;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import exp.devops.sisd.model.WebResponse;



/**
 * 
 * @author rpauljr
 *
 */
public abstract class ApplicationController {	

	/** {@value} Expected Request Header Type; use headers=... */
	public static final String HTTP_ACCEPT_JSON_HEADER = 
			"Accept=application/json";
	/** {@value} Response Header Type; use produces=... */
	public static final String HTTP_RESPONSE_JSON_HEADER = 
			"application/json";

	public void handleException(WebResponse resp, Throwable t){
		if(resp == null || t == null){
			return;
		}
		resp.setMessage(t.getMessage());
		resp.setTotal(0);
		resp.setResponseData(null);
		resp.setSuccess(false);
	}

	public void handleException(WebResponse resp, String errorMessage){
		if(resp == null){
			return;
		}
		resp.setMessage(errorMessage);
		resp.setTotal(0);
		resp.setResponseData(null);
		resp.setSuccess(false);
	}

	public void handleSuccess(WebResponse resp, Object data, String message, int totalSize)
	{
		if(resp == null){
			return;
		}
		resp.setResponseData(data);
		resp.setSuccess(true);
		resp.setTotal(totalSize);					
		if(StringUtils.isEmpty(message)){
			resp.setMessage(Boolean.TRUE.toString());
		}else{
			resp.setMessage(message);}
	}



	@SuppressWarnings("rawtypes") 
	public void handleSuccess(WebResponse resp, Object data, String message){
		if(resp == null){
			return;
		}
		resp.setResponseData(data);
		resp.setSuccess(true);

		if(data != null && data instanceof Collection){
			resp.setTotal(((Collection)data).size());
		}else if(data != null){
			//If there data element is null it is assumed there is one element. 
			resp.setTotal(1);
		}

		if(StringUtils.isEmpty(message)){
			resp.setMessage(Boolean.TRUE.toString());
		}else{
			resp.setMessage(message);}
	}

	public void handleSuccess(WebResponse resp, Object data){
		handleSuccess(resp, data, "OK");
	}
}
