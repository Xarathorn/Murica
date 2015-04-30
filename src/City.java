
public class City {
	private String n, cn, fi, fcn, url, lat, lon;
	City (String name, String county_name, String feature_id, String full_county_name, String u, String latitude, String longitude) {
		n = name;
		cn = county_name;
		fi = feature_id;
		fcn = full_county_name;
		url = u;
		lat = latitude;
		lon = longitude;
	}
	public String getName() {
		return n;
	}
	public String getCountyName() {
		return cn;
	}
	public String toString() {
		return n;
	}
}
