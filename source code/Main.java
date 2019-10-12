
public class Main {
String temp;
String presure;
String humidity;
String temp_min;
String temp_max;
String sea_level;
String grnd_level;
public String getTemp() {
	if(temp == null)
		return "";
	return temp;
}
public String getPresure() {
	if(presure == null)
		return "----";
	return presure;
}
public String getHumidity() {
	if(humidity == null)
		return "";
	return humidity;
}
public String getTemp_min() {
	if(temp_min == null)
		return "";
	return temp_min;
}
public String getTemp_max() {
	if(temp_max == null)
		return "";
	return temp_max;
}
public String getSea_level() {
	if(sea_level == null)
		return "";
	return sea_level;
}
public String getGrnd_level() {
	if(grnd_level == null)
		return "";
	return grnd_level;
}
}
