/**
 *
 * @author kenneth Odoh
 */


import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.File;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import com.ibm.xml.parser.TXDocument;

import java.io.StringWriter;
import java.io.StringReader;
import java.io.PrintWriter;
import java.io.DataInputStream ;         

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import java.io.FileWriter;
import java.io.BufferedWriter;



public class XMLDOM
{

	private Document dom;  //Document object
    private Element root;  //Root
    private String filename; //filename
    private String newFileName; //copied file

    public XMLDOM(String filename)
    {
        this.filename = filename;
        loadXmlFile(filename);
		//get the root elememt
        root = dom.getDocumentElement();
    }

	private void loadXmlFile(String filename) //load XML object
    {
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(filename);
			

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

    //for unique element Get the nodevalue of an element
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

    //for repetitive element get the nodevalue into an array
	private ArrayList<String>getTextValueArray(Element ele, String tagName)
    {
		String textVal = null;
        ArrayList<String>output = new ArrayList<String>();
		NodeList nl = ele.getElementsByTagName(tagName);  //get the element by tagname
		if(nl != null && nl.getLength() > 0) 
        {
            for (int i = 0; i < nl.getLength(); i ++) 
            {
			    Element el = (Element)nl.item(i);
			    textVal = el.getFirstChild().getNodeValue(); //get node value of the first child of the element
                output.add(textVal);
            }
		}
		return output;
	}

    // remove all the null values in an array of string
    private String[] removeNull(String[] a) {
       ArrayList<String> removed = new ArrayList<String>();
       for (String str : a)
          if (str != null)
             removed.add(str);
       return removed.toArray(new String[0]);
    }


    //returns a collection of customer objects
    public ArrayList<Customer> getAllCustomer()
    {
        String[] elements={"Name", "Address", "Phone", "Email", "Notes", "ID"};
        String[] tags={"Type", "Street", "PostalCode", "Town"};
        String[] pTag = {"Type", "Value" };

        ArrayList<Customer> custObjList = new ArrayList<Customer>(); //container for the customer object

        //ArrayList<String> street = null;
        for (Node kvchild = root.getFirstChild(); kvchild != null; kvchild = kvchild.getNextSibling()) 
        {
            if (kvchild instanceof Element) //get the child that are elements
            { 
                //create customer object here
                Customer custObj = new Customer();
                ArrayList<String> street = null;
                String name = null, note = null, id = null;
                String[] addproperty = new String[tags.length];
                String[] emproperty = new String[pTag.length];
                String[] phproperty = new String[pTag.length];  
                
                NodeList children = kvchild.getChildNodes(); //get its children
                for(int i=0; i<children.getLength(); i++)
                {
                    Node childNode = children.item(i);
                    if (childNode instanceof Element) 
                    { 
                        if( childNode.getNodeName().equals(elements[1]))
                        {
                            //Address
                            NodeList grandchildren = childNode.getChildNodes();
                            for(int counter=0; counter<grandchildren.getLength(); counter++)
                            {
                                Node grandchildNode = grandchildren.item(counter);
                                if (grandchildNode instanceof Element) 
                                {

                                    String[] addprop = new String[tags.length];
                                    for (int count = 0; count < (tags.length); count++ )
                                    {
                                       
                                        if(grandchildNode.getNodeName().equals(tags[count]))
                                        {
                                            if (count != 1)
                                            {
                                                addprop[count] = grandchildNode.getFirstChild().getTextContent();
                                            }else
                                            {
                                                street = getTextValueArray((Element)childNode, tags[1]); //get text from repetitive tags into an arraylist
                                            }

                                            if (addprop[count] != null) // remove nulls
                                            {
                                                addproperty[count] = addprop[count];     
                                            }
                                            
                                        }
                                    }
                                }
                            }
                        }


                        if( childNode.getNodeName().equals(elements[2]))
                        {
                            //Phone
                            NodeList grandchildren = childNode.getChildNodes();
                            for(int counter=0; counter<grandchildren.getLength(); counter++)
                            {
                                Node grandchildNode = grandchildren.item(counter);
                                if (grandchildNode instanceof Element) 
                                {
                                    String[] phprop = new String[pTag.length];
                                    for (int count = 0; count < (pTag.length); count++ )
                                    {
                                        if(grandchildNode.getNodeName().equals(pTag[count]))
                                        {
                                            phprop[count] = grandchildNode.getFirstChild().getTextContent() ;  

                                            if (phprop[count] != null) // remove nulls
                                            {
                                                phproperty[count] = phprop[count];     
                                            }      
                                        }
                                    }
                                }
                            }
                        } 

                        if( childNode.getNodeName().equals(elements[3]))
                        {
                            //Email
                            NodeList grandchildren = childNode.getChildNodes();
                            for(int counter=0; counter<grandchildren.getLength(); counter++)
                            {
                                Node grandchildNode = grandchildren.item(counter);
                                if (grandchildNode instanceof Element) 
                                {
                                    String[] emprop = new String[pTag.length];
                                    for (int count = 0; count < pTag.length; count++ )
                                    {
                                        if(grandchildNode.getNodeName().equals(pTag[count]))
                                        {
                                            emprop[count] = grandchildNode.getFirstChild().getTextContent();   

                                            if (emprop[count] != null) // remove nulls
                                            {
                                                emproperty[count] = emprop[count];     
                                            }
                                        }   
                                    }
                                }
                            }
                        } 

                        if( childNode.getNodeName().equals(elements[0]))
                        {
                            //name
                            name = childNode.getFirstChild().getTextContent();
                        }

                        if( childNode.getNodeName().equals(elements[4]))
                        {
                            //note
                            note = childNode.getFirstChild().getTextContent();
                        } 

                        if( childNode.getNodeName().equals(elements[5]))
                        {
                            //ID
                            id = childNode.getFirstChild().getTextContent();

                        }
                    }     
                }
                //set the properties of the customer
                custObj.setCustomerName(name);  //set customer's name
                custObj.setCustomerID(id);      //set customer's id
                custObj.setNote(note);          //set customer's note
                custObj.addNewAddress(addproperty[0], street, addproperty[2], addproperty[3]); //set customer's address
                custObj.addNewPhone(phproperty[0], phproperty[1]);                              //add customer phone
                custObj.addNewEmail(emproperty[0], emproperty[1]);                              //add customer's email
                custObjList.add(custObj); // add to the collection
            }
        } 

        return custObjList;   

    } //end of method

    //remove element based on element ID

    public void removeElement( String ID )
    {
        String[] elements={"Name", "Address", "Phone", "Email", "Notes", "ID"};
        int counter = 0;

        //ArrayList<String> street = null;
        for (Node kvchild = root.getFirstChild(); kvchild != null; kvchild = kvchild.getNextSibling()) 
        {
            if (kvchild instanceof Element) 
            {   
                //System.out.println(kvchild.getNodeName());
                NodeList children = kvchild.getChildNodes();
                for(int i=0; i<children.getLength(); i++)
                {
                    Node childNode = children.item(i);
                    if (childNode instanceof Element) 
                    {               
                        if(( childNode.getNodeName().equals(elements[5])) && (childNode.getFirstChild().getTextContent().equals(ID)))
                        {
                            //ID use the ID to search for the node
                            root.removeChild(kvchild); // remove the node
                            writeToXMLFile();
                        }
                        counter++;
                    }     
                }
            }
        }
    }


    //update element based on element ID and tag name  (Single Tags)
    public void upDateElemenTtags( String ID, String tagName, String upDatString )
    {
        //String[] elements={"Name", "Address", "Phone", "Email", "Notes", "ID"};
        String[] elements={"Name", "Notes", "ID"}; //single occuring tags
        boolean status = false;
        String IDTAG = "ID";

        for(int count=0 ; count < elements.length; count++)
        {
            if (tagName.equals(elements[count]))
            {
                status = true;
                break;  
            }
        }
        int counter = 0;

        for (Node kvchild = root.getFirstChild(); kvchild != null; kvchild = kvchild.getNextSibling()) 
        {
            if (kvchild instanceof Element) 
            {   
                //System.out.println(kvchild.getNodeName());
                NodeList children = kvchild.getChildNodes();
                for(int i=0; i<children.getLength(); i++)
                {
                    Node childNode = children.item(i);
                    if (childNode instanceof Element) 
                    {   
                        Node parent;            
                        if(( childNode.getNodeName().equals(IDTAG)) && (childNode.getFirstChild().getTextContent().equals(ID)))
                        {
                            parent = childNode.getParentNode();
                            NodeList immediateChild = parent.getChildNodes();
                            for(int cnt=0; cnt<immediateChild.getLength(); cnt++)
                            {
                                Node nwchildNode = immediateChild.item(cnt);
                                if (nwchildNode instanceof Element) 
                                {
                                    if (status)
                                    {
                                        if( nwchildNode.getNodeName().equals(tagName))
                                        {
                                            nwchildNode.getFirstChild().setTextContent(upDatString);
                                            
                                        } 
                                    }
                                } 
                            }

                            writeToXMLFile();  //save changes to the XML file
                        }

                        counter++;
                    }     
                }
            }
        }
    }


    //update element based on element ID and tag name  (Multiple Tags)
    public void upDateElemenTtags( String ID, String tagName, String subTag, String upDatString, String oldString )
    {
        //String[] elements={"Name", "Address", "Phone", "Email", "Notes", "ID"};
        String[] elements={"Address", "Phone", "Email" }; //single occuring tags
        boolean status = false;
        String IDTAG = "ID";

        for(int count=0 ; count < elements.length; count++)
        {
            if (tagName.equals(elements[count]))
            {
                status = true;
                break;  
            }
        }
        int counter = 0;

        //ArrayList<String> street = null;
        for (Node kvchild = root.getFirstChild(); kvchild != null; kvchild = kvchild.getNextSibling()) 
        {
            if (kvchild instanceof Element) 
            {   
                //System.out.println(kvchild.getNodeName());
                NodeList children = kvchild.getChildNodes();
                for(int i=0; i<children.getLength(); i++)
                {
                    Node childNode = children.item(i);
                    if (childNode instanceof Element) 
                    {   
                        Node parent;            
                        if(( childNode.getNodeName().equals(IDTAG)) && (childNode.getFirstChild().getTextContent().equals(ID)))
                        {
                            parent = childNode.getParentNode();
                            NodeList immediateChild = parent.getChildNodes();
                            for(int cnt=0; cnt<immediateChild.getLength(); cnt++)
                            {
                                Node nwchildNode = immediateChild.item(cnt);
                                if (nwchildNode instanceof Element) 
                                {
                                    if (status)
                                    {
                                        if( nwchildNode.getNodeName().equals(tagName))
                                        {
                                            NodeList ngrandchildren = nwchildNode.getChildNodes();
                                            for( counter=0; counter<ngrandchildren.getLength(); counter++)
                                            {
                                                Node nngrandchildNode = ngrandchildren.item(counter);
                                                if (nngrandchildNode instanceof Element) 
                                                {
                                                    if(nngrandchildNode.getNodeName().equals(subTag) )
                                                    {
                                                        if(nngrandchildNode.getFirstChild().getTextContent().equalsIgnoreCase(oldString) ) //check for old content
                                                        {
                                                            nngrandchildNode.getFirstChild().setTextContent(upDatString);
                                                        }
                                                    } 
                                                }
                                            }
                                        } 
                                    }
                                } 
                            }

                            writeToXMLFile(); //save changes to the file
                        }

                        counter++;
                    }     
                }
            }
        }
    }

    private void writeToXMLFile()
    {
        try
        {
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    DOMSource source = new DOMSource(this.dom);
		    StreamResult result = new StreamResult(new File(this.filename));
		    transformer.transform(source, result);
            System.out.println("Done");
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
        }
		
    }

    private String loadXMLFileToString()
    {
        StringBuilder sb = new StringBuilder();
        try 
        {
            FileInputStream fstream = new FileInputStream(this.filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str = br.readLine()) != null) 
            {
                sb.append(str);
            }
            in.close();
        } catch (Exception e) 
        {
          e.printStackTrace();
        }
        return sb.toString();
    }


    private String RemoveEmptyElementsXMLString() //remove empty elements from the XML file
    {
        String xml = loadXMLFileToString(); //get the XML
        xml = xml.replaceAll("<(\\w+)></\\1>|<\\w+/>", "");
        return xml;
    }

    private void getXMLFileWithEmptyTags() // write to a new file the contents of the XML file
    {
        try{
            // Create new file 
            newFileName = this.filename;
            newFileName = newFileName.substring(0, newFileName.lastIndexOf('.'));
            newFileName = newFileName +"new.xml";

            FileWriter fstream = new FileWriter(newFileName);
            BufferedWriter out = new BufferedWriter(fstream);
            String fileContent = RemoveEmptyElementsXMLString();
            out.write(fileContent);  //write the content to the file
            //Close the output stream
            out.close();
            this.filename = newFileName;
        }catch (Exception e)
        {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String fileUniqueElementsFileName()
    {
        getXMLFileWithEmptyTags(); //create new file with unique tags
        return newFileName;
    }



    public void CreateCustomerByLoadingXML() 
    {
        boolean accept = false;
      try {
          //create a scanner object
          Scanner scanner = new Scanner(System.in);
          String elementName = null;

        for (Node kvchild = root.getFirstChild(); kvchild != null; kvchild = kvchild.getNextSibling()) 
        {
            if (kvchild instanceof Element) 
            { 
                if (kvchild.getNodeName() != null)
                {
                    //get the element name
                    elementName = kvchild.getNodeName();
                    break;
                }
                else
                {
                    elementName = "Customer";
                }
            }
        }


          String[] elements={"Name", "Address", "Phone", "Email", "Notes", "ID"};
          String[] tags={"Type", "Street", "PostalCode", "Town", "Value"};

          Element subItem = null;

          //create customer object here
          int tmp = 0;
          String input = null;
          int Limit = 0;
          // Makes the "Customer" element as the cust, and adds it.
          Element cust = dom.createElement(elementName);


          // Makes the "Name" element, and adds it.
          Element item = dom.createElement(elements[0]);
          System.out.println("Customer");
          System.out.print("<Customer name>: ");
          input = scanner.nextLine();
          item.appendChild(dom.createTextNode(input));
          cust.appendChild(item);


          // Makes the "Address" element, and adds it.


          // Makes the "Address" element, and adds it.
          System.out.println("Address");
          

          System.out.print("Enter the number of Address Tag : ");
          input = scanner.nextLine();
          while (true )
          {
              if(Validator.isNumeric(input))
              {
                  break;
              }
              else
              {
                    input = scanner.nextLine();
              }
          }
          Limit = Integer.parseInt( input );

          for (int count = 0; count < Limit; count++)
          {

              System.out.println("<Address" +(count + 1) +"> ");
              item = dom.createElement(elements[1]);
              //Type sub-element
              subItem = dom.createElement(tags[0]);
              System.out.print("<Type>: ");
              input = scanner.nextLine();
              while (true )
              {
                if(input != null)
                {
                    break;
                }
                else
                {
                    input = scanner.nextLine();
                }
              }
              subItem.appendChild(dom.createTextNode(input));
              item.appendChild(subItem);

              //Street sub-element
              subItem = dom.createElement(tags[1]);

              System.out.print("<Street>: ");

              System.out.print("Enter the number of Streets Tag : ");
              input = scanner.nextLine();
              while (true )
              {
                if(Validator.isNumeric(input))
                {
                  break;
                }
                else
                {
                    input = scanner.nextLine();
                }
              }
              Limit = Integer.parseInt( input );
              //Element newItem = null;
              for (int counters = 0; counters < Limit; counters++)
              {

                    System.out.print("<Street " +(counters + 1) +">: ");
                    input = scanner.nextLine();
                    if (input != null)
                    {
                        Element newItem = dom.createElement(tags[1]);
                        newItem.appendChild(dom.createTextNode(input));
                        //subItem.appendChild(newItem);
                        item.appendChild(newItem);
                        //subItem.appendChild(dom.createTextNode(input));
                    }
              }

              item.appendChild(subItem);
              //PostalCode sub-element
              subItem = dom.createElement(tags[2]);
              System.out.print("<PostalCode>: ");
              input = scanner.nextLine();
              while (true )
              {
                if(Validator.isNumeric(input))
                {
                    break;
                }
                else
                {
                    input = scanner.nextLine();
                }
              }
              subItem.appendChild(dom.createTextNode(input));
              item.appendChild(subItem);
              //Town sub-element
              subItem = dom.createElement(tags[3]);

              System.out.print("<Town>: ");
              input = scanner.nextLine();
              subItem.appendChild(dom.createTextNode(input));
              item.appendChild(subItem);
              //item.appendChild(doc.createTextNode("35"));
              cust.appendChild(item);

        }


          // Makes the "Phone" element, and adds it.
          System.out.println("Phone");

          System.out.print("Enter the number of Phone Tag : ");
          input = scanner.nextLine();
          while (true )
          {
              if(Validator.isNumeric(input))
              {
                  break;
              }
              else
              {
                    input = scanner.nextLine();
              }
          }
          Limit = Integer.parseInt( input );

          for (int count = 0; count < Limit; count++)
          {

                System.out.println("<Phone " +(count + 1) +"> ");
                  item = dom.createElement(elements[2]);
                  //Type sub-element
                  subItem = dom.createElement(tags[0]);
                  System.out.print("<Type>: ");
                  input = scanner.nextLine();
                  while (true )
                  {
                    if(input != null)
                    {
                        break;
                    }
                    else
                    {
                        input = scanner.nextLine();
                    }
                  }
                  subItem.appendChild(dom.createTextNode(input));
                  item.appendChild(subItem);
                  //Value sub-element

                  subItem = dom.createElement(tags[4]);
                  System.out.print("<Value>: ");
                  input = scanner.nextLine();
                  while (true)
                  {
                    if( Validator.isPhone(input) )
                    {
                        break;
                    }
                    else
                    {
                        input = scanner.nextLine();
                    }
                  }
                    subItem.appendChild(dom.createTextNode(input));
                  item.appendChild(subItem);

                  cust.appendChild(item);
            }


          // Makes the "Email" element, and adds it.
          System.out.println("Email");

          System.out.print("Enter the number of Email Tag : ");
          input = scanner.nextLine();
          while (true )
          {
              if(Validator.isNumeric(input))
              {
                  break;
              }
              else
              {
                    input = scanner.nextLine();
              }
          }
          Limit = Integer.parseInt( input );

          for (int count = 0; count < Limit; count++)
          {

                System.out.println("<Email " +(count + 1) +"> ");
              item = dom.createElement(elements[3]);
              //Type sub-element
              subItem = dom.createElement(tags[0]);
              System.out.print("<Type>: ");
              input = scanner.nextLine();
              while (true )
              {
                if(input != null)
                {
                    break;
                }
                else
                {
                    input = scanner.nextLine();
                }
              }
              subItem.appendChild(dom.createTextNode(input));
              item.appendChild(subItem);

              subItem = dom.createElement("Value");
              System.out.print("<Value>: ");
              input = scanner.nextLine();
              while (true)
              {
                    if( Validator.isEmailValid(input) )
                    {
                        break;
                    }
                    else
                    {
                        input = scanner.nextLine();
                    }
              }
              subItem.appendChild(dom.createTextNode(input));
              item.appendChild(subItem);
              cust.appendChild(item);
        }

          // Makes the "Notes" element, and adds it.
          System.out.println("Notes");
          item = dom.createElement(elements[4]);
          System.out.print("<Notes>: ");
          input = scanner.nextLine();
          item.appendChild(dom.createTextNode(input));
          cust.appendChild(item);

          // Makes the "ID" element, and adds it.
          System.out.println("ID");
          item = dom.createElement(elements[5]);
          System.out.print("<ID>: ");
          input = scanner.nextLine();
          while (true)
          {
                    if( Validator.isNumeric(input) )
                    {
                        break;
                    }
                    else
                    {
                        input = scanner.nextLine();
                    }
          }
          item.appendChild(dom.createTextNode(input));


          cust.appendChild(item);

          root.appendChild(cust); //add customer to customers

          // 
          //dom.appendChild(root);

          //dom = modifiedDoc();
          writeToXMLFile();

      }  catch (Exception e) {
          e.printStackTrace();
      }
   }

}
