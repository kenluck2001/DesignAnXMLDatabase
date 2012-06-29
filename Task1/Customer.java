/**
 *
 * @author kenneth Odoh
 */
import java.util.ArrayList;

public class Customer
{
    private String name;        //name
    private String note;        //note
    private ArrayList<Address> addresLstObj = new ArrayList<Address>(); //addresses
    private ArrayList<Phone> phoneLstObj = new ArrayList<Phone>();      //phones
    private ArrayList<Email> emailLstObj = new ArrayList<Email>();      //emails
 

    //create Customer object
	public Customer(String nam, String not)
	{
		name = nam;
        note = not;
	}

    //default constructor
	public Customer()
	{
	}


    //set customer name
    public void setCustomerName(String nam)
    {
        name = nam;
    }

    //get customer name
    public String getCustomerName()
    {
        return name;
    }

    //set the notes
    public void setNote(String not)
    {
        note = not;
    }

    //get the notes
    public String getNote()
    {
        return note;
    }

    //add new address to the address list
    public void addNewAddress(String type, ArrayList<String>street, String postCod, String town)
    {
        Address addObj = new Address(type, street, postCod, town);
        addresLstObj.add(addObj);
    }

    //get all addresses
    public ArrayList<Address>getAddresses()
    {
        return addresLstObj;
    }

    //add new phone to the phone list
    public void addNewPhone(String type, String pno)
    {
        Phone phObj = new Phone(type, pno);
        phoneLstObj.add(phObj);
    }

    //get all phone
    public ArrayList<Phone>getPhones()
    {
        return phoneLstObj;
    }

    //add new email to the email list
    public void addNewEmail(String type, String em)
    {
        Email emObj = new Email( type, em);
        emailLstObj.add(emObj);
    }

    //get all email
    public ArrayList<Email>getEmails()
    {
        return emailLstObj;
    }
}
