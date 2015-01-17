package eu.sqooss.mail.test;

import static org.junit.Assert.*;


import java.util.HashSet;


import java.util.Set;
import org.junit.Test;

import eu.sqooss.service.db.MailMessage;
import eu.sqooss.service.db.MailingList;
import eu.sqooss.service.db.MailingListThread;

public class MailingListTest {

	@Test
	public void testGetMessages() {
		MailingList ml = new MailingList();
		
		ml.setThreads(new HashSet<MailingListThread>());
		
		MailingListThread  mlthread1 = new MailingListThread();
		HashSet<MailMessage> mlthread1set = new HashSet<MailMessage>();
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		ml.getThreads().add(mlthread1);
		
		MailingListThread  mlthread2 = new MailingListThread();
		HashSet<MailMessage> mlthread2set = new HashSet<MailMessage>();
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		ml.getThreads().add(mlthread2);
		
		
		MailingListThread  mlthread3 = new MailingListThread();
		HashSet<MailMessage> mlthread3set = new HashSet<MailMessage>();
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		ml.getThreads().add(mlthread3);
		
		MailingListThread  mlthread4 = new MailingListThread();
		HashSet<MailMessage> mlthread4set = new HashSet<MailMessage>();
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		ml.getThreads().add(mlthread4);
		
		Set<MailMessage> messages = ml.getMessages();
		
		assertEquals(20, messages.size());
	}

	@Test
	public void testGetThreads() {
		MailingList ml = new MailingList();
		
		ml.setThreads(new HashSet<MailingListThread>());
		
		MailingListThread  mlthread1 = new MailingListThread();
		HashSet<MailMessage> mlthread1set = new HashSet<MailMessage>();
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		mlthread1set.add(new MailMessage());
		ml.getThreads().add(mlthread1);
		
		MailingListThread  mlthread2 = new MailingListThread();
		HashSet<MailMessage> mlthread2set = new HashSet<MailMessage>();
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		mlthread2set.add(new MailMessage());
		ml.getThreads().add(mlthread2);
		
		
		MailingListThread  mlthread3 = new MailingListThread();
		HashSet<MailMessage> mlthread3set = new HashSet<MailMessage>();
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		mlthread3set.add(new MailMessage());
		ml.getThreads().add(mlthread3);
		
		MailingListThread  mlthread4 = new MailingListThread();
		HashSet<MailMessage> mlthread4set = new HashSet<MailMessage>();
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		mlthread4set.add(new MailMessage());
		ml.getThreads().add(mlthread4);
		
		Set<MailingListThread> threads = ml.getThreads();
		
		assertEquals(4, threads.size());
	}

}
