package fr.epita.iamcoretTDas.service.dao;

import java.util.List;

import fr.epita.iamcoreTDas.datamodel.Identity;
import fr.epita.iamcoreTDas.exception.DAODeleteException;
import fr.epita.iamcoreTDas.exception.DAOSaveException;
import fr.epita.iamcoreTDas.exception.DAOSearchException;
import fr.epita.iamcoreTDas.exception.DAOUpdateException;
import fr.epita.iamcoreTDas.service.dao.DAOResourceException;

public interface IdentityDAOInterface {
	
	public void save(Identity identity) throws DAOSaveException ;
	
	public List<Identity> search(Identity criteria) throws DAOSearchException ;
	
	public void update(Identity identity) throws DAOUpdateException;
	
	public void delete(Identity identity) throws DAODeleteException ;
	
	public void releaseResources() throws DAOResourceException;
	

}
