import java.util.ArrayList;


public class XMLCustomerTest
{
    public static void main(String []args)
    {
        XMLDOM dom = new XMLDOM(args[0]); //create XMLDOM object
        Customer custobj = dom.getCustomer();//get the customer object from the XML file

        System.out.println("##############################################");

        System.out.println("Name : " + custobj. getCustomerName());
        System.out.println("Note : " + custobj.getNote());

        //Address
        ArrayList<Address> allAddr = custobj.getAddresses();

        System.out.println("Address");
        System.out.println("----------");
        System.out.println("");

        for (int i=0; i< allAddr.size(); i++)
        {
            Address addOj = allAddr.get(i);
            System.out.println( "Type : " + addOj.getAddressType());
            System.out.println( "street : " + addOj.getStreet()); //returns arraylist
            System.out.println( "Postal Code : " + addOj.getPostalCode());
            System.out.println( "Town : " + addOj.getTown());
        }

        //phone

        ArrayList<Phone> allPh = custobj.getPhones();

        System.out.println("Phone");
        System.out.println("--------");
        System.out.println("");

        for (int i=0; i< allPh.size(); i++)
        {
            Phone phOj = allPh.get(i);
            System.out.println( "Type : " + phOj.getPhoneType());
            System.out.println( "Value : " + phOj.getPhoneNumber());
        }

        //email

        System.out.println("");
        ArrayList<Email> emPh = custobj.getEmails();

        System.out.println("Email");
        System.out.println("-------");
        System.out.println("");

        for (int i=0; i< emPh.size(); i++)
        {
            Email emOj = emPh.get(i);
            System.out.println( "Type : " + emOj.getEmailType());
            System.out.println( "Value : " + emOj.getEmailAddress());
        }

        System.out.println("##############################################");

        
    }

}
