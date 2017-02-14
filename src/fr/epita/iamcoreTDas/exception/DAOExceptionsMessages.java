package fr.epita.iamcoreTDas.exception;

/**
 * @author ZAC
 *This is the enum file for DAO exception messages
 */
public enum DAOExceptionsMessages {

		UNABLE_TO_READ_XML_FILE("unable to read the identities.xml file","XML01"),
		UNABLE_TO_READ_TEXT_FILE("unable to read the text file", "TXT01"),
		UNABLETOCONNECT ("unable to connect to the database", "DTB01"),
		
		;
		
		private String message;
		private String code;
		
		/**
		 * @param message
		 * @param code
		 */
		private DAOExceptionsMessages(String message, String code) {
			this.message = message;
			this.code = code;
		}
		
		/**
		 * @return
		 */
		public String getFormattedMessage(){
			return this.code + " : " + this.message;
		}

}
