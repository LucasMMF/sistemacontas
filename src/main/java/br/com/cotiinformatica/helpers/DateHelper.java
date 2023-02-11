package br.com.cotiinformatica.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

	public static String getFirstDayOfMonth() {
		
		Calendar calendar = Calendar.getInstance();
		Date dataIni = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1).getTime();
		
		return new SimpleDateFormat("yyyy-MM-dd").format(dataIni);
	}
	
	public static String getLastDayOfMonth() {
		
		Calendar calendar = Calendar.getInstance();
		Date dataFim = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).getTime();
		
		return new SimpleDateFormat("yyyy-MM-dd").format(dataFim);
	}
	
}
