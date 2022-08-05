package tools;


import java.util.regex.Pattern;

public class RegexValidator {

	private static String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static String regexName = "^[a-zA-Z]+[- ]?[a-zA-Z]+[- ]?[a-zA-Z]+$";
	// #[^0-9]# ou \D : Chaîne ne contenant PAS de chiffres.
	private static String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	// https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
	// ^ # start-of-string
	// (?=.*[0-9]) # a digit must occur at least once
	// (?=.*[a-z]) # a lower case letter must occur at least once
	// (?=.*[A-Z]) # an upper case letter must occur at least once
	// (?=.*[@#$%^&+=]) # a special character must occur at least once
	// (?=\S+$) # no whitespace allowed in the entire string
	// .{8,} # anything, at least eight places though
	// $ # end-of-string

	// EmailValidator
	public static boolean emailValidator(String email) {
		return Pattern.compile(regexEmail)
				.matcher(email)
				.matches();
	}

	// NameValidator
	public static boolean nameValidator(String name) {
		return Pattern.compile(regexName)
				.matcher(name)
				.matches();
	}

	// PasswordValidator
	public static boolean passwordValidator(String password) {
		return Pattern.compile(regexPassword)
				.matcher(password)
				.matches();
	}
	
	
	// method generic
	public static boolean matchMe(String matchMe, String constRegex) {
		return Pattern.compile(constRegex)
				.matcher(matchMe)
				.matches();
	}
}
