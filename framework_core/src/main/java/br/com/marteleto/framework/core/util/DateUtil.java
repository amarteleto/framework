package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	protected static transient Logger logger = Logger.getLogger(DateUtil.class.getName());
	
	public static String convertDateToString(String format, Date date){
        String result = null;
        try{
        	SimpleDateFormat df = new SimpleDateFormat(format, ResourceUtil.getLocale());
        	result = df.format(date);
        } catch (Exception ex) {
        	logger.severe("Fail: convertDateToString");
        }
        return result;
    }
    
    public static Date convertStringToDate(String format, String date){
    	Date result = null;
        try{
        	SimpleDateFormat df = new SimpleDateFormat(format, ResourceUtil.getLocale());
        	result = df.parse(date);
        } catch (Exception ex) {
        	logger.severe("Fail: convertStringToDate");
        }
        return result;
    }
    
    public static Date formatDate(String format, Date date){
    	Date result = null;
        try{
        	SimpleDateFormat df = new SimpleDateFormat(format, ResourceUtil.getLocale());
        	result = df.parse(df.format(date));
        } catch (Exception ex) {
        	logger.severe("Fail: formatDate");
        }
        return result;
    }
    
    public static Date currentDate(){
    	Calendar calendar = Calendar.getInstance(ResourceUtil.getLocale());
        return calendar.getTime();
    }
    
    public static Long dateDiff(Date initialDate, Date finalDate) {
    	Long result = null;
    	try {
    		if (initialDate == null) {
    			throw new DateTimeException("Start date not found.");
    		}
    		if (finalDate == null) {
    			throw new DateTimeException("Finish date not found.");
    		}
	        Calendar c1 = Calendar.getInstance(ResourceUtil.getLocale());  
	        c1.setTime(initialDate);
	        Calendar c2 = Calendar.getInstance(ResourceUtil.getLocale());  
        	c2.setTime(finalDate);	
	        result = (c2.getTimeInMillis() - c1.getTimeInMillis());
    	 } catch (DateTimeException ex) {
    		 logger.severe("Fail: dateDiff");
         }
    	return result;
    }
    
    public static Date dateAdd(Date date, Integer day) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, day);
	    return calendar.getTime();
    }
}