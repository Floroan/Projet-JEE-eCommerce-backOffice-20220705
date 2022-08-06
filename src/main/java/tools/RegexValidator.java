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
	private static String regexSupplier = "^[a-zA-Z0-9\s]{1,50}$";
	private static String regexRudeWords = ""
			/*
			 * À FAIRE : 1 - Récupérer la liste des mots vulgaires :
			 * https://fr.wiktionary.org/wiki/Cat%C3%A9gorie:Termes_vulgaires_en_fran%C3%
			 * A7ais Et créer avec « StringBuffer ou autre » une méthod pour construire
			 * regexRudeWolds. private String buildRegexRudeWolds ( ArrayList<String>
			 * frenchRudeWordsList ) { return String s; } 2 - De même avec :
			 * https://github.com/splorp/wordpress-comment-blacklist
			 * 
			 */
			// https://stackoverflow.com/questions/18289929/regex-to-find-a-specific-word-in-a-string-in-java
			// . matches any character, *? is for zero or more times, \b is a word boundary.
			// https://docs.oracle.com/javase/1.5.0/docs/api/java/util/regex/Pattern.html
			// ENLEVER LES 2 DÉLIMITEURS DE MOT (\b) POUR OBTENIR LA SUITE DE CARACTÈRES
			// AJOUTER Pattern.CASE_INSENSITIVE DANS LA MÉTHODE
			+ ".*?chi.*?"
			+ "|.*?con.*?"
			+ "|.*?couille.*?"
			+ "|.*?encul.*?"
			+ "|.*?merd.*?"
			+ "|.*?niq.*?"
			+ "|.*?nik.*?"
			+ "|.*?pétasse.*?"
			+ "|.*?salop.*?";

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

	// SupplierValidator
	public static boolean supplierValidator(String supplier) {
		return Pattern.compile(regexSupplier)
				.matcher(supplier)
				.matches();
	}

	// RudeWordsValidator
	public static boolean RudeWordsValidator(String s) {

//		Pattern p = Pattern.compile(regexRudeWords, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
//		Matcher m = p.matcher(s);

//		int c = 0;
//		while (m.find())// finding pattern in regular expression
//		{
//			System.out.println(" Start : " + m.start() + ", End : " + m.end() + " Pattern group : " + m.group());
//		}
//
//		System.out.println(" number of Matches:  " + c);

		return Pattern.compile(regexRudeWords, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE)
				.matcher(s)
				.find();

//		return Pattern.compile(regexRudeWords)
//				.matcher(s)
//				.matches();
	}
	
	
	// method generic
	public static boolean matchMe(String matchMe, String constRegex) {
		return Pattern.compile(constRegex)
				.matcher(matchMe)
				.matches();
	}
}