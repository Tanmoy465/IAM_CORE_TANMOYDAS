/**
 * 
 */
package fr.epita.iamcoreTDas.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iamcoreTDas.datamodel.Identity;
import fr.epita.iamcoreTDas.exception.DAODeleteException;
import fr.epita.iamcoreTDas.exception.DAOExceptionsMessages;
import fr.epita.iamcoreTDas.exception.DAOInitializationException;
import fr.epita.iamcoreTDas.exception.DAOSaveException;
import fr.epita.iamcoreTDas.exception.DAOSearchException;
import fr.epita.iamcoreTDas.exception.DAOUpdateException;
import fr.epita.iamcoretTDas.service.dao.IdentityDAOInterface;

/**
 * This class is dealing with the Identity Persistence using a JDBC back-end
 * 
 * @author ZAC
 *
 */
public class IdentityJDBCDAO implements IdentityDAOInterface{

	private static final String IDENTITY_UID = "IDENTITY_UID";
	//private static final String QUERY_ALL_IDENTITIES = "select * from IDENTITIES where IDENTITY_DISPLAYNAME = ? and IDENTITY_EMAIL = ?";
	private static final String QUERY_ALL_IDENTITIES = "select * from IDENTITIES";
	private static final String QUERY_UPDATE_IDENTITY = 
			"update IDENTITIES set IDENTITY_EMAIL=?,IDENTITY_DISPLAYNAME=? where IDENTITY_UID=?";
	private static final String QUERY_DELETE_IDENTITY = 
			"delete from IDENTITIES where IDENTITY_UID=?";
	private static final String COLUMN_IDENTITY_DISPLAYNAME = "IDENTITY_DISPLAYNAME";
	private static final String CONF_USERNAME = "tom";
	private static final String CONF_PASSWORD = "tom";
	private static final String CONF_CONNECTION_STRING = "jdbc:derby://localhost:1527/Identities;create=true";
	private Connection connection;

	private static IdentityJDBCDAO instance;
	
	private IdentityJDBCDAO() throws DAOInitializationException {
		try {
			this.connection = getConnection();
		} catch (SQLException e) {
			DAOInitializationException die = new DAOInitializationException(
					DAOExceptionsMessages.UNABLETOCONNECT);
			die.initCause(e);
			throw die;
		}
	}
	
	/**
	 * Singleton pattern
	 * @return
	 * @throws DAOInitializationException
	 */
	public static IdentityJDBCDAO getInstance() throws DAOInitializationException{
		if (instance == null){
			instance = new IdentityJDBCDAO();
		}
		return instance;
	
	
	}

	public List<Identity> readAll() throws SQLException {
		
		//list of identities
		List<Identity> identities = new ArrayList<Identity>();

		PreparedStatement pstmtselect = connection.prepareStatement(QUERY_ALL_IDENTITIES);
		ResultSet rs = pstmtselect.executeQuery();
		while (rs.next()) {
			String displayName = rs.getString(COLUMN_IDENTITY_DISPLAYNAME);
			String uid = String.valueOf(rs.getString(IDENTITY_UID));
			String email = rs.getString("IDENTITY_EMAIL");
			Identity identity = new Identity(uid, displayName, email);
			identities.add(identity);
			
		}
		pstmtselect.close();
		return identities;

	}
	
	
	public List<Identity> search(Identity criteria) throws DAOSearchException {
		List<Identity> identities = new ArrayList<>();
		try {
			if(criteria == null){
				criteria = new Identity("","","");
			}
			Connection connection = getConnection();
			// prepare the query
			PreparedStatement prepareStatement = connection
					.prepareStatement(QUERY_ALL_IDENTITIES);
			prepareStatement.setString(1, criteria.getDisplayName());
			prepareStatement.setString(2, criteria.getEmail());
			prepareStatement.setString(3, criteria.getUid());  //check
			
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				String displayName = rs.getString(COLUMN_IDENTITY_DISPLAYNAME);
				String uid = rs.getString(IDENTITY_UID);
				String email = rs.getString("IDENTITY_EMAIL");
				Identity identity = new Identity(displayName, email, uid);
				System.out.println("displayName"+ "uid"+"email");
				identities.add(identity);
			}

		} catch (SQLException e) {
			DAOSearchException searchException = new DAOSearchException("Problem in seaching");
			searchException.initCause(e);
			throw searchException;
		}
		System.out.println(identities);
		return identities;


	}

	public void save(Identity identity) throws DAOSaveException {
		try {
			Connection connection = getConnection();
			PreparedStatement pstmt = 
					connection.prepareStatement("insert into IDENTITIES (IDENTITY_DISPLAYNAME, IDENTITY_EMAIL) values( ?, ?)");
			
			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			pstmt.execute();
			
			
		} catch (SQLException sqle) {
			DAOSaveException dse = new DAOSaveException("problem...");
			dse.initCause(sqle);
			throw dse;
		}

	}

	private Connection getConnection() throws SQLException {
		if (this.connection == null || this.connection.isClosed()){
			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.connection = DriverManager.getConnection(CONF_CONNECTION_STRING,
					CONF_USERNAME, CONF_PASSWORD);
		}
		return this.connection;
		
	}
	
	
	public void update(Identity identity) throws DAOUpdateException{
		PreparedStatement prepareStatement;
		try {
			prepareStatement = getConnection().prepareStatement(QUERY_UPDATE_IDENTITY);
			prepareStatement.setString(1, identity.getEmail() );
			prepareStatement.setString(2, identity.getDisplayName() );
			prepareStatement.setString(3, identity.getUid());
			prepareStatement.execute();
		} catch (SQLException e) {
			DAOUpdateException due = new DAOUpdateException("Problem in Updating");
			due.initCause(e);
			throw due;
		}
	}
	
	public void delete(Identity identity) throws DAODeleteException{
		PreparedStatement prepareStatement;
		try {
			prepareStatement = getConnection().prepareStatement(QUERY_DELETE_IDENTITY);
			prepareStatement.setString(1, identity.getUid());
			prepareStatement.execute();
		} catch (SQLException e) {
			DAODeleteException dde = new DAODeleteException("Problem in Deleting");
			dde.initCause(e);
			throw dde;
		}
	}
	
	
	public void releaseResources() throws DAOResourceException{
		if (this.connection == null){
			return;
		}
		try {
			this.connection.close();
		} catch (SQLException e) {
			DAOResourceException re = new DAOResourceException();
			re.initCause(e);
			throw re;
		}
	}

	// insert into "TOM"."IDENTITIES" ("IDENTITY_DISPLAYNAME", "IDENTITY_EMAIL")
	// values('Quentin Decayeux', 'qdeca@natsystem.fr')
}
