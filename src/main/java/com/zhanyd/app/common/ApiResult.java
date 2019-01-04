package com.zhanyd.app.common;


public class ApiResult<T> {

	private int code;
	private String message;
	private T data;
	
	public ApiResult(){
		
	}
	
	public ApiResult(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
	}

	public ApiResult(ResultEnum resultEnum, T data) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
		this.data = data;
	}
	
	
	public ApiResult<T> success(T data){
		this.code = ResultEnum.SUCCESS.getCode();
		this.message = ResultEnum.SUCCESS.getMessage();
		this.data = data;
		return this;
	}
	
	public ApiResult<T> fail(T data){
		this.code = ResultEnum.SERVER_ERROR.getCode();
		this.message = ResultEnum.SERVER_ERROR.getMessage();
		this.data = data;
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
