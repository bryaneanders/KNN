package knn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

import data.DataCleaner;
import data.GeneDataParser;
import data.GeneNode;

public class KNNTest {
	
	private static final int DEFAULT_K = 10;
	private static final String OUTPUT_FILE_NAME = "localization_predictions.txt";
	private static HashMap<String, String> keysMap;

	public static void main(String[] args) {
		try{
			String trainingSetPath = args[0];
			String testSetPath = args[1];
			
			File trainingSetFile = new File(trainingSetPath);
			File testSetFile = new File(testSetPath);
			
			if(!trainingSetFile.exists()) {
				System.out.println("Training set file does not exist");
				System.exit(0);
			} else if(!testSetFile.exists()) {
				System.out.println("Test set file does not exist");
				System.exit(0);
			}
			
			predictLocalizations(trainingSetFile, testSetFile);
			
		} catch(Exception e) {
			System.err.println("Error in main:"+e.toString()+"\n\n");
			System.out.println("Usage: java KNNTest training_set_File test_set_file");
		}
	}
	
	public static void predictLocalizations(File trainingSetFile, File testSetFile) throws FileNotFoundException
	{
		
		// parse the files to build the training and test sets
		GeneDataParser trainingSetParser =  new GeneDataParser(new Scanner(new FileReader(trainingSetFile)));
		GeneDataParser testSetParser = new GeneDataParser(new Scanner(new FileReader(testSetFile)));
		trainingSetParser.parseDataFile();
		testSetParser.parseDataFile();
			
		List<GeneNode> trainingSet  = trainingSetParser.getGeneList();
		List<GeneNode> testSet = testSetParser.getGeneList(); 
					
		trainingSetParser.closeScanner();
		testSetParser.closeScanner();			
			
		// clean the training set data
		DataCleaner cleaner = new DataCleaner(trainingSet);
		cleaner.replaceMissingValues();
		trainingSet = cleaner.getGenes();
					
		// predict the localization for each element in the test case
		GeneNode node;
		KNearestNeighbor knn = new KNearestNeighbor(trainingSet, DEFAULT_K);
		for(int i = 0; i < testSet.size(); i++) {
			node = testSet.get(i);
			node.setLocalization(knn.predictLocalization(node));
		}
		
		// print the accuracy of the results
		PrintWriter print = new PrintWriter(OUTPUT_FILE_NAME);
		mapKeys(new Scanner(new FileReader(".//hw2_files//keys.txt")));
		checkAccuracy(testSet, print);
		
		// write to the output file
		print.println("GeneID\t\t\tLocalization");
		print.println("----------------------------------");
		
		for(GeneNode gene : testSet) {
			print.println(gene.getGeneId() + "\t\t|\t" + gene.getLocalization());
		}
				
		print.close();
	}
	
	public static void checkAccuracy(List<GeneNode> testSet, PrintWriter print) {
		
		int correct = 0;
		int total = testSet.size();
		
		for(GeneNode node : testSet) {			
			if(!keysMap.containsKey(node.getGeneId())) {
				continue;
			}
			
			// count the number of matches
			String testLocalization = keysMap.get(node.getGeneId());
			if(testLocalization.equalsIgnoreCase(node.getLocalization())) {
				correct++;
			}
		}
		
		double percentCorrect = (double) correct / (double) total;
		
		print.println("Total rows tested: " + total);
		print.println("Correctly labeled localizations: " + correct);
		print.println("Percentage labeled correctly: " + percentCorrect +"\n\n");
	}
	
	public static void mapKeys(Scanner keysFile) {
		keysMap = new HashMap<String, String>();
		
		String[] tokens;
		while(keysFile.hasNextLine()) {
			tokens = keysFile.nextLine().split(",");
			System.out.println(tokens[0] + " " + tokens[1]);
			keysMap.put(tokens[0], tokens[1]);
		}
	}
}
