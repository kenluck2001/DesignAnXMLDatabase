/**
 *
 * @author kenneth Odoh
 */
import javax.swing.JOptionPane;
import java.util.regex.*;
import java.util.StringTokenizer;

public class Validator
{

	public static boolean isEmailValid(String email)
	{  
       /** isEmailValid: Validate email address using Java reg ex. 
        * This method checks if the input string is a valid email address. 
        * @param email String. Email address to validate 
        * @return boolean: true if email address is valid, false otherwise. 
        */  
		boolean isValid = false;  
		try
		{		  
			/** 
			Email format: A valid email address will have following format: 
				    [\\w\\.-]+: Begins with word characters, (may include periods and hypens). 
				@: It must have a '@' symbol after initial characters. 
				([\\w\\-]+\\.)+: '@' must follow by more alphanumeric characters (may include hypens.). 
			This part must also have a "." to separate domain and subdomain names. 
				[A-Z]{2,4}$ : Must end with two to four alaphabets. 
			(This will allow domain names with 2, 3 and 4 characters e.g pa, com, net, wxyz) 
			 
			Examples: Following email addresses will pass validation 
			abc@xyz.net; ab.c@tx.gov 
			*/  
			  
			//Initialize reg ex for email.  
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
			CharSequence inputStr = removeSpaces(email);  
			//Make the comparison case-insensitive.  
			Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
			Matcher matcher = pattern.matcher(inputStr);  
			if(matcher.matches())
			{  
				isValid = true;  
			}  
			if(!isValid)
			{
				JOptionPane.showMessageDialog(null, "Email must be in form of abc@xyz.net; ab.c@tx.gov ");
			}
		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "Empty email field");
		}

		return isValid;  
	}  

	  
	public static boolean isPhone(String number)
	{ 
		/** isPhone: Validate a phone number using Java regex. 
		* This method checks if the input string contains all numeric characters. 
		* @param email String. Number to validate 
		* @return boolean: true if the input is all numeric, false otherwise. 
		*/   
		boolean isValid = false;  
		  
		/*Number: A numeric value will have following format: 
		         ^[-+]?: Starts with an optional "+" or "-" sign. 
		     [0-9]*: May have one or more digits. 
		    \\.? : May contain an optional "." (decimal point) character. 
		    [0-9]+$ : ends with numeric digit. 
		*/  
		  
		//Initialize reg ex for numeric data. 
		try
		{  
			String expression = "^[-+]?[0-9]*\\.?[0-9]+$";  
			CharSequence inputStr = removeSpaces(number);  
			Pattern pattern = Pattern.compile(expression);  
			Matcher matcher = pattern.matcher(inputStr);  

			if(matcher.matches())
			{  
				isValid = true;  
			}  
			if(!isValid)
			{
				JOptionPane.showMessageDialog(null, "Enter phone number instead of alphabets");
			}

		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "Empty phone field");
		} 
		return isValid;  
	} 

	public static boolean isNumeric(String number)
	{ 
		/** isNumeric: Validate a number using Java regex. 
		* This method checks if the input string contains all numeric characters. 
		* @param email String. Number to validate 
		* @return boolean: true if the input is all numeric, false otherwise. 
		*/   
		boolean isValid = false;  
		  
		/*Number: A numeric value will have following format: 
		         ^[-+]?: Starts with an optional "+" or "-" sign. 
		     [0-9]*: May have one or more digits. 
		    \\.? : May contain an optional "." (decimal point) character. 
		    [0-9]+$ : ends with numeric digit. 
		*/  
		  
		//Initialize reg ex for numeric data. 
		try
		{  
			String expression = "^[-+]?[0-9]*\\.?[0-9]+$";  
			CharSequence inputStr = removeSpaces(number);  
			Pattern pattern = Pattern.compile(expression);  
			Matcher matcher = pattern.matcher(inputStr);  

			if(matcher.matches())
			{  
				isValid = true;  
			}  
			if(!isValid)
			{
				JOptionPane.showMessageDialog(null, "Enter Digits");
			}

		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "Empty phone field");
		} 
		return isValid;  
	} 

    private static String removeSpaces(String s) 
    {
        //remove all the whitespaces in the string
        StringTokenizer st = new StringTokenizer(s," ",false);
        String t="";
        while (st.hasMoreElements()) t += st.nextElement();
        return t;
    }

} 

