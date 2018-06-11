import java.util.Random;
import java.util.Scanner;
import junit.framework.TestCase;
import java.security.SecureRandom;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!




public class UrlValidatorTest extends TestCase {
	
	String[] scheme = {"http","https","imap","ftp","ssh"};
	
	// mix set of valid and invalid authority
	String[] authority = {"en:wikipedia.org", "en.wikipedia.org", "wikipedia.org", "", null, "pmtpa.wikimedia.org",
			"_.wikipedia.org", "0.wikipedia.org", "-en.wikipedia.org", " ",
			"en..wikipedia.org", "192.0.2.235", "0.0.0.0", "192.0.2.256",
			"255.255.255.255", "255.256.255.254", "2555.255.255.2",
			"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmno.org",
			"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop.org",
			"en/.wikipedia.org", "0wikipedia.org", "-wikipedia.org", "en.wiki pedia.org", 
			"2001:db8:a0b:12f0::1", "2001:db8:0:0:0:0:2:1", "2001:db8::2:1"};
	
	// mix set of valid and invalid path
	String[] path = {" ",null,"index.html","index html","index%20html","index+html",
			"page?","page?search=new","page?search&new","page?search%20new","page?search+new", 
			"page=","page=search=new","page=search&new","page=search%20new","page=search+new",
			"page&","page&search=new","page&search&new","page&search%20new","page&search+new",
			"/home/index.hmtl","home/index.html","home/dir1/index.html","home/dir1/dir2/index.html"};

	// invalid scheme - for invalid test 
	// Do NOT add Double colon and two slashes "://" to build URL. It is included.
	String[] invalidScheme = {"http","htt[p://","htt[","/http://","-http",":http$://","7hhp@:/"};
	
	//  Hostname is composed of labels separated by periods. 
	//  Each label is between 1 and 63 characters long. 
	//abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijk   (63 chars)
	String[] validAuthority= {"en.wikipedia.org", "wikipedia.org", "pmtpa.wikimedia.org",
			"0.wikipedia.org",
			"192.0.2.235", "0.0.0.0",
			"255.255.255.255", 
			"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijk.org",
			"0wikipedia.org"};

	// abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijkl  (64 chars)
	String[] invalidAuthority = {"en:wikipedia.org", "", null, 
			"_.wikipedia.org", "-en.wikipedia.org", " ",
			"en..wikipedia.org", "192.0.2.256",
			"255.256.255.254", "2555.255.255.2",
			"en.abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop.org",
			"en/.wikipedia.org",  "-wikipedia.org", "en.wiki pedia.org", "2001:db8:0:0:0:0:2:1", 
			"2001:db8:a0b:12f0::1", "2001:db8::2:1"};
	
	String[] validPath= {"","index.html","index%20html","index+html",
			"page?","page?search=new","page&search&new","page&search%20new","page&search+new",
			"home/index.hmtl","home/index.html","home/dir1/index.html","home/dir1/dir2/index.html"};
	
	String[] invalidPath = {" ", "index html","page?search&new","page?search%20new","page?search+new", 
			"page=","page=search=new","page=search&new","page=search%20new","page=search+new",
			"page&","page&search=new"};
	
	
   public UrlValidatorTest(String testName) {
      super(testName);
   }
    
   public void testManualTest()
   {
	   String[] schemes = {"http","https","imap","ftp","ssh"};
	   String	urlStr;
	   
	   Scanner  userIn = new Scanner(System.in); 	   
	   UrlValidator urlValidator = new UrlValidator(schemes);
   
	   System.out.printf("Manual Testing\n");
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
	   System.out.println("Exiting Manual Test\n");
	   userIn.close(); 
   }
  
 
   public void testYourFirstPartition()
   {
		   
	   	   UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		   int numTest = 0;
		   int numFail = 0;
	 	   System.out.printf("\nPartitioning Special Scheme Valid Testing\n");
	 	   System.out.printf("\nValid Scheme Case 1) null scheme\n");
		   if(!urlValidator.isValid("www.google.com")) {
			   System.err.println("null scheme test failed\n" + "TEST URL: www.google.com\n");
		   }
		   
		   System.out.printf("\nValid Scheme Case 2) case insensitive alpha + (alphanumeric or + or _ or .) + ://\n");
	   
		   for(int i = 0; i < 10; i++) {
			   String testURL = createRandScheme() + "en.wikipedia.org";
			   // String testURL = "https://" + "www.google.com";
		   
			   if(!urlValidator.isValid(testURL)) {
				   numFail++;
				   System.err.println("Valid Test Failed\n" + "TEST URL: " + testURL + "\n");
			   }
			   /* for debugging, remove comment out 
			   else {
				   System.out.printf("Passed\n" + "TEST URL: " + testURL + "\n");
			   }
			   */
			   numTest++;
		   }
		   printResult(numTest, numFail);
		   numTest = 0;
		   numFail = 0;
		   
	 	   System.out.printf("\nInvalid Scheme Case\n");
		   for(int i = 0; i<invalidScheme.length; i++) {
			   String testInvalidURL = invalidScheme[i] + "en.wikipedia.org";
			   
			   if (urlValidator.isValid(testInvalidURL)) {
				   System.err.println("Invalid Test Failed\n" + "TEST URL: " + testInvalidURL + "\n");
				   numFail++;
			   }
			   numTest++;
		   }
		   printResult(numTest, numFail);

		   System.out.printf("\nEnd Of Partitioning Special Scheme Valid Testing\n\n");	      

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

   /********************************************************
    * testYourThirdPartition test 
    * 	Test following cases
    * Case 1) the boundary testing to evaluate max length of url
    * Case 1-1)Expected URL is valid when total URL string length <= max length, 2083
    * Case 1-2)Expected URL is invalid when total URL string length > max length, 2083
    * 
    * Case 2) partition path validity testing
    ********************************************************/
   public void testYourThirdPartition() {
	   UrlValidator urlValidator = new UrlValidator();
	   
	   String	testURL;
	   int		maxLen = 2083; 
	   int		startLen;
	   Random 	ra = new Random();
	   char		rchar;
	   int numTest = 0;
	   int numFail = 0;
	   
	   System.out.printf("\n\nPath Testing\n");
	   System.out.printf("Case 1) the boundary testing to evaluate max length(2083) of url\n");
	   //Check Max length with different Authority values
	   for (int i = 0; i < validAuthority.length; i++){
		   testURL = "http://" + validAuthority[i] + "/";
		   startLen = testURL.length();
		   
		   //fill in the rest of the max length with characters
		   for (int k = 0; k < (maxLen - startLen); k++) {
			   rchar = (char) (ra.nextInt(26) + 'a');
			   testURL = testURL + Character.toString(rchar);
		   }
		   
		   if (urlValidator.isValid(testURL)) {
			   //System.out.printf("The url %s with length %d is valid\n", testURL, testURL.length());
		   } else {
			   System.out.printf("Failed. Expected: valid, Result: invalid\nThe url %s with length %d must be valid.\n", testURL, testURL.length());
			   numFail++;
		   }
		   numTest++;
		   
		   //add one more to go over max
		   testURL = testURL + "a";
		   if (urlValidator.isValid(testURL)) {
			   System.out.printf("Failed. Expected: invalid, Result: valid\nThe url %s with length %d must be invalid\n", testURL, testURL.length());
			   numFail++;
		   } else {
			  // System.out.printf("The url %s with length %d is invalid\n", testURL, testURL.length());
		   }
		   numTest++;
	   }
	   printResult(numTest, numFail);
	   numTest = 0;
	   numFail = 0;
	   
	   //Test Path array	   
	   System.out.printf("\nCase 2) partition path validity testing\n");
	   System.out.printf("\nCase 2-1) valid path testing\n");
	   for (int i = 0; i < validAuthority.length; i++) {
		   for (int k = 1; k < validPath.length; k++) {
			   testURL = "http://" + validAuthority[i] + "/" + validPath[k];
			   if (urlValidator.isValid(testURL)) {
				   //System.out.printf("the url %s with path %s is valid\n", testURL, path[k]);
			   } else {
				   System.out.printf("failed\n   the url %s with valid path %s must be valid\n", testURL, path[k]);
				   numFail++;
			   }
			   numTest++;
		   }		   
	   }
	   printResult(numTest, numFail);
	   numTest = 0;
	   numFail = 0;
	   
	   System.out.printf("\nCase 2-2) invalid path testing\n");
	   for (int i = 0; i < validAuthority.length; i++) {
		   for (int k = 1; k < invalidPath.length; k++) {
			   testURL = "http://" + validAuthority[i] + "/" + invalidPath[k];
			   if (urlValidator.isValid(testURL)) {
				   System.out.printf("failed\n   the url %s with invalid path %s must be invalid\n", testURL, path[k]);
				   numFail++;
			   } else {
				   //System.out.printf("the url %s with path %s is invalid\n", testURL, path[k]);
			   }
			   numTest++;
		   }		   
	   }
	   printResult(numTest, numFail);
	   numTest = 0;
	   numFail = 0;
	   
	   System.out.printf("\n\nEnd of Path Testing\n");
   }
   
   /********************************************************
    * isValid test 
    * Valid Test Cases  --  valid URL should return True
    ********************************************************/
   public void testIsValidTrue()
   {
	   UrlValidator urlValidator = new UrlValidator(scheme);
	   String  		testURL;
	   int numTest = 0;
	   int numFail = 0;

	   System.out.printf("\n\nValid Case Combination Testing\n");
	   
	   for(int i = 0; i<scheme.length; i++) {
		   for(int j=0; j<validAuthority.length; j++) {
			   for(int k=0; k<validPath.length; k++) {

				   //Add forward slash to separate authority and path 
				   testURL = scheme[i] + "://"+ validAuthority[j] + "/" + validPath[k];
				   
				   if (!urlValidator.isValid(testURL)) {
					   numFail++;
					   System.err.println(testURL + " failed");
				   }
				   numTest++;
			   }
		   }
	   }	
	   printResult(numTest, numFail);
	   System.out.printf("\nEnd of Valid Case Combination Testing\n");
   }
   
/********************************************************
 * isValid test 
 * Invalid Test Cases  --  invalid URL should return false
 * If one part of URL is invalid, should be invalid
 * Case 1) Invalid URL = invalid scheme + any authority + any path 
 * Case 2) Invalid URL = any scheme + invalid authority + any path
 * Case 3) Invalid URL = any scheme + any authority + invalid path
 * If no slash between authority and path, should be invaild
 * Case 4) no slash between authority AND valid URL
 ********************************************************/ 
   public void testIsValidFalse()
   {
	   UrlValidator urlValidator = new UrlValidator(scheme);
	   String  		testURL;
	   int numTest = 0;
	   int numFail = 0;

	   System.out.printf("\n\nInvalid Case Testing\n");
	   System.out.printf("Case 1) Invalid URL = invalid scheme + any authority + any path \n");
	   for(int i = 0; i<invalidScheme.length; i++) {
		   for(int j=0; j<validAuthority.length; j++) {
			   for(int k=0; k<validPath.length; k++) {
				   //Add forward slash to separate authority and path 
				   testURL = invalidScheme[i] + authority[j] + "/" + path[k];
				   
				   // if isValid method returns with true, url is valid.
				   // url should be invalid -> Test Failed.
				   if (urlValidator.isValid(testURL)) {
					   numFail++;
					   System.err.println(testURL + " failed");
				   }
				   numTest++;
			   }
		   }
	   }
	   
	   printResult(numTest, numFail);
	   numTest = 0;
	   numFail = 0;
	   
	   System.out.printf("Case 2) Invalid URL = any scheme + invalid authority + any path \n");
	   for(int i = 0; i<scheme.length; i++) {
		   for(int j=0; j<invalidAuthority.length; j++) {
			   for(int k=0; k<path.length; k++) {
				   //Add forward slash to separate authority and path 
				   testURL = scheme[i] + "://" + invalidAuthority[j] + "/" + path[k];
				   
				   // if isValid method returns with true, url is valid.
				   // url should be invalid -> Test Failed.
				   if (urlValidator.isValid(testURL)) {
					   numFail++;
					   System.err.println(testURL + " failed");
				   }
				   numTest++;
			   }
		   }
	   }
	   
	   printResult(numTest, numFail);
	   numTest = 0;
	   numFail = 0;
	   
	   System.out.printf("Case 3) Invalid URL = any scheme + any authority + invalid path\n");
	   for(int i = 0; i<scheme.length; i++) {
		   for(int j=0; j<authority.length; j++) {
			   for(int k=0; k<invalidPath.length; k++) {
				   //Add forward slash to separate authority and path 
				   testURL = scheme[i] + "://" + authority[j] + "/" + invalidPath[k];
				   
				   // if isValid method returns with true, url is valid.
				   // url should be invalid -> Test Failed.
				   if (urlValidator.isValid(testURL)) {
					   numFail++;
					   System.err.println(testURL + " failed");
				   }
				   numTest++;
			   }
		   }
	   }
	   
	   printResult(numTest, numFail);
	   numTest = 0;
	   numFail = 0;
	   
	   System.out.printf("Case 4) no slash between authority and path\n");
	   for(int i = 0; i<scheme.length; i++) {
		   for(int j=0; j<validAuthority.length; j++) {
			   for(int k=0; k<validPath.length; k++) {
				   //No forward slash to separate authority and path 
				   testURL = scheme[i] + "://" + validAuthority[j] + validPath[k];
				   
				   // WHEN PASS IS NULL, if isValid method returns with false, url is invalid.
				   // url should be valid -> Test Failed.
				
				   if(validPath[k] == "") {
					   if (!urlValidator.isValid(testURL)) {
						   numFail++;
						   System.err.println(testURL + " failed");
					   }					   
				   }
				   // if isValid method returns with true, url is valid.
				   // url should be invalid -> Test Failed.
				   else {
					   if(urlValidator.isValid(testURL)) {
						   numFail++;
						   System.err.println(testURL + " failed");
					   }				   
				   }
				   numTest++;
			   }
		   }
	   }
	   
	   printResult(numTest, numFail);
	   numTest = 0;
	   numFail = 0;

	   System.out.printf("\nEnd of Invalid Case Testing\n");   
   }

   /*******************************************************************
   ** Method: prtinResult
   *  print test result(all passed or failed), 
   *  number of tests, and number of failed tests 
   ********************************************************************/
   protected void printResult(int numTest, int numFail) {
	   System.out.printf("\nTOTAL TESTED URL: " + numTest + "\n");
	   
	   if (numFail > 0) {
		   System.out.println("FAILED: " + numFail + "\n\n");
	   }
	   else if(numFail == 0) {
		   System.out.printf("ALL TESTS PASSED\n\n");	
	   }
	   else {
		   System.err.println("\nNumber of tests should be 0 or larger\n");	
	   } 
   }
	
   /*******************************************************************
   ** Random String Creating Helper Functions
   ** 	returns specifically selected type of string as needed
   **	createRandAlpha(int length): use for scheme, authority...
   **	createRandAlphaNum(int length, boolean withSlash): if withSlash is passed as true
   **		it returns with slash in the string.  Ex: bb223/br
   **	createRandNum(int min, int max): use for creating random port #
   ********************************************************************/
	   protected String createRandAlpha(int length) {
	   
	    String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    
	        Random random = new SecureRandom();
	        if (length <= 0) {
	            throw new IllegalArgumentException("String length must be a positive integer");
	        }

	        StringBuilder sb = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            sb.append(alpha.charAt(random.nextInt(alpha.length())));
	        }


	   return sb.toString();
   }
   
   protected String createRandAlphaNum(int length, boolean withSlash) {
	   
	   String alpha;
	   alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	   
       Random random = new SecureRandom();
       if (length <= 0) {
           throw new IllegalArgumentException("String length must be a positive integer");
       }
       
       StringBuilder sb = new StringBuilder(length);
       
	   if(withSlash) {
		   int counter = 0;
	        for (int i = 0; i < length; i++) {
	            sb.append(alpha.charAt(random.nextInt(alpha.length())));
	            
	            if(counter == (length % 5) + 5) {
	            	sb.append("/");
	            	length++;
	            	counter = 0;
	            }
	        } 
	   }
	   else {
	        for (int i = 0; i < length; i++) {
	            sb.append(alpha.charAt(random.nextInt(alpha.length())));
	        } 
	   } 
	   return sb.toString();
  }
   
   protected String createRandNum(int min, int max) {
	    
	        Random rand = new SecureRandom();
	 	    int num = rand.nextInt(max - min) + min;

	   return String.valueOf(num);
 }
   protected String createRandScheme() {
	   // valid "Alpha + (Alnum | + | - | . )
	   
	   // get random length of Scheme
	   Random rand = new SecureRandom();
	   
	   // length - 1 of Scheme
	   int num = rand.nextInt((10 - 3) + 1) + 3;

	   return (createRandAlpha(1) + createRandAlphaNum(num, false) + "://");
   }
    /*******************************************************************
   ** End of Random String Creating Helper Functions
   ********************************************************************/  
}


/********************************************************
 * isValid test 
 * original test
 ********************************************************/
/*
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
*/

/********************************************************
 * testYourThirdPartition test 
 * Original
 ********************************************************/
/*
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
*/