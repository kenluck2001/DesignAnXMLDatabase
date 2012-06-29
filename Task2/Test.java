/**
 *
 * @author kenneth Odoh
 */

public class Test
{

    public static void main(String []args)
    {
        XMLDOMTEST dom = new XMLDOMTEST(args[0]);
        dom.printAllCustomers();

        //delete customer using the customer ID

        dom.removeElement ("1");


        //create new customer
        dom.createCustomer();

        //update a customer
        dom.upDateElement( "2", "Name", "Kenneth Odoh" );
    
        //if we need to modify the Street tags as it occured twice. You need the old text in the node before updating the node.
        dom.upDateElement( "2", "Address", "Street", "Frantsinkatu 5H 85", "Customer Street 8 B 9" );

        //print to see changes
        dom.printAllCustomers();


        
    }

}
