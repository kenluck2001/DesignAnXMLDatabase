/**
 *
 * @author kenneth Odoh
 */

public class Email
{
    private String etype;   //type
    private String value;   //value

    public Email(String type, String em)
    {
        setEmailType(type); //set the email type
        setEmailAddress(em);//set the email address
    }

    //set phone type
    public void setEmailType(String type)
    {
        /**
            type can be either "WORK_EMAIL" or "HOME_EMAIL"
        */

        EmailType [] values = EmailType.values();
        for (EmailType emType : values) 
        {
            if ((emType.toString().equalsIgnoreCase(type)) && (emType.toString().equals("WORK_EMAIL")))
            {
                etype = emType.toString();
            }
            else if ((emType.toString().equalsIgnoreCase(type)) && (emType.toString().equals("HOME_EMAIL")))
            {
                etype = emType.toString();
            }
        }
    }

    //get email type
    public String getEmailType()
    {
        return etype;
    }

    //set email address
    public void setEmailAddress(String num)
    {
        if (Validator.isEmailValid(num))
        {
            value = num;
        }
    }

    //get email address
    public String getEmailAddress()
    {
        return value;
    }

}
