/**
 *
 * @author kenneth Odoh
 */


public enum PhoneType
{
    WORK_PHONE("W"), MOBILE_PHONE("M");
    final String code;
        
    PhoneType(String code) 
    {
        this.code = code;
    }

    @Override
    public String toString() {
        if (this.code.equals("W"))
        {
            return "WORK_PHONE";
        }else if (this.code.equals("M"))
        {
            return "MOBILE_PHONE";
        }
        else
        {
            return "INVALID";
        }
    } 
};
