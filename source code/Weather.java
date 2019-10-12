
public class Weather {
String id;
String main;
String description;
String icon;
public String getId() {
	if(id == null)
		return "";
	return id;
}
public String getMain() {
	if(main == null)
		return "";
	return main;
}
public String getDescription() {
	if(description == null)
		return "";
	return description;
}
public String getIcon() {
	if(icon == null)
		return "";
	return icon;
}
}
