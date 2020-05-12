/*package com.cloud.platform.producer.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformResult<T> implements Result {

	private static final long serialVersionUID = 874200365941306385L;

	private Long status;

	private String message;

	private com.worldunion.clientServer.exchangedata.Data<T> data;

	private Long timestamp;

	private String ext;

	@SuppressWarnings("rawtypes")
	public static PlatformResult success() {
		PlatformResult result = new PlatformResult();
		result.setResultCode(ResultCode.SUCCESS);
		return result;
	}

	public static <T> PlatformResult<T> success(com.worldunion.clientServer.exchangedata.Data<T> data) {
		PlatformResult<T> result = new PlatformResult<T>();
		result.setResultCode(ResultCode.SUCCESS);
		result.setData(data);
		result.setTimestamp(new Date().getTime());
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static PlatformResult failure(ResultCode resultCode) {
		PlatformResult result = new PlatformResult();
		result.setResultCode(resultCode);
		return result;
	}

	public static <T> PlatformResult<T> failure(ResultCode resultCode,
			com.worldunion.clientServer.exchangedata.Data<T> data) {
		PlatformResult<T> result = new PlatformResult<T>();
		result.setResultCode(resultCode);
		result.setData(data);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static PlatformResult failure(String message) {
		PlatformResult result = new PlatformResult();
		result.setStatus(ResultCode.PARAM_IS_INVALID.code());
		result.setMessage(message);
		return result;
	}

	private void setResultCode(ResultCode code) {
		this.status = code.code();
		this.message = code.message();
	}

}*/