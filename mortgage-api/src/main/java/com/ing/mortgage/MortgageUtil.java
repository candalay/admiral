package com.ing.mortgage;

import java.sql.Timestamp;
import java.util.Date;

public class MortgageUtil {
	
	private MortgageUtil (){
		
	}

	public static Timestamp getDateAsTimeStamp(Date date) {

		return new Timestamp(date.getTime());
	}

}
