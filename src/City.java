import java.net.MalformedURLException;
import java.net.URL;

public class City {
	private String n, cn, fcn;
	private URL url;
	private int fi;
	private double lat, lon;
	
	///INSTANTIATE///
	City (String name, String county_name, String feature_id, String full_county_name, String u, String latitude, String longitude) {
		n = name;
		cn = county_name;
		fi = Integer.parseInt(feature_id);
		fcn = full_county_name;
		lat = Double.parseDouble(latitude);
		lon = Double.parseDouble(longitude);
		try{
			url = new URL(u);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	///GET METHODS///
	public String getName() {
		return n;
	}
	public String getCountyName() {
		return cn;
	}
	public String getFullCountyName() {
		return fcn;
	}
	public URL getURL() {
		return url;
	}
	public int getFeatureID() {
		return fi;
	}
	public double getLatitude() {
		return lat;
	}
	public double getLongitude() {
		return lon;
	}
	
	public String toString() {
		return n;
	}
}
