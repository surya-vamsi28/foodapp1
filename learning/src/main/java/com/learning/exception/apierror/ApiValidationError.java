package com.learning.exception.apierror;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false) // imt will not call super.hashcode/super.equals
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
	
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
		public ApiValidationError(String object,String message) {
			this.object = object;
			this.message = message;
		}
}
