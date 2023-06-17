package es.yoshibv.utils;

import java.util.List;

public class Utils {
	public static String placeholderParser(String message, List<String> placeholders, List<String> values) {
        int i = 0;
        message = message.replace('&', '§');
    	for(String p:placeholders) {
    		if(message.contains(p)) {
        	    message = message.replace(p, values.get(i));
        	    i++;
        	}
    	}
    	return message;
    }
	
	public static String colorCodeParser(String message) {
		message = message.replace('&', '§');
		return message;
	}
	
}
