import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIAndMainClass extends Application {

	BorderPane mainPane = new BorderPane();
	Label lbWheater_main = new Label();
	Label lbWeather_description = new Label();
	Label lbMain_temp = new Label();
	Label lbMain_presure = new Label();
	Label lbMain_humidity = new Label();
	Label lbMain_temp_min = new Label();
	Label lbMain_temp_max = new Label();
	Label lbMain_sea_level_presure = new Label();
	Label lbMain_ground_level_presure = new Label();
	Label lbWind_speed = new Label();
	Label lbWind_deg = new Label();
	Label lbClouds_all = new Label();
	Label lbRain_1h = new Label();
	Label lbRain_3h = new Label();
	Label lbSnow_1h = new Label();
	Label lbSnow_3h = new Label();
	Label lbData = new Label();
	Label lbName = new Label();
	Label lbSunset = new Label();
	Label lbSunrise = new Label();
	TextField tfSearch = new TextField();
	Button btSearch = new Button("Search");
	VBox leftData = new VBox(30);
	VBox rightData = new VBox(30);
	ComboBox<String>  cboCity = new ComboBox<>();
	RadioButton rbDefault = new RadioButton("Default");
	RadioButton rbMetric = new RadioButton("Metric");
	RadioButton rbImperial = new RadioButton("Imperial");
	ToggleGroup tgMeasureUnit = new ToggleGroup();
	Gson parse = new Gson();
	
	
		public static void main(String[] args){ launch(args);}
	

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		leftData.getChildren().addAll(lbName, lbWheater_main, lbWeather_description, lbClouds_all, lbMain_temp, lbMain_temp_min,lbMain_temp_max, lbMain_presure, lbWind_speed, lbWind_deg);
		rightData.getChildren().addAll(lbData, lbMain_humidity, lbSunset, lbSunrise, lbRain_1h, lbRain_3h , lbSnow_1h, lbSnow_3h, lbMain_sea_level_presure, lbMain_ground_level_presure);
		HBox center = new HBox(20);
		center.getChildren().addAll(leftData, rightData);
		
		HBox up = new HBox(30);
		up.getChildren().addAll(tfSearch, btSearch, cboCity);
		up.setPadding(new Insets(	0, 0, 20, 0));
		mainPane.setPadding(new Insets(20));
		updateDefault("2643741");
		cboCity.setValue("London  GB#2643741");
		cboCity.setPrefSize(122, 22);
		
		cboCity.setOnAction(e ->{
			
				if (rbDefault.isSelected())
					updateDefault(GetCode(cboCity.getValue()));
					
				if (rbMetric.isSelected())
					updateMetric(GetCode(cboCity.getValue()));
			
				if (rbImperial.isSelected())
					updateImperial(GetCode(cboCity.getValue()));
			
		});
		
		mainPane.setCenter(center);
		mainPane.setTop(up);
		rbDefault.setToggleGroup(tgMeasureUnit);
		rbMetric.setToggleGroup(tgMeasureUnit);
		rbImperial.setToggleGroup(tgMeasureUnit);
		rbDefault.setSelected(true);
		
		VBox vbMetric = new VBox(10);
		vbMetric.getChildren().addAll(rbDefault, rbMetric, rbImperial);
		mainPane.setRight(vbMetric);
		
		
		
		
		
		btSearch.setOnAction(e ->{ 
			try {
				//add a list of cities to combo box
				cboCity.getItems().addAll(GetList(tfSearch.getText()));
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//updateValues();
		
		});
	
		
		rbDefault.setOnAction(e -> {
			if (rbDefault.isSelected())
				updateDefault(GetCode(cboCity.getValue()));
		});
		rbMetric.setOnAction(e -> {
			if (rbMetric.isSelected())
				updateMetric(GetCode(cboCity.getValue()));
		});
		rbImperial.setOnAction(e -> {
			if (rbImperial.isSelected())
				updateImperial(GetCode(cboCity.getValue()));
		});
		
		
			
		
		
		primaryStage.setResizable(false);
		primaryStage.setWidth(600);
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private String GetCode(String value) {
		String[] split = value.split("#");
		
		return split[1];
	}

	private void updateDefault(String Code) {
		Gson parse = new Gson();
		try {
			Data data = parse.fromJson(new GetResponse(Code, "").result, Data.class);
					
			lbWheater_main.setText("Main : " + data.weather[0].getMain());
			lbWeather_description.setText("Description : " + data.weather[0].getDescription());//lbMain_temp.setText("Temperature now : " + data.main.getTemp() + " K");
			lbMain_presure.setText("Pressure : " + data.main.getPresure() + " hPa");
			lbMain_humidity.setText("Humidity : " + data.main.getHumidity() + " %");
			lbMain_sea_level_presure.setText("Sea level presure : " + data.main.getSea_level() + " hPa");
			lbMain_ground_level_presure.setText("Ground level presure : " + data.main.getGrnd_level() + " hPa");
			lbWind_deg.setText("wind Direction : " + data.wind.getDeg());
			lbClouds_all.setText("Cloudness : " + data.clouds.getAll() + " %");
			lbRain_1h.setText("Rain in last 1h : " + data.getRain().getOne_h() + " mm");
			lbRain_3h.setText("Rain in last 3h : " + data.getRain().getThree_h() + " mm" );
			lbSnow_1h.setText("Snow in last 1h : " + data.getSnow().getOne_h() + " mm");
			lbSnow_3h.setText("Snow in last 3h : " + data.getSnow().getThree_h() + " mm");
			lbData.setText("Time : " + data.convertSecondsToHMmSs(Long.parseLong(data.dt)));
			lbName.setText("City : " + data.getName());
			lbSunset.setText("Sunset : " + data.convertSecondsToHMmSs(Long.parseLong(data.sys.getSunset())));
			lbSunrise.setText("Sunrise : " + data.convertSecondsToHMmSs(Long.parseLong(data.sys.getSunrise())));
			lbMain_temp.setText("Temperature now : " + data.main.getTemp() + " K");
			lbMain_temp_min.setText("Minimum temperature : " + data.main.getTemp_min() + " K");
			lbMain_temp_max.setText("Maximim temperature : " + data.main.getTemp_max() + " K");
			lbWind_speed.setText("Wind speed : " + data.wind.getSpeed() + " m/s");
			mainPane.setLeft(new ImageView(new Image("http://openweathermap.org/img/wn/" + data.weather[0].getIcon() + "@2x.png")));
			
			
			
			
			
		
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void updateMetric(String Code) {
		Gson parse = new Gson();
		try {
			Data data = parse.fromJson(new GetResponse(Code, "metric").result, Data.class);
					
			lbWheater_main.setText("Main : " + data.weather[0].getMain());
			lbWeather_description.setText("Description : " + data.weather[0].getDescription());//lbMain_temp.setText("Temperature now : " + data.main.getTemp() + " K");
			lbMain_presure.setText("Pressure : " + data.main.getPresure() + " hPa");
			lbMain_humidity.setText("Humidity : " + data.main.getHumidity() + " %");
			lbMain_sea_level_presure.setText("Sea level presure : " + data.main.getSea_level() + " hPa");
			lbMain_ground_level_presure.setText("Ground level presure : " + data.main.getGrnd_level() + " hPa");
			lbWind_deg.setText("wind Direction : " + data.wind.getDeg());
			lbClouds_all.setText("Cloudness : " + data.clouds.getAll() + " %");
			lbRain_1h.setText("Rain in last 1h : " + data.getRain().getOne_h() + " mm");
			lbRain_3h.setText("Rain in last 3h : " + data.getRain().getThree_h() + " mm" );
			lbSnow_1h.setText("Snow in last 1h : " + data.getSnow().getOne_h() + " mm");
			lbSnow_3h.setText("Snow in last 3h : " + data.getSnow().getThree_h() + " mm");
			lbData.setText("Time : " + data.convertSecondsToHMmSs(Long.parseLong(data.dt)));
			lbName.setText("City : " + data.getName());
			lbSunset.setText("Sunset : " + data.convertSecondsToHMmSs(Long.parseLong(data.sys.getSunset())));
			lbSunrise.setText("Sunrise : " + data.convertSecondsToHMmSs(Long.parseLong(data.sys.getSunrise())));
			lbMain_temp.setText("Temperature now : " + data.main.getTemp() + " °C");
			lbMain_temp_min.setText("Minimum temperature : " + data.main.getTemp_min() + " °C");
			lbMain_temp_max.setText("Maximim temperature : " + data.main.getTemp_max() + " °C");
			lbWind_speed.setText("Wind speed : " + String.format("%.2f  km/h", Double.parseDouble(data.wind.getSpeed()) * 3.6));
			mainPane.setLeft(new ImageView(new Image("http://openweathermap.org/img/wn/" + data.weather[0].getIcon() + "@2x.png")));

			
			
			
			
		
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void updateImperial(String Code) {
		Gson parse = new Gson();
		try {
			Data data = parse.fromJson(new GetResponse(Code, "imperial").result, Data.class);
					
			lbWheater_main.setText("Main : " + data.weather[0].getMain());
			lbWeather_description.setText("Description : " + data.weather[0].getDescription());//lbMain_temp.setText("Temperature now : " + data.main.getTemp() + " K");
			lbMain_presure.setText("Pressure : " + data.main.getPresure() + " hPa");
			lbMain_humidity.setText("Humidity : " + data.main.getHumidity() + " %");
			lbMain_sea_level_presure.setText("Sea level presure : " + data.main.getSea_level() + " hPa");
			lbMain_ground_level_presure.setText("Ground level presure : " + data.main.getGrnd_level() + " hPa");
			lbWind_deg.setText("wind Direction : " + data.wind.getDeg());
			lbClouds_all.setText("Cloudness : " + data.clouds.getAll() + " %");
			lbRain_1h.setText("Rain in last 1h : " + data.getRain().getOne_h() + " mm");
			lbRain_3h.setText("Rain in last 3h : " + data.getRain().getThree_h() + " mm" );
			lbSnow_1h.setText("Snow in last 1h : " + data.getSnow().getOne_h() + " mm");
			lbSnow_3h.setText("Snow in last 3h : " + data.getSnow().getThree_h() + " mm");
			lbData.setText("Time : " + data.convertSecondsToHMmSs(Long.parseLong(data.dt)));
			lbName.setText("City : " + data.getName());
			lbSunset.setText("Sunset : " + data.convertSecondsToHMmSs(Long.parseLong(data.sys.getSunset())));
			lbSunrise.setText("Sunrise : " + data.convertSecondsToHMmSs(Long.parseLong(data.sys.getSunrise())));
			lbMain_temp.setText("Temperature now : " + data.main.getTemp() + " °F");
			lbMain_temp_min.setText("Minimum temperature : " + data.main.getTemp_min() + " °F");
			lbMain_temp_max.setText("Maximim temperature : " + data.main.getTemp_max() + " °F");
			lbWind_speed.setText("Wind speed : " + data.wind.getSpeed() + " miles/hour");
			mainPane.setLeft(new ImageView(new Image("http://openweathermap.org/img/wn/" + data.weather[0].getIcon() + "@2x.png")));

			
			
			
			
		
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
private String[] GetList(String searchElement) throws FileNotFoundException{
	//capitalize first char if isn't in search
	searchElement = searchElement.substring(0, 1).toUpperCase() + searchElement.substring(1);
		
		Scanner in = new Scanner(new File("city.list.txt"));
		
		ArrayList<String> temp = new ArrayList<>();
		while(in.hasNext()) {
			String element = in.nextLine();
			if(element.startsWith(searchElement))
				temp.add(element);
		}
		
		in.close();
		String[] list = new String[temp.size()];
		list = temp.toArray(list);
		return list;
		
	}

}