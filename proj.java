import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;
import java.io.FileWriter;
import org.json.JSONObject;
import java.util.*;
public class proj {

  public static void main(String argv[]) {

    try {
    int temp,tem,tem2;
	File fXmlFile = new File("C:\\Users\\842135\\Documents\\Hackathon\\securityreport_xml.xml");
    FileWriter file = new FileWriter("C:\\Users\\842135\\Documents\\Hackathon\\securityreport_json.json");

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
	JSONObject jsonObject2 = new JSONObject();
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	
	
	NodeList nList = doc.getElementsByTagName("Summary");
	System.out.println("----------------------------");
	
	for (temp = 0; temp < nList.getLength(); temp++)
	{
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) 
		{
			Element eElement = (Element) nNode;
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("TotalIssues ",eElement.getElementsByTagName("TotalIssues").item(0).getTextContent());
			data.put("TotalVariants ",eElement.getElementsByTagName("TotalVariants").item(0).getTextContent());
			data.put("TotalRemediations ",eElement.getElementsByTagName("TotalRemediations").item(0).getTextContent());
			data.put("TotalScanDuration ",eElement.getElementsByTagName("TotalScanDuration").item(0).getTextContent());
			
			NodeList list=doc.getElementsByTagName("Host");
			Map<String, Object> data2 = new HashMap<String, Object>();
			for(tem=0;tem <list.getLength();tem++ )
			{
				Node n2=list.item(tem);
				System.out.println("Current Element:"+ n2.getNodeName());
				Element element=(Element)n2;
				data2.put("Host Name",element.getAttribute("Name"));
				data2.put("TotalInformationalIssues ",eElement.getElementsByTagName("TotalInformationalIssues").item(0).getTextContent());
				data2.put("TotalLowSeverityIssues ",eElement.getElementsByTagName("TotalLowSeverityIssues").item(0).getTextContent());
				data2.put("TotalMediumSeverityIssues ",eElement.getElementsByTagName("TotalMediumSeverityIssues").item(0).getTextContent());
				data2.put("TotalHighSeverityIssues ",eElement.getElementsByTagName("TotalHighSeverityIssues").item(0).getTextContent());
				data2.put("Total ",eElement.getElementsByTagName("Total").item(0).getTextContent());
				
			}
			
			JSONObject jsonObject = new JSONObject(data);
			 jsonObject2 = new JSONObject(data2);
		    System.out.println(jsonObject.toString());
		    System.out.println(jsonObject2.toString());
		   	    
            //FileWriter file = new FileWriter("C:\\Users\\842135\\Documents\\Hackathon\\securityreport_json.json");
            file.write(jsonObject.toString());
            file.write(jsonObject2.toString());
            file.flush();
                    
		}
	}
	NodeList n2List = doc.getElementsByTagName("Results");
	System.out.println("----------------------------");
	
	for (temp = 0; temp < n2List.getLength(); temp++)
	{
		Node nNode = n2List.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) 
		{
			Element eElement = (Element) nNode;
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("Total ",eElement.getElementsByTagName("Total").item(temp).getTextContent());
		
			NodeList list=doc.getElementsByTagName("RemediationTypes");
			Map<String, Object> data2 = new HashMap<String, Object>();
			for(tem=0;tem <list.getLength();tem++ )
			{
				Node n2=list.item(tem);
				System.out.println("Current Element:"+ n2.getNodeName());
				Element element=(Element)n2;
				NodeList list2=element.getElementsByTagName("RemediationType");
				for(tem2=0;tem2 <list2.getLength();tem2++ )
				{
					Node n3=list2.item(tem2);
					Element element2=(Element)n3;
				data2.put("Remediation type",element2.getAttribute("ID"));
				data2.put("Name ",element2.getElementsByTagName("Name"));
				data2.put("Priority ",element2.getElementsByTagName("Priority"));
				
				for (Map.Entry<String,Object> entry : data2.entrySet()) 
				{
					 jsonObject2 = new JSONObject(data2);
		    System.out.println(jsonObject2.toString());
				} 
				// System.out.println("Key = " + entry.getKey() +  ", Value = " + entry.getValue()); 
				System.out.println(jsonObject2.toString());
				}	
			}
			System.out.println(data2);
			
			JSONObject jsonObject = new JSONObject(data);
			
		    System.out.println(jsonObject.toString());
		    
		   	    
            //FileWriter file = new FileWriter("C:\\Users\\842135\\Documents\\Hackathon\\securityreport_json.json");
            file.write(jsonObject.toString());
            file.write(jsonObject2.toString());
            file.flush();
                    
		}
	}
	
    }
		 catch (Exception e) {
	e.printStackTrace();
    }
    

  }
}
