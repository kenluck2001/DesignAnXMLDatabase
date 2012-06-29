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

public class XMLDOM
{

	private Document dom;   //Document object
    private Element root;   //root
    private String filename;//filename of XML file

    public XMLDOM(String filename)
    {
        this.filename = filename;
        loadXmlFile(filename);
		//get the root elememt
        root = dom.getDocumentElement();
    }

	private void loadXmlFile(String filename)
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

	private Map<String, String[] >  loadELementsWithTags() 
    {
        Map<String, String[] > hm = new HashMap<String, String[] >();

        String[] elements={"Name", "Address", "Phone", "Email", "Notes"};
        String[] nTag = null;
        String[] aTag = {"Type", "Street", "PostalCode", "Town"};
        String[] pTag = {"Type", "Value" };
        String[] eTag = {"Type", "Value" };
        String[] noteTag = null;


        hm.put(elements[0], nTag);
        hm.put(elements[1], aTag);
        hm.put(elements[2], pTag);
        hm.put(elements[3], eTag);
        hm.put(elements[4], noteTag);

        return hm;
	}

    //for unique element
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

    //for repetitive element
	private ArrayList<String>getTextValueArray(Element ele, String tagName)
    {
		String textVal = null;
        ArrayList<String>output = new ArrayList<String>();
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) 
        {
            for (int i = 0; i < nl.getLength(); i ++) 
            {
			    Element el = (Element)nl.item(i);
			    textVal = el.getFirstChild().getNodeValue();
                output.add(textVal);
            }
		}
		return output;
	}

    private String[] removeNull(String[] a) {
       ArrayList<String> removed = new ArrayList<String>();
       for (String str : a)
          if (str != null)
             removed.add(str);
       return removed.toArray(new String[0]);
    }


    public Customer getCustomer()
    {
        //create customer object
        Customer custObj = new Customer();
        String[] elements={"Name", "Address", "Phone", "Email", "Notes"};
        String[] tags={"Type", "Street", "PostalCode", "Town"};
        String[] pTag = {"Type", "Value" };

        String[] addproperty = new String[tags.length];
        String[] emproperty = new String[pTag.length];
        String[] phproperty = new String[pTag.length];
        String name = null , note = null;
        ArrayList<String>street = null;


        for (Node kvchild = root.getFirstChild(); kvchild != null; kvchild = kvchild.getNextSibling()) 
        {
            if (kvchild instanceof Element) 
            {   
                //System.out.println(kvchild.getNodeName()) ;
                //getELementChildren(Element empEl, String[] childnodes)  
                NodeList children = kvchild.getChildNodes();
                //System.out.println(kvchild.getNodeName()) ;
                for(int i=0; i<children.getLength(); i++)
                {
                    Node childNode = children.item(i);
                    String[] elementTags =  loadELementsWithTags().get(kvchild.getNodeName());
                    
                    if ((childNode != null) && (elementTags != null)) //get all element that has a child element
                    {
                        for (int count = 0; count < elementTags.length; count++)
                        {
                            if (childNode.getNodeName().equals(elementTags[count]))
                            {
                                if( kvchild.getNodeName().equals(elements[1]))
                                {
                                    
                                    //Address
                                    String[] addprop = new String[tags.length];
                                    for (count = 0; count < (tags.length); count++ )
                                    {
                                       
                                        if(childNode.getNodeName().equals(tags[count]))
                                        {
                                            if (count != 1)
                                            {
                                                addprop[count] = childNode.getFirstChild().getTextContent();
                                            }else
                                            {
                                                street = getTextValueArray((Element)kvchild, tags[1]); //get text from repetitive tags into an arraylist
                                            }

                                            if (addprop[count] != null) // remove nulls
                                            {
                                                addproperty[count] = addprop[count];     
                                            }
                                            
                                        }
                                    }


                                    //System.out.println(addproperty[0] + "-----" + addproperty[2]+ "------"+ addproperty[3]);
                                    //custObj.addNewAddress(addprop[0], street, addprop[2], addprop[3]);                                
                                }

                                if( kvchild.getNodeName().equals(elements[3]))
                                {
                                    //Email
                                    String[] emprop = new String[pTag.length];
                                    //System.out.println(pTag.length);
                                    for (count = 0; count < pTag.length; count++ )
                                    {
                                        if(childNode.getNodeName().equals(pTag[count]))
                                        {
                                            //System.out.println(childNode.getFirstChild().getTextContent()+ "   "+ count);  
                                            
                                            //System.out.println(pTag[count]);
                                            emprop[count] = childNode.getFirstChild().getTextContent();   

                                            if (emprop[count] != null) // remove nulls
                                            {
                                                emproperty[count] = emprop[count];     
                                            }
                                        }   
                                    }
                                }                           
                                if( kvchild.getNodeName().equals(elements[2]))
                                {
                                    //Phone
                                    String[] phprop = new String[pTag.length];
                                    for (count = 0; count < (pTag.length); count++ )
                                    {
                                        if(childNode.getNodeName().equals(pTag[count]))
                                        {
                                            //System.out.println(childNode.getFirstChild().getTextContent());   
                                            phprop[count] = childNode.getFirstChild().getTextContent() ;  

                                            if (phprop[count] != null) // remove nulls
                                            {
                                                phproperty[count] = phprop[count];     
                                            }      
                                        }
                                    }
                                }        
                            }              
                        }
                    }else
                    {

                        //System.out.println(kvchild.getFirstChild().getTextContent()); //get the text content of a node
                        //System.out.println(childNode.getNodeName()+ "----"+ kvchild.getNodeName());
                        /**
                            #text----Name
                            #text----Notes
                        */
                        if( kvchild.getNodeName().equals(elements[0]))
                        {
                            //name
                            name = kvchild.getFirstChild().getTextContent();
                        }

                        if( kvchild.getNodeName().equals(elements[4]))
                        {
                            //note
                            note = kvchild.getFirstChild().getTextContent();
                        }              
                    }
                }
            }
        } 

        //set the properties of the customer
        custObj.setCustomerName(name);
        custObj.setNote(note);
        custObj.addNewAddress(addproperty[0], street, addproperty[2], addproperty[3]);
        custObj.addNewPhone(phproperty[0], phproperty[1]);
        custObj.addNewEmail(emproperty[0], emproperty[1]);

        return custObj;

        //
   
    } //end of method


}

