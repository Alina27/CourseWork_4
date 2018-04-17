import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.print.attribute.standard.PresentationDirection;

import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class Tester {
	
	public static Comparator<PredictedClass> distCompare = new Comparator<PredictedClass>() {
		@Override
		public int compare(PredictedClass predc1, PredictedClass predc2) {
			if(predc1.distance < predc2.distance)
				return -1;
			else if(predc1.distance == predc2.distance)
				return 0;
			else 
				return 1;
		}         
	};
	
	private static ArrayList<PredictedClass> sortByDistance(ArrayList<PredictedClass> pc){
		Collections.sort(pc,distCompare);
		return pc;
	  };
		
   public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("trainingData.arff"));
		ArffReader arff = new ArffReader(reader);
		Instances data = arff.getData();
		data.setClassIndex(data.numAttributes() - 1);
		
		ArrayList<Publication> publication = new ArrayList<Publication>();
		ArrayList<PredictedClass> prediction = new ArrayList<PredictedClass>();
		
		for(int i = 0; i < data.sumOfWeights(); i++){
			publication.add(new Publication(data.instance(i)));
		}
	
		int k = 4;
	    double[] predict = {0.85,3,0,0};

	    for(Publication pub: publication) {
			 double distance = 0.0;
			 int N = pub.values.numClasses();
			   for(int i = 0; i < N; i++)
			     distance += Math.pow(pub.values.value(i) - predict[i], 2);
			   distance = Math.sqrt(distance);
			   prediction.add(new PredictedClass(pub.values.stringValue(4), distance));
			}
	      sortByDistance(prediction);
	        String[] resByK = new String[k];
	        
	        for(int i = 0; i < resByK.length; i++){
	        	resByK[i] = prediction.get(i).title;
	        }
	        
	        K_NearestNeighbours kNeigh = new K_NearestNeighbours();
	        String predictedClass = kNeigh.getMostCommon(resByK);
	        System.out.println("Predicted class is: " + predictedClass);
	   }	
  }



//double[] predict = {273,2,0,1}; 
		//double[] predict = {6,2,0,1}; //230,4,0,1;450,2,0,1;12,1,0,1
		//double[] predict = {1,1,0,0};
		