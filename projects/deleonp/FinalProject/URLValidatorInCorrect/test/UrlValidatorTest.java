

import java.util.Scanner;
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
//   public void testManualTest()
//   {
//	   String[] schemes = {"http","https","imap","ftp","ssh"};
//	   String	urlStr;
//	   
//	   Scanner  userIn = new Scanner(System.in); 	   
//	   UrlValidator urlValidator = new UrlValidator(schemes);
//	   
//	   while (true) {
//		   urlStr = userIn.nextLine();
//		   
//		   if (urlStr == "")
//			   break;
//		   
//		   if (urlValidator.isValid(urlStr)) {
//			   System.out.println("url is valid");
//		   } else {
//			   System.out.println("url is invalid");
//		   }   
//	   }
//	   System.out.println("Exiting Test");
//	   userIn.close(); 
//   }
//   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){

	   String[] scheme = {"http","https","imap","ftp","ssh"};

	   UrlValidator urlValidator = new UrlValidator(scheme);

	   String[] authority = {"en:wikipedia.org", "en.wikipedia.org", "wikipedia.org", "", null, "pmtpa.wikimedia.org",
				"_.wikipedia.org", "0.wikipedia.org", "-en.wikipedia.org", " ",
				"en..wikipedia.org", "192.0.2.235", "0.0.0.0", "192.0.2.256",
				"255.255.255.255", "255.256.255.254", "2555.255.255.2",
				"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmno.org",
				"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop.org",
				"en/.wikipedia.org", "0wikipedia.org", "-wikipedia.org", "en.wiki pedia.org", 
				"2001:db8:a0b:12f0::1", "2001:db8:0:0:0:0:2:1", "2001:db8::2:1"};
	   
	   for(int j = 0; j < scheme.length; j++) {
		   for(int i = 0; i < authority.length; i++) {
			   String testURL = scheme[j] + "://" + authority[i];
			   if (urlValidator.isValid(testURL)) {
				   System.out.printf("the url %s with authority %s is valid\n", testURL, authority[i]);
			   } else {
				   System.out.printf("the url %s with authority %s is invalid\n", testURL, authority[i]);
			   }
		   }
	   }


   }

   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
