package service_archivage;

import dao.GenericDAO;

public class Service_Archivage {

	
	public static void archiver(int id,  int statut, String table) {
		GenericDAO genDao = new GenericDAO();
		genDao.archiveById(id, statut, table);
	}
}
