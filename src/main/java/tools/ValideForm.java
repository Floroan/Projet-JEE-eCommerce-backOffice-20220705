package tools;

import java.io.ObjectInputValidation;

public class ValideForm {

	
	
	public static boolean validField(String o, int constLength) {
		
		if(o.isEmpty()) {
			return false;
		}else if (o.length() <= constLength) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isGoodLength(String o, int constLength) {
		
		if(o.isEmpty()) {
			return false;
		}else if (o.length() <= constLength) {
			return true;
		}else {
			return false;
		}
	}
	
}
