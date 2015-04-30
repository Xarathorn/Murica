import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class Interface {
	private static String[] stateNames = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
			"Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
			"Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
			"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
			"Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
	private static String[] stateAbrvs = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
			"MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX",
			"UT", "VT", "VA", "WA", "WV", "WI", "WY"};
	private static HashMap<String, String> states = new HashMap<String, String>();
	
	public static void main(String[] args) throws Exception {
		//Assign variables
		for (int i = 0; i < stateNames.length; i++)
			states.put(stateNames[i], stateAbrvs[i]);
		Scanner kin = new Scanner(System.in);
		boolean keepOn = true;

		System.out.println("Hey there! This'll find info about any city in the US! Huzzah for procrastination!\n");
		
		//Update?
		System.out.print("Update files? (y/n):  ");
		if (kin.nextLine().equals("y"))
			updateFiles();
		
		//States loop
		while (keepOn) {
			System.out.print("\nEnter state:  ");
			String state = kin.nextLine();
			State st8;
			if (states.containsKey(state))
				st8 = new State(states.get(state));
			else if (states.containsValue(state.toUpperCase()))
				st8 = new State(state);
			else
				continue;
			
			//Cities loop
			while (true) {
				System.out.print("\nEnter city:  ");
				String city = kin.nextLine().trim();
				ArrayList<City> cities = st8.getCityByName(city);
				System.out.println("There are "+cities.size()+" cities by that name in "+st8+".");
				System.out.print("New city? (y/n):  ");
				if (!kin.nextLine().equals("y"))
					break;
			}
			System.out.print("New state? (y/n):  ");
			keepOn = kin.nextLine().equals("y");
		}
		kin.close();
	}
	
	/**
	 * Updates xml files containing city information
	 */
	public static void updateFiles(){
		try {
			System.out.println("Updating files...");
			for (String s : stateAbrvs) {
				System.out.println(s);
			    URL url = new URL("http://api.sba.gov/geodata/city_links_for_state_of/"+s+".xml");
			    URLConnection conn = url.openConnection();
			
			    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			    DocumentBuilder builder = factory.newDocumentBuilder();
			    Document doc = builder.parse(conn.getInputStream());
			
			    TransformerFactory tfactory = TransformerFactory.newInstance();
			    Transformer xform = tfactory.newTransformer();
			    
			    File myOutput = new File("states/"+s+".xml");
			    xform.transform(new DOMSource(doc), new StreamResult(myOutput));
			}
			System.out.println("\nDone with update!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
