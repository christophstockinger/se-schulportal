/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author Christoph
 */
public interface DBKonstanten {
    // Konstanten für DB Login
    static final String DBNAMElive = "jdbc:mysql://localhost:3306/schulportal";
    static final String DBNAMElocal = "jdbc:derby://localhost:1527/schulportal";
    
    static final String USERlocal = "root";
    static final String USERlive = "thomas";
    
    static final String PASSWORDlocal = "root";
    static final String PASSWORDlive = "DiT2017+1";
    
    static final String DBNAME = DBNAMElocal;
    static final String USER = USERlocal;
    static final String PASSWORD = PASSWORDlocal;
}
