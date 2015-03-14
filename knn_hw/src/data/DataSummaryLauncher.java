package data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class DataSummaryLauncher 
{
	public static void main(String[] args) {
		
		System.out.println(args[0]);
		
		File file = new File(args[0]);
		
		if(!file.exists()) {
			System.err.println("File does not exist");
			System.exit(0);
		} else {
			System.out.println("File exists");
		}
		PrintWriter print;
		try {
			GeneDataParser parser = new GeneDataParser(new Scanner(new FileReader(file)));
			parser.parseDataFile();
			List<GeneNode> nodeList = parser.getGeneList();
			GeneDataMeasurements measurements = new GeneDataMeasurements(nodeList);
	
			print = new PrintWriter(args[1]);
			
			print.println("Column Distinct Values:");
			print.println("Genes:\t\t\t\t" + measurements.geneIdCount());
			print.println("Essential:\t\t\t" + measurements.essentialCount());
			print.println("Class:\t\t\t\t" + measurements.classCount());
			print.println("Complex:\t\t\t" + measurements.complexCount());
			print.println("Motif:\t\t\t" + measurements.motifCount());
			print.println("Phenotype:\t\t\t\t" + measurements.phenotypeCount());
			print.println("Chromosomes:\t\t" + measurements.chromosomeCount());
			//print.println("Function:\t\t\t" + measurements.functionCount());
			//-print.println("Localization:\t\t\t" + measurements.localizationCount());
			print.println();
			
			print.println("Column Modes:");
			print.println("Genes:\t\t\t\t|" + measurements.getGeneMode()+ "|");
			print.println("Essential:\t\t\t|" + measurements.getEssentialMode()+ "|");
			print.println("Class:\t\t\t\t|" + measurements.getClassMode()+ "|");
			print.println("Complex:\t\t\t|" + measurements.getComplexMode()+ "|");
			print.println("Motif:\t\t\t|" + measurements.getMotifMode()+ "|");
			print.println("Phenotype:\t\t\t\t|" + measurements.getPhenotypeMode()+ "|");
			print.println("Chromosomes:\t\t|" + measurements.getChromosomeMode()+ "|");
			//print.println("Function:\t\t\t" + measurements.getFunctionMode());
			//print.println("Localization:\t\t\t|" + measurements.getLocalizationMode()+ "|");
			print.println();
			
			print.println("Column Distinct Value Counts:");
			printStringArray(measurements.getGeneCounts(), print);
			printStringArray(measurements.getEssentialCounts(), print);
			printStringArray(measurements.getClassCounts(), print);
			printStringArray(measurements.getComplexCounts(), print);
			printStringArray(measurements.getPhenotypeCounts(), print);
			printStringArray(measurements.getMotifCounts(), print);
			printStringArray(measurements.getChromosomeCounts(), print);
			//printStringArray(measurements.getFunctionCounts(), print);
			//printStringArray(measurements.getLocalizationCounts(), print);
			
			
			print.close();
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		
	}
	
	public static void printStringArray(String[] strings, PrintWriter print) {
		if(print == null || strings == null) {
			return;
		}
		
		for(String str : strings) {
			print.println(str);
		}
		print.println();
	}
}
