/**
 *
 * @author kenneth Odoh
 */


public enum EmailType
{
    WORK_EMAIL("W"), HOME_EMAIL("H");
    final String code;
        
    EmailType(String code) 
    {
        this.code = code;
    }

    @Override
    public String toString() {
        if (this.code.equals("W"))
        {
            return "WORK_EMAIL";
        }else if (this.code.equals("H"))
        {
            return "HOME_EMAIL";
        }
        else
        {
            return "INVALID";
        }
    } 
};
