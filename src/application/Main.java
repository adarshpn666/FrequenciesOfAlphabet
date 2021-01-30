package application;
	



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



public class Main extends Application{
	
	private final Scanner scanner = new Scanner(System.in);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Frequencies of alphabet in text");
			
			final CategoryAxis xAxis = new CategoryAxis();
			final NumberAxis yAxis = new NumberAxis();
			BarChart<String,Number> bChart = new BarChart<String, Number>(xAxis, yAxis);
			bChart.setTitle("Frequencies of Alphabet");
			xAxis.setLabel("Alphabet");
			yAxis.setLabel("Frequencies");
			
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			
			String textString =  scanner.nextLine();
			
			Map<String, Number> newMap = newdata(textString);
			
			for (String s:newMap.keySet()) {
				
				series.getData().add(new XYChart.Data<String, Number>(s, newMap.get(s)));
			}
			
			
			Scene scene = new Scene(bChart,800,800);
			bChart.getData().add(series);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {	
		launch(args);
	}
	
	
	public static  Map<String, Number> newdata(String string){
		Map<String, Number> input = new HashMap<String, Number>();
		char[] charValue = string.toCharArray();
		
		
		Set<Character> charSet = new HashSet<Character>();
		for (int i = 0; i < charValue.length; i++) {
			charSet.add(charValue[i]);
		}
		
		char[] punctuations = {
				' ','.','?','/',',','[',']','{','}',
				'"','\'','(',')','-','_',':',';','!','’'
		};
		
		ArrayList<Character> arrayList = new ArrayList<Character>();
		for (int i = 0; i < punctuations.length; i++) {
			arrayList.add(punctuations[i]);
		}
		
		for (int i = 0; i < string.length(); i++) {
			if(charSet.contains(string.charAt(i))&& !arrayList.contains(string.charAt(i))) {
				if(!input.containsKey(String.valueOf(string.charAt(i)))) {
					input.put(String.valueOf(string.charAt(i)), 1);
				}else {
				 int value = (Integer) input.get(String.valueOf(string.charAt(i)));
					input.replace(String.valueOf(string.charAt(i)), value+1 );
				}
			}
		}
		
		
		return input;
	}
}





