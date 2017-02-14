/**
 * 
 */
package fr.epita.iamcoreTDas.tests.services;

import java.sql.SQLException;
import java.util.List;

import fr.epita.iamcoreTDas.datamodel.Identity;
import fr.epita.iamcoreTDas.exception.DAODeleteException;
import fr.epita.iamcoreTDas.exception.DAOInitializationException;
import fr.epita.iamcoreTDas.exception.DAOSaveException;
import fr.epita.iamcoreTDas.exception.DAOSearchException;
import fr.epita.iamcoreTDas.exception.DAOUpdateException;
import fr.epita.iamcoreTDas.service.dao.DAOResourceException;
import fr.epita.iamcoreTDas.service.dao.IdentityDAOFactory;
import fr.epita.iamcoretTDas.service.dao.IdentityDAOInterface;

/**
 * @author ZAC
 *
 */
public class TestDAO {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws DAOSaveException 
	 * @throws DAOInitializationException 
	 * @throws DAOSearchException 
	 * @throws DAOUpdateException 
	 * @throws DAODeleteException 
	 * @throws DAOResourceException 
	 */
	public static void main(String[] args) throws DAOSaveException, DAOInitializationException, DAOSearchException, DAOUpdateException, DAODeleteException, DAOResourceException {
		IdentityDAOInterface dao = IdentityDAOFactory.getIdentityDAO();
		System.out.println(dao.search(null));
		Identity identity = new Identity("James", "Watson", null);
		System.out.println("before save");
		dao.save(identity);
		List<Identity> identities = dao.search(identity);
		System.out.println("after save");
		
		System.out.println(identities);
		identity = identities.get(0);
		identity.setDisplayName("Johnny");
		dao.update(identity);
		
		System.out.println("after update");
		identities = dao.search(identity);
		System.out.println(identities);

		dao.delete(identity);
		
		System.out.println("after delete");
		identities = dao.search(identity);
		System.out.println(identities);
		dao.releaseResources();
		
		System.out.println("end of test");

	

		
	}
	
}
	

