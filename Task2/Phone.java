/**
 *
 * @author kenneth Odoh
 */
public class Phone
{
    private String ptype;   //type
    private String value;   //value

    public Phone(String type, String pno)
    {
        setPhoneType(type); //set the phone type
        setPhoneNumber(pno);//set the phone number
    }

    //set phone type
    public void setPhoneType(String type)
    {
        /**
            type can be either "WORK_PHONE" or "MOBILE_PHONE"
        */
        PhoneType [] values = PhoneType.values();
        for (PhoneType phone : values) 
        {
            if ((phone.toString().equalsIgnoreCase(type)) && (phone.toString().equals("WORK_PHONE")))
            {
                ptype = phone.toString();
            }
            else if ((phone.toString().equalsIgnoreCase(type)) && (phone.toString().equals("MOBILE_PHONE")))
            {
                ptype = phone.toString();
            }
        }
    }

    //get phone type
    public String getPhoneType()
    {
        return ptype;
    }

    //set phone number
    public void setPhoneNumber(String num)
    {
        if (Validator.isPhone(num))
        {
            value = num;
        }
    }

    //get phone number
    public String getPhoneNumber()
    {
        return value;
    }

}
