package eu.sqooss.developer.test;

import static org.junit.Assert.*;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.TreeSet;

import javassist.expr.NewExpr;

import org.junit.BeforeClass;
import org.junit.Test;

import eu.sqooss.impl.service.admin.AdminServiceImpl;
import eu.sqooss.impl.service.db.DBServiceImpl;
import eu.sqooss.service.admin.actions.RunTimeInfo;
import eu.sqooss.service.db.Developer;
import eu.sqooss.service.db.DeveloperAlias;
import eu.sqooss.service.db.DeveloperDB;
import eu.sqooss.service.db.StoredProject;

public class DeveloperDBTest {

    static DBServiceImpl impl;
    static long failid;
    static long successid;
	
	 @BeforeClass
	    public static void setUp() {
	        impl = new DBServiceImpl();
	    }

	
	@Test
	public void testGetDeveloperByEmailStringStoredProject() {
		String email = "boooom";
		
		Developer developer= new Developer();
		DeveloperAlias alias = new DeveloperAlias();
		alias.setEmail(email);
		
		StoredProject storedProject = new StoredProject();
		storedProject.setDevelopers(new HashSet<Developer>());
		storedProject.getDevelopers().add(developer);
		
		developer.setAliases(new HashSet<DeveloperAlias>());
		developer.getAliases().add(alias);
		
        impl.addRecord(developer);
        
        DeveloperDB dbd = new DeveloperDB();
       
        Developer retrievedDeveloper = dbd.getDeveloperByEmail(email, storedProject);
		
        assertNotNull(retrievedDeveloper);
        
	}

	@Test
	public void testGetDeveloperByEmailStringStoredProjectBoolean() {
		String email = "boooom";
		
		Developer developer= new Developer();
		DeveloperAlias alias = new DeveloperAlias();
		alias.setEmail(email);
		
		StoredProject storedProject = new StoredProject();
		storedProject.setDevelopers(new HashSet<Developer>());
		storedProject.getDevelopers().add(developer);
		
		developer.setAliases(new HashSet<DeveloperAlias>());
		developer.getAliases().add(alias);
		
        impl.addRecord(developer);
        
        DeveloperDB dbd = new DeveloperDB();
       
        Developer retrievedDeveloper;
        retrievedDeveloper = dbd.getDeveloperByEmail(email, storedProject, false);
		
        assertNull(retrievedDeveloper);
        
        retrievedDeveloper = dbd.getDeveloperByEmail(email, storedProject, true);
        
        assertNotNull(retrievedDeveloper);
	}

	@Test
	public void testGetDeveloperByUsernameStringStoredProject() {
		String username = "name";
		
		Developer developer= new Developer();

		developer.setUsername(username);
		
		StoredProject storedProject = new StoredProject();
		storedProject.setDevelopers(new HashSet<Developer>());
		storedProject.getDevelopers().add(developer);

        impl.addRecord(developer);
        
        DeveloperDB dbd = new DeveloperDB();
       
        Developer retrievedDeveloper = dbd.getDeveloperByUsername(username, storedProject);
		
        assertNull(retrievedDeveloper);
        
        
	}

	@Test
	public void testGetDeveloperByUsernameStringStoredProjectBoolean() {
		String username = "name";
		
		Developer developer= new Developer();

		developer.setUsername(username);
		
		StoredProject storedProject = new StoredProject();
		storedProject.setDevelopers(new HashSet<Developer>());
		storedProject.getDevelopers().add(developer);

        impl.addRecord(developer);
        
        DeveloperDB dbd = new DeveloperDB();
       
        Developer retrievedDeveloper;
        retrievedDeveloper = dbd.getDeveloperByUsername(username, storedProject, false);
		
        assertNull(retrievedDeveloper);
        
        retrievedDeveloper = dbd.getDeveloperByUsername(username, storedProject, true);
        
        assertNotNull(retrievedDeveloper);
	}

	@Test
	public void testGetDeveloperByName() {
		String name = "name";
		
		Developer developer= new Developer();

		developer.setName(name);
		
		StoredProject storedProject = new StoredProject();
		storedProject.setDevelopers(new HashSet<Developer>());
		storedProject.getDevelopers().add(developer);

        impl.addRecord(developer);
        
        DeveloperDB dbd = new DeveloperDB();
        
        Developer retrievedDeveloper;
        retrievedDeveloper = dbd.getDeveloperByUsername(name, storedProject, false);
		
        assertNull(retrievedDeveloper);
        
        retrievedDeveloper = dbd.getDeveloperByUsername(name, storedProject, true);
        
        assertNotNull(retrievedDeveloper);
	}

}
