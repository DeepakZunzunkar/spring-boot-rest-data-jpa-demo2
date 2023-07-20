package com.dz.app.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class AppUtility {

	public static DecimalFormat decif=new DecimalFormat("$#0.00");
	
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getDoubleValueFormated(Double val){
		
		return decif.format(val);
	}
}
