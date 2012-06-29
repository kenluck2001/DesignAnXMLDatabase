/**
 *
 * @author kenneth Odoh
 */

import java.util.ArrayList;

public class XMLDOMTEST
{

    //CRUD
    //Create, update and delete
    private XMLDOM dom;

    public XMLDOMTEST(String fileName )
    {
        dom = new XMLDOM(fileName);
    }

    public void createCustomer()
    {
        //Create a new Element in the XML file and save the new file. Therefore the file is updated.
        //we can create a new instance using the new file
        dom.CreateCustomerByLoadingXML();
    }

    public void removeElement (String ID)
    {
        //However, since we are trying to create an XML database. We need to get all the Custoemrs ID.This allows us to identify a customer even if they share the same name. We share to the child element of the Customer and get the parent. Then we delete the node. We can delete a customer if we know the ID. This kind of XML database requires that there are method to ensure that their are not duplicate IDs. In a production-ready software, the ID should be issued by a method in the class. This would work properly if the ID are unique. This would serve as the primary key of each entity.
        dom.removeElement(ID );
    }

    public void upDateElement(String ID, String tagName, String upDatString )
    {
        //update
        //The update method is a bit complex. It depends on the nature of the Element to be updated. If the element is used in the child node. We just need only the name of the node to make the change by setting the firstchild of the node using the setText method.
        dom.upDateElemenTtags( ID, tagName, upDatString );
    }

    public void upDateElement(String ID, String tagName, String subTag, String upDatString, String oldString )
    {
        //if we need to modify the Street tags as it occured twice. You need the old text in the node before updating the node.     
        dom.upDateElemenTtags( ID, tagName, subTag, upDatString, oldString );
    }

    public void printAllCustomers()
    {

        //The program has a feature that adds an empty street tag. The method to get all the customer cannot accept XML with empty tags. Therefore, we need to create a new file in the same directory. This new file will used a replaceAll method of the string class to perform a form of regular expression matching. This removes all the empty tag and give use only tags that don't have null values.

        //get the filename of the file with unique tags that are not empty
        String newFileName = dom.fileUniqueElementsFileName(); //copied file 

        //we can create a new instance using the new file
        dom = new XMLDOM(newFileName);

        //The customers are returned as appropriate. This is actually an Array of customer objects that can be iterated to get the field of the class.     
        //System.out.println(dom.getAllCustomer());

        ArrayList<Customer>allCustObj = dom.getAllCustomer();
        for (int i=0; i< allCustObj.size(); i++)
        {

            Customer custobj = allCustObj.get(i);

            System.out.println("########################################");
            

            System.out.println("Name : " + custobj. getCustomerName());
            System.out.println("Note : " + custobj.getNote());

            //Address
            ArrayList<Address> allAddr = custobj.getAddresses();

            System.out.println("Address");
            System.out.println("----------");
            System.out.println("");

            for (int count1=0; count1< allAddr.size(); count1++)
            {
                Address addOj = allAddr.get(count1);
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

            for (int count2=0; count2< allPh.size(); count2++)
            {
                Phone phOj = allPh.get(count2);
                System.out.println( "Type : " + phOj.getPhoneType());
                System.out.println( "Value : " + phOj.getPhoneNumber());
            }

            //email

            System.out.println("");
            ArrayList<Email> emPh = custobj.getEmails();

            System.out.println("Email");
            System.out.println("-------");
            System.out.println("");

            for (int count3=0; count3< emPh.size(); count3++)
            {
                Email emOj = emPh.get(count3);
                System.out.println( "Type : " + emOj.getEmailType());
                System.out.println( "Value : " + emOj.getEmailAddress());
            }
            
        }
    }


}

