/* Dependency libraries 
 * module path - JRE System library [jdk-13.0.2]
 * class path - activation-1.1.1.jar
 * hamcrest-core-1.3.jar
 * junit-4.13.jar
 * mail.jar
*/


package org.apache.commons.mail;

import static org.junit.Assert.*;
import org.apache.commons.mail.EmailConstants;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import javax.naming.NamingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;

import org.junit.After;
import org.junit.Before;
import javax.mail.internet.AddressException;
import org.junit.Test;
import junit.framework.*;

public class EmailTest extends TestCase 
{

	protected Email Testmail;
      

        private static final String[] VALID_EMAILS = 
{
           "vidya10@gmail.com",
           "123@yahoo.com",
           "divyashivakumar@gmail.com"
 };
           
	

	@Before
	public void setUp() throws Exception {
		 Testmail = new SimpleEmail();
	}

    //TEST CASE testAddBcc(String email's)
	@Test
	public void testAddBcc() throws EmailException, AddressException {
		//Add a valid mail to the Array list 
		List<InternetAddress> recipient = new ArrayList<InternetAddress>();
		recipient.add(new InternetAddress("vidya10@gmail.com"));
		//parameter testing  (string mails)
		String[] emails = { "vidya10@gmail.com" };
		Testmail.addBcc(emails);
		//Asserts whether the arrays are equal or not
		assertArrayEquals(recipient.toArray(), Testmail.getBccAddresses().toArray()); }
	// checking whether the email address is added to the Bcc
	
	
	
	
	//TEST CASE testAddCc(String email)	
	@Test
	public void testAddCc() {

		//From list string to Internet address array
        List<InternetAddress> recipient = new ArrayList<InternetAddress>();
        try {
            recipient.add(new InternetAddress("vidya10@gmail.com"));
        } catch (AddressException e) {
            //Email failed to add to array list
            e.printStackTrace();
        }
        try {
            recipient.add(new InternetAddress("123@yahoo.com"));
        } catch (AddressException e) {
            //Email failed to add to array list
            e.printStackTrace();
        }
        try {
            recipient.add(new InternetAddress("divyashivakumar@gmail.com"));
        } catch (AddressException e) {
            //Email failed to add to array list
            e.printStackTrace();
        }


        // valid email address to Cc
        try {
            for(InternetAddress temp : recipient)
                Testmail.addCc(temp.toString());
        } catch (EmailException e) {
            //Email failed to add to Cc list
            e.printStackTrace();
        }

        //assertEquals ()
        assertEquals("Test success", recipient.size(), Testmail.getCcAddresses().size());
        assertEquals("Test success", recipient.toString(), Testmail.getCcAddresses().toString());
    }
	
	
	

	//TEST CASE addHeader(String name, String value)
	@Test
	public void testAddHeader() throws IllegalArgumentException
	{
		
		String headervalue = "value can not be null or empty";
		String headername = "name can not be null or empty";
		
		// Add header name and value
		Testmail.addHeader("TName", "TValue");
		
		
		try{
			// Add null for value
			Testmail.addHeader("TName", null);}
		// pass IllegalArgumentException as IAE
		catch(IllegalArgumentException IAE)
		{
			//boolean condition asserts that given condition is true 
			assertTrue(IAE.getMessage().equals(headervalue));
		}
		
		
		try
		{
			// Add null for name
			Testmail.addHeader(null, "TValue");
		}
		catch(IllegalArgumentException IAE)
		{
			assertTrue(IAE.getMessage().equals(headername));
		}
	}
	
	
	
	
	//TEST CASE addReplyTo(String email, String name)
	@Test
	public void testAddReplyTo()  throws EmailException {
		try {
			//Add a valid mail to the Array list 
			List<InternetAddress> sendmail = new ArrayList<InternetAddress>();
			//Parameter testing (string,String) 
			sendmail.add(new InternetAddress("vidya10@gmail.com"));
			Testmail.addReplyTo("vidya10@gmail.com", "vidya10");
			assertArrayEquals(sendmail.toArray(), Testmail.getReplyToAddresses().toArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
	//TEST CASE buildMimeMessage()
	@Test
	public void testBuildMimeMessage() throws EmailException{
		
			// buildMimemessgae test
			Testmail.setHostName("Remote Host");
			Testmail.setSubject("Meeting");
			Testmail.setFrom("123@yahoo.com");
			Testmail.setMsg("COVID19");
			Testmail.setCharset("ASCII");
			Testmail.addTo("vidya10@gmail.com");
			Testmail.addBcc("divyashivakumar@gmail.com");
			Testmail.addCc("vidya@gmail.com");
			Testmail.addReplyTo("pqr@yahoo.com");
			Testmail.addHeader("Tname", "Tvalue");
			try {
				Testmail.buildMimeMessage();
			}
			catch (EmailException e) {
				
			}
			catch (IllegalStateException e) {
				
			}
			}
	
	
	
	
	
	//TEST CASE buildMimeMessageabc()
		@Test	
	public void testBuildMimeMessageabc() throws EmailException{
		String xyz =new String();
		//buildMimemessageabc test
		Testmail.setHostName("Remote Host");
		Testmail.setSubject("Meeting");
		Testmail.setFrom("123@yahoo.com");
		Testmail.setMsg("COVID19");
		Testmail.setCharset("ASCII");
		Testmail.addTo("vidya10@gmail.com");
		Testmail.addBcc("divyashivakumar@gmail.com");
		Testmail.addCc("vidya@gmail.com");
		Testmail.addReplyTo("pqr@yahoo.com");
		Testmail.addHeader("Tname", "Tvalue");
		Testmail.setContent(null, null);
		try {
			Testmail.buildMimeMessage();
		}
		catch (EmailException e) {
			
		}
		catch (IllegalStateException e) {
			
		}
		Testmail.setContent(new String(), "xyz");
		try {
			Testmail.buildMimeMessage();
		}
		catch (EmailException e) {
			
		}
		catch (IllegalStateException e) {
			
		}
		}
			
		
		
		
		
		//TEST CASE getHostName()

	@Test
	public void testGetHostName()  {
		assertEquals(null, Testmail.getHostName());
		Testmail.setHostName("HTest");
		assertEquals("HTest", Testmail.getHostName());
		// Default session object
		Testmail.setMailSession(Session.getDefaultInstance(System.getProperties()));
		//java.lang.string message,actual value, expected value
		assertEquals(null,Testmail.getHostName());
	}
	
	
	
	
	//TEST CASE  getMailSession()
	@Test
	public void testGetMailSession() {
		try {
			Testmail.setSSLOnConnect(true);
			Testmail.setSSLCheckServerIdentity(true);
			Testmail.setSubject("Meeting"); //subject
			Testmail.setFrom("123@yahoo.com"); //from
			Testmail.addTo("vidya10@gmail.com");//To
			Testmail.setMsg("COVID19");	//Message
			Testmail.setHostName("Remote host");//Host name
			Session session = Testmail.getMailSession();
			assertNotNull(session); // the parameter is  not null
		} catch (EmailException e) {
			assertTrue(false); //value true or false
		}
	}

	
	
	//TEST CASE  getSentDate()
	@Test
	public void testGetSentDate() {
		Date xyz = Testmail.getSentDate();
		assertNotNull(xyz);
	}
	
	
	
	//TEST CASE  getSentDateabc()
	@Test
	public void testGetSentDateabc() {
		Date abc = new Date();
		Testmail.setSentDate(abc);
		Date xyz = Testmail.getSentDate();
		assertEquals(abc, xyz);
	}
	
	


	//TEST CASE getSocketConnectionTimeout()
    @Test
	public void testGetSocketConnectionTimeout() {
		int time = Testmail.getSocketConnectionTimeout(); //returns timeout in ms
		assertEquals(EmailConstants.SOCKET_TIMEOUT_MS, time); //timeout is 60000ms
	}
	

	
    
    
  //TEST CASE send()
	@Test
	 public void testSend() throws Exception {
		Testmail.setHostName("Remote Host");
		Testmail.setSubject("Meeting");
		Testmail.setFrom("123@yahoo.com");
		Testmail.setMsg("COVID19");
		Testmail.addTo("vidya10@gmail.com");
		Testmail.addBcc("divyashivakumar@gmail.com");
		Testmail.addCc("vidya@gmail.com");
		Testmail.addReplyTo("pqr@yahoo.com");
		Testmail.setSmtpPort(8080); //setSmtpport(port)
		try{
			assertNotNull(Testmail.send());
		} catch(EmailException e)
		{
			
		}
    }

		

	 //TEST CASE setFrom(String email)
	@Test
	public void testSetFrom() throws EmailException{
		try {
			Testmail.setFrom("vidya10@gmail.com");
			InternetAddress fromaddr = new InternetAddress("vidya10@gmail.com");
			assertEquals(fromaddr, Testmail.getFromAddress());
		} catch (Exception e) {
			
		}
	}
	
	
	

	 //TEST CASE updateContentType(String aContentType))
	@Test
	public void testUpdateContentType() 
	{
		// Test testUpdateContentType
		Testmail.updateContentType("aContententType");
		Testmail.updateContentType(null);
		Testmail.updateContentType("update C; charset=type");
		Testmail.updateContentType("html/text");
		
	}
			
	
	@After
	public void tearDown() throws Exception { //Perform the clean up of resources
		Testmail = null;
		
	}

}
