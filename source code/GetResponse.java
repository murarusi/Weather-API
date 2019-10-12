import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetResponse {
	StringBuilder sb = new StringBuilder();
	String code;
	String result;
	
	
	GetResponse(String code, String unit) throws IOException{
	 BufferedReader br = null;

     try {

         URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + code + "&units=" + unit + "&APPID=54019f2be1f7592b1916469b6be06211");
         br = new BufferedReader(new InputStreamReader(url.openStream()));

         String line;

         StringBuilder sb = new StringBuilder();

         while ((line = br.readLine()) != null) {

             sb.append(line);
             sb.append(System.lineSeparator());
         }
         result = sb.toString();
                 
         

         
     } finally {

         if (br != null) {
             br.close();
         }
     }
}
}
