
public class Sys {
String type;
String id;
String message;
String country;
String sunrise;
String sunset;
public String getType() {
	if(type == null)
		return "";
	return type;
}
public String getId() {
	if(id == null)
		return "";
	return id;
}
public String getMessage() {
	if(message == null)
		return "";
	return message;
}
public String getCountry() {
	if(country == null)
		return "";
	return country;
}
public String getSunrise() {
	if(sunrise == null)
		return "";
	return sunrise;
}
public String getSunset() {
	if(sunset == null)
		return "";
	return sunset;
}
}
