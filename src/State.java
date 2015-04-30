import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class State {
	private ArrayList<City> cities = new ArrayList<City>();
	private File xFile;

	State(String state) {
		try {
			xFile = new File("states/"+state + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xFile);
			
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("site");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					String county_name = eElement.getElementsByTagName("county_name").item(0).getTextContent();
					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					String feature_id = eElement.getElementsByTagName("feature_id").item(0).getTextContent();
					String full_county_name = eElement.getElementsByTagName("full_county_name").item(0).getTextContent();
					String url = eElement.getElementsByTagName("url").item(0).getTextContent();
					String primary_latitude = eElement.getElementsByTagName("primary_latitude").item(0).getTextContent();
					String primary_longitude = eElement.getElementsByTagName("primary_longitude").item(0).getTextContent();
					
					cities.add(new City(name, county_name, feature_id, full_county_name, url, primary_latitude, primary_longitude));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<City> getCityByName(String name) {
		ArrayList<City> city = new ArrayList<City>();
		for (City c : cities)
			if (c.getName().equals(name))
				city.add(c);
		return city;
	}
}
