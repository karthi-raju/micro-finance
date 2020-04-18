package com.spring.microfinance.util;

public class MicroFinanceUtil {

	public static String getFieldNameFromMessage(String message) {
		String fieldName = null;
		if (message.contains(Constant.AADHAR_NUMBER_INDEX))
			fieldName = Constant.AADHAR_NUMBER;
		else if (message.contains(Constant.MOBILE_NUMBER_INDEX))
			fieldName = Constant.MOBILE_NUMBER;
		return fieldName;
	}
}
