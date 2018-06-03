

import java.util.Scanner;
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   String[] schemes = {"http","https","imap","ftp","ssh"};
	   String	urlStr;
	   
	   Scanner  userIn = new Scanner(System.in); 	   
	   UrlValidator urlValidator = new UrlValidator(schemes);
	   
	   while (true) {
		   urlStr = userIn.nextLine();
		   
		   if (urlStr == "")
			   break;
		   
		   if (urlValidator.isValid(urlStr)) {
			   System.out.println("url is valid");
		   } else {
			   System.out.println("url is invalid");
		   }   
	   }
	   System.out.println("Exiting Test");
	   userIn.close(); 
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
