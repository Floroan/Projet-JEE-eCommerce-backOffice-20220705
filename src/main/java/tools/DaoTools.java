package tools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoTools {

	// COUNT ALL PRODUCT CLICKS BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return String
	public String countAllClicksByOneUserPerEachMonthOfTheCurrentYearForOneTable (int fk_user, String tableName) {
		
		String s = "";
		String s2 = "";
		String s31 = "";
		ArrayList<String> s31Col = new ArrayList<>();
		String s30 = "";
		ArrayList<String> s30Col = new ArrayList<>();	
		
		String year = DateManipulator.dateGetCurrentYYYY();
		
		try {
			
			for (int i = 1; i <= 12; i++) {
				
				// Février année bisextile ???
				if ( i == 2 ) {
					
					PreparedStatement ps = Database.connexion
							.prepareStatement("SELECT COUNT(*) FROM `" + tableName + "` WHERE `fk_user`=? "
									+ "AND `date` BETWEEN \"" + year + "-" + i + "-01\" AND \"" + year + "-" + i + "-27\"");
					ps.setInt(1, fk_user);
					
					ResultSet rs = ps.executeQuery();
					
					rs.next();
					
					Integer c = rs.getInt("COUNT(*)");
					
					s2 += String.valueOf(c) + ",";
					
				}

				// Mois à 31 jours
				if ( i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12 ) {
					
					PreparedStatement ps = Database.connexion
							.prepareStatement("SELECT COUNT(*) FROM `" + tableName + "` WHERE `fk_user`=? "
									+ "AND `date` BETWEEN \"" + year + "-" + i + "-01\" AND \"" + year + "-" + i + "-31\"");
					ps.setInt(1, fk_user);
					
					ResultSet rs = ps.executeQuery();
					
					rs.next();
					
					Integer c = rs.getInt("COUNT(*)");
					
					s31 = String.valueOf(c) + ",";
					s31Col.add(s31);
					
				}
				
				// Mois à 30 jours
				if ( i == 4 || i == 6 || i == 9 || i == 11 ) {
					
					PreparedStatement ps = Database.connexion
							.prepareStatement("SELECT COUNT(*) FROM `" + tableName + "` WHERE `fk_user`=? "
									+ "AND `date` BETWEEN \"" + year + "-" + i + "-01\" AND \"" + year + "-" + i + "-31\"");
					ps.setInt(1, fk_user);
					
					ResultSet rs = ps.executeQuery();
					
					rs.next();
					
					Integer c = rs.getInt("COUNT(*)");
					
					s30 = String.valueOf(c) + ",";
					s30Col.add(s30);
					
				}
			}
//				System.out.println("s2 : " + s2);
//				System.out.println("s31Col : " + s31Col);
//				System.out.println("s30Col : " + s30Col);
			s = s31Col.get(0) 		// janvier
					+ s2 			// février
					+ s31Col.get(1) // mars
					+ s30Col.get(0) // avril
					+ s31Col.get(2) // mai
					+ s30Col.get(1) // juin
					+ s31Col.get(3) // juillet
					+ s31Col.get(4) // août
					+ s30Col.get(2) // septembre
					+ s31Col.get(5) // octobre
					+ s30Col.get(3) // novembre
					+ s31Col.get(6);// décembre
			
			s = s.substring( 0, s.length() - 1 ); // retirer la dernière virgule
//			System.out.println("s : " + s);
			
			return s;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
}
