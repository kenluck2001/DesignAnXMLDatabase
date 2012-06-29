/**
 *
 * @author kenneth Odoh
 */

import java.util.*;

public class Address
{
    private String addressType; //address type
    private ArrayList<String>street;//street
    private String postalCode;  //post code
    private String town;    //town

    
    public Address(String type, ArrayList<String>street, String postCod, String town)
    {
        setAddressType(type); //set address type
        setPostalCode(postCod); // set postal code
        setStreet(street);   //set street
        setTown(town);      // set town

    }

    //set street
    public void setStreet(ArrayList<String>street)
    {
        this.street = street;
    }

    //get street
    public ArrayList<String>getStreet()
    {
        return street;
    }

    //set address type
    public void setAddressType(String type)
    {
        /**
            type can be either "VISITING_ADDRESS" or "RESIDENT_ADDRESS"
        */
        AddressType [] values = AddressType.values();
        for (AddressType addType : values) 
        {
            if ((addType.toString().equalsIgnoreCase(type)) && (addType.toString().equals("VISITING_ADDRESS")))
            {
                addressType = addType.toString();
            }
            else if ((addType.toString().equalsIgnoreCase(type)) && (addType.toString().equals("RESIDENT_ADDRESS")))
            {
                addressType = addType.toString();
            }
        }
    }

    public String getAddressType()
    {
        //get address type
        return addressType;
    }


    //set postal code
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    //get postal code
    public String getPostalCode()
    {
        return postalCode;
    }

    //set town
    public void setTown(String town)
    {
        this.town = town;
    }

    //get town
    public String getTown()
    {
        return town;
    }
}
