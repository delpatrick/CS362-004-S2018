
import java.util.Random;
import java.util.Scanner;
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {
	
	String[] scheme = {"http","https","imap","ftp","ssh"};
	
	String[] authority = {"en:wikipedia.org", "en.wikipedia.org", "wikipedia.org", "", null, "pmtpa.wikimedia.org",
			"_.wikipedia.org", "0.wikipedia.org", "-en.wikipedia.org", " ",
			"en..wikipedia.org", "192.0.2.235", "0.0.0.0", "192.0.2.256",
			"255.255.255.255", "255.256.255.254", "2555.255.255.2",
			"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmno.org",
			"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop.org",
			"en/.wikipedia.org", "0wikipedia.org", "-wikipedia.org", "en.wiki pedia.org", 
			"2001:db8:a0b:12f0::1", "2001:db8:0:0:0:0:2:1", "2001:db8::2:1"};
	
	String[] path = {" ",null,"index.html","index html","index%20html","index+html",
			"page?","page?search=new","page?search&new","page?search%20new","page?search+new", 
			"page=","page=search=new","page=search&new","page=search%20new","page=search+new",
			"page&","page&search=new","page&search&new","page&search%20new","page&search+new",
			"/home/index.hmtl","home/index.html","home/dir1/index.html","home/dir1/dir2/index.html"};

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   String[] schemes = {"http","https","imap","ftp","ssh"};
	   String	urlStr;
	   
	   Scanner  userIn = new Scanner(System.in); 	   
	   UrlValidator urlValidator = new UrlValidator(schemes);
   
	   System.out.println("Enter Url to test or 'exit' to quit...");
	   
	   while (true) {
		   urlStr = userIn.nextLine();
		   
		   if (urlStr.equals("exit"))
			   break;
		   
		   if (urlValidator.isValid(urlStr)) {
			   System.out.println("url is valid");
		   } else {
			   System.out.println("url is invalid");
		   }   
	   }
	   System.out.println("Exiting Test\n");
	   userIn.close(); 
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
//   public void testYourSecondPartition(){
//
//	   String[] schemes = {"http","https","imap","ftp","ssh"};
//
//	   UrlValidator urlValidator = new UrlValidator(schemes);
//
//	   
//	   for(int j = 1; j < scheme.length; j++) {
//		   for(int i = 0; i < authority.length; i++) {
//			   String testURL = scheme[j] + "://" + authority[i];
//			   if (urlValidator.isValid(testURL)) {
//				   System.out.printf("the url %s with authority %s is valid\n", testURL, authority[i]);
//			   } else {
//				   System.out.printf("the url %s with authority %s is invalid\n", testURL, authority[i]);
//			   }
//		   }
//	   }
//
//
//   }

   public void testYourThirdPartition() {
	   UrlValidator urlValidator = new UrlValidator();
	   
	   String	testURL;
	   int		maxLen = 2083; 
	   int		startLen;
	   Random 	ra = new Random();
	   char		rchar;
	   
	   String[] authority3 = {"en.wikipedia.org", "wikipedia.org", "pmtpa.wikimedia.org", "0.wikipedia.org",
				"192.0.2.235", "192.0.2.256", "255.255.255.255", "255.256.255.254",
				"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmno.org" };
	   
	   //Check Max length with different Authority values
	   for (int i = 0; i < authority3.length; i++){
		   testURL = "http://" + authority3[i] + "/";
		   startLen = testURL.length();
		   
		   //fill in the rest of the max length with characters
		   for (int k = 0; k < (maxLen - startLen); k++) {
			   rchar = (char) (ra.nextInt(26) + 'a');
			   testURL = testURL + Character.toString(rchar);
		   }
		   
		   if (urlValidator.isValid(testURL)) {
			   //System.out.printf("The url %s with length %d is valid\n", testURL, testURL.length());
		   } else {
			   System.out.printf("The url %s with length %d is invalid\n", testURL, testURL.length());
		   }
		   //add one more to go over max
		   testURL = testURL + "a";
		   if (urlValidator.isValid(testURL)) {
			   //System.out.printf("The url %s with length %d is valid\n", testURL, testURL.length());
		   } else {
			   System.out.printf("The url %s with length %d is invalid\n", testURL, testURL.length());
		   }
	   }
	   //Test Path array
	   for (int i = 0; i < authority3.length; i++) {
		   for (int k = 1; k < path.length; k++) {
			   testURL = "http://" + authority3[i] + "/" + path[k];
			   if (urlValidator.isValid(testURL)) {
				   //System.out.printf("the url %s with path %s is valid\n", testURL, path[k]);
			   } else {
				   System.out.printf("the url %s with path %s is invalid\n", testURL, path[k]);
			   }
		   }		   
	   }
   }
    
   
   public void testIsValid()
   {
	   UrlValidator urlValidator = new UrlValidator(scheme);
	   String  		testURL;

	   for(int i = 0; i<scheme.length; i++) {
		   for(int j=0; j<authority.length; j++) {
			   for(int k=0; k<path.length; k++) {
				   testURL = scheme[i] + "://"+ authority[j] + path[k];
				   if (urlValidator.isValid(testURL)) {
					   System.out.printf("the url %s is valid\n", testURL);
				   } else {
					   System.out.printf("the url %s is invalid\n", testURL);
				   }
				   
				   //Add forward slash to separate authority and path 
				   testURL = scheme[i] + "://"+ authority[j] + "/" + path[k];
				   if (urlValidator.isValid(testURL)) {
					   System.out.printf("the url %s is valid\n", testURL);
				   } else {
					   System.out.printf("the url %s is invalid\n", testURL);
				   }
			   }
		   }
	   }	   
   }

}
