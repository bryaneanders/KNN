package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneDataParser {
	
	private Scanner scan;
	private List<GeneNode> geneList;
	
	public GeneDataParser(Scanner scan) {
		this.scan = scan;
		geneList = new ArrayList<GeneNode>();
	}
	
	public List<GeneNode> getGeneList() { return geneList; }
	
	public void parseDataFile() {
		GeneNode node;
		String line;
		String[] sections;
		
		if(scan != null) {
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				
				// Credit for this regex to Bart Kiers from
				// http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
				// Indended to split on commas when not in quotes
				sections = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				
			//	if(sections.length != 9) { 
				//	System.out.println("sections.length = " + sections.length);
				//	System.out.println("GeneID: " + sections[0]);
				//	System.out.println("Essential: " + sections[1]);
				//	System.out.println("Class: " + sections[2]);
				//	System.out.println("Complex: " + sections[3]);
				//	System.out.println("Phenotype: " + sections[4]);
				//	System.out.println("Motif: " + sections[5]);
				//	System.out.println("Chromosome: " + sections[6]);
				//	System.out.println("Function: " + sections[7]);
				//	System.out.println("Localization: " + sections[8]);
					

			//		continue; 
			//	}
				
				//isEssential = sections[1].equalsIgnoreCase("essential") ? true : false;
				
				node = new GeneNode(sections[0]);
				node.setEssential(sections[1]);
				node.setGeneClass(sections[2]);
				node.setComplex(sections[3]);
				node.setPhenotype(sections[4]);
				node.setMotif(sections[5]);
				
				try {
					node.setChromosome(Integer.parseInt(sections[6]));
				} catch(NumberFormatException e) {
					node.setChromosome(0);
				}
				
				node.setFunction(sections[7]);
				
				if(sections[8].endsWith(".")) {
					sections[8] = sections[8].substring(0, sections[8].length()-1);
				}
				//System.out.println("Localization: " + sections[8]);
				node.setLocalization(sections[8]);
				
				
				
				geneList.add(node);
			}
		}
	}
	
	public void closeScanner() {
		if(scan != null) {
			scan.close();
		}
	}

}
