package eu.sqooss.service.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import eu.sqooss.core.AlitheiaCore;

public class DeveloperDB {

	/**
	 * Return the entry in the Developer table that corresponds to the provided
	 * email. If the entry does not exist, it will be created and saved. If the
	 * email username (the part before @) exists in the database, then this
	 * record is updated with the provided email and returned.
	 * 
	 * @param email
	 *            The Developer's email
	 * @param sp The StoredProject this Developer belongs to
	 * @return A Developer record for the specified Developer or null when:
	 *         <ul>
	 *         <li>The passed StoredProject does not exist</li>
	 *         <li>The passed email is invalid syntactically</li>
	 *         <ul>
	 */
	public static Developer getDeveloperByEmail(String email, 
	        StoredProject sp) {
	    return DeveloperDB.getDeveloperByEmail(email, sp, true);
	}

	/**
	 * Return the entry in the Developer table that corresponds to the provided
	 * email. If the entry does not exist, then the parameter <tt>create</tt>
	 * controls whether it will be created and saved. If the email username (the
	 * part before @) exists in the database, then this record is updated with
	 * the provided email and returned.
	 * 
	 * @param email The Developer's email
	 * @param sp The StoredProject this Developer belongs to
	 * @return A Developer record for the specified Developer or null when:
	 *         <ul>
	 *         <li>The passed StoredProject does not exist</li>
	 *         <li>The passed email is invalid syntactically</li>
	 *         <ul>
	 */
	public static synchronized Developer getDeveloperByEmail(String email,
	        StoredProject sp, boolean create){
	    DBService dbs = AlitheiaCore.getInstance().getDBService();
	    
	    String paramProject = "project";
	    String paramEmail = "email";
	
	    StringBuffer q = new StringBuffer("select d ");
	    q.append(" from Developer d, DeveloperAlias da ");
	    q.append(" where da.developer = d ");
	    q.append(" and d.storedProject = :").append(paramProject);
	    q.append(" and da.email = :").append(paramEmail);
	    
	    Map<String,Object> parameterMap = new HashMap<String,Object>();
	    parameterMap.put(paramEmail, email);
	    parameterMap.put(paramProject, sp);
	    
	    List<Developer> devs = (List<Developer>) dbs.doHQL(q.toString(), parameterMap);
	    
	    /* Developer in the DB, return it */
	    if ( !devs.isEmpty() )
	        return devs.get(0);
	    
	    parameterMap.clear();
	    
	  /*  if (!email.contains("@"))
	        return null;
	    
	    String unameFromEmail = email.substring(0, email.indexOf('@'));
	    
	    if (unameFromEmail == "" || unameFromEmail == email)
	        return null;
	    
	    parameterMap.put("username", unameFromEmail);
	    parameterMap.put("storedProject", sp);
	    
	    devs = dbs.findObjectsByProperties(Developer.class, 
	            parameterMap);
	    
	    /* Developer's uname in table, update with email and return it *
	    if (!devs.isEmpty()) {
	        Developer d = devs.get(0);
	        d.addAlias(email);
	        return d;
	    }*/
	    
	    /* Try Ohloh */
	    String hash = DigestUtils.shaHex(email);
	    OhlohDeveloper od = OhlohDeveloper.getByEmailHash(hash);
	    
	    if (od != null) {
	        Developer d = DeveloperDB.getDeveloperByUsername(od.getUname(), sp, false);
	    
	        if (d != null) {
	            d.addAlias(email);
	            return d;
	        }
	    }
	    
	    if (!create)
	        return null;
	    
	    /* Developer email not in table, create it new developer*/ 
	    Developer d = new Developer();
	    d.setStoredProject(sp);
	    
	    /*Failure here probably indicates non-existing StoredProject*/
	    if ( !dbs.addRecord(d) )
	        return null;
	    
	    d.addAlias(email);
	    
	    return d;
	}

	/**
	 * Return the entry in the Developer table that corresponds to the provided
	 * username. If the entry does not exist, it will be created and saved. If
	 * the username matches the email of an existing developer in the database,
	 * then this record is updated with the provided username and returned.
	 * 
	 * @param username The Developer's username
	 * @param sp The StoredProject this Developer belongs to
	 * @return A Developer record for the specified Developer or null on failure
	 */
	public static Developer getDeveloperByUsername(String username, 
	        StoredProject sp) {
	    return DeveloperDB.getDeveloperByUsername(username, sp, true);
	}

	/**
	 * Return the entry in the Developer table that corresponds to the provided
	 * username. If the entry does not exist, then the parameter <tt>create</tt>
	 * controls whether it will be created and saved. If the username matches
	 * the email of an existing developer in the database, then this record is
	 * updated with the provided username and returned.
	 * 
	 * @param username The Developer's username
	 * @param sp The StoredProject this Developer belongs to
	 * @param create Create the developer entry if not found?
	 * 
	 * @return A Developer record for the specified Developer or null on failure
	 *         to retrieve or create an entry.
	 * 
	 */    
	@SuppressWarnings("unchecked")
	public static synchronized Developer getDeveloperByUsername(String username,
	        StoredProject sp, boolean create) {
		
	    DBService dbs = AlitheiaCore.getInstance().getDBService();
	
	    Map<String, Object> parameterMap = new HashMap<String, Object>();
	    parameterMap.put("username", username);
	    parameterMap.put("storedProject", sp);
	
	    List<Developer> devs = dbs.findObjectsByProperties(Developer.class,
	                                                       parameterMap);
	
	    /*
	     * Developer in the DB, return it Username + storedproject is unique, so
	     * only one record can be returned by the query
	     */
	    if (!devs.isEmpty())
	        return devs.get(0);
	    
	    /*
	     * Try to find a Developer whose email starts with username
	     * 
	     * TODO: "like" is NOT a Hibernate keyword. The following query might
	     * only work with certain databases (tested with mysql, postgres and 
	     * derby).
	     */
	    /*devs = (List<Developer>) dbs.doHQL("from Developer as foo where email like " +
	    		"'%" +username+ "%' and storedProject.id=" + sp.getId() );
	
	    for (Developer d : devs) {
	        Set<DeveloperAlias> aliases = d.getAliases();
	        for (DeveloperAlias da : aliases) {
	            /* Ok got one, update the username *
	            if (da.getEmail().startsWith(username)) {
	                d.setUsername(username);
	                return d;
	            }
	        }
	    }*/
	
	    if (!create)
	        return null;
	    
	    /* Developer not in table, create new developer */
	    Developer d = new Developer();
	
	    d.setUsername(username);
	    d.setStoredProject(sp);
	
	    /*Failure here probably indicates non-existing StoredProject*/
	    if (!dbs.addRecord(d))
	        return null;
	
	    return d;
	}

	/**
	 * Get a developer entry by developer name. If the entry does not exist,
	 * then the parameter <tt>create</tt> controls whether it will be created
	 * and saved.
	 * 
	 * @param name
	 * @param sp
	 * @param create
	 * @return
	 */
	public static synchronized Developer getDeveloperByName(String name, 
	        StoredProject sp, boolean create) {
	    
	    DBService dbs = AlitheiaCore.getInstance().getDBService();
	
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("name", name);
	    params.put("storedProject", sp);
	
	    List<Developer> devs = dbs.findObjectsByProperties(Developer.class,params);
	    
	    /* This code assumes that each name is unique in a project*/
	    if (devs.size() > 0)
	        return devs.get(0);
	    
	    if (!create)        
	        return null;
	    
	    Developer d = new Developer();
	    d.setName(name);
	    if (!dbs.addRecord(d))
	        return null;
	    
	    return d;
	}

}
