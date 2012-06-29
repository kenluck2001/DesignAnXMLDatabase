/**
 *
 * @author kenneth Odoh
 */


public enum AddressType
{
    VISITING_ADDRESS("V"), RESIDENT_ADDRESS("R");
    final String code;
        
    AddressType(String code) 
    {
        this.code = code;
    }

    @Override
    public String toString() {
        if (this.code.equals("V"))
        {
            return "VISITING_ADDRESS";
        }else if (this.code.equals("R"))
        {
            return "RESIDENT_ADDRESS";
        }
        else
        {
            return "INVALID";
        }
    } 
};
