package tools;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateManipulator {

	// private static Date d;

		public static Date dateConvertToSql(java.util.Date d) {
			
			long timeInMilliSeconds = d.getTime();
			java.sql.Date date = new java.sql.Date(timeInMilliSeconds);
			return date;
			
		}
		
		public static String dateConvertToDDmmYYYY ( Date d ) {
			
			DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");		
			String date = sourceFormat.format( d );
			
			return date;
			
		}
		
		public static String dateGetCurrentYYYY () {
			
			System.currentTimeMillis();
			
			SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
			
			Date date = new Date(System.currentTimeMillis());
			
			String year = formatter.format(date);
			
			return year;
		}
}
