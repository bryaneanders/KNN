package data;

import java.io.PrintWriter;
import java.util.List;

public class DataCleaner {

	private List<GeneNode> genes;
	private PrintWriter print;
	
	private String essentialMode, classMode, complexMode, phenotypeMode, motifMode;
	private int chromosomeMode;
	
	public DataCleaner(List<GeneNode> genes, PrintWriter print) {
		this.genes = genes;
		this.print = print;
		
		calcModes();
	}
	
	public DataCleaner(List<GeneNode> genes) {
		this(genes, null);
	}
	
	public void calcModes() {
		GeneDataMeasurements measurements = new GeneDataMeasurements(genes);
		
		essentialMode = measurements.getEssentialMode();
		classMode = measurements.getClassMode();
		complexMode = measurements.getComplexMode();
		phenotypeMode = measurements.getPhenotypeMode();
		motifMode = measurements.getMotifMode();
		chromosomeMode = measurements.getChromosomeMode();
	}
	
	public void replaceMissingValues() {
		
		for(GeneNode node : genes) {			
			if(node.getEssential().equals("?")) {
				node.setEssential(essentialMode);
			} 
			if(node.getGeneClass().equals("?")) {
				node.setGeneClass(classMode);
			} 
			if(node.getComplex().equals("?")) {
				node.setComplex(complexMode);
			} 
			if(node.getPhenotype().equals("?")) {
				node.setPhenotype(phenotypeMode);
			} 
			if(node.getMotif().equals("?")) {
				node.setMotif(motifMode);
			} 
			if(node.getChromosome() == 0) {
				node.setChromosome(chromosomeMode);
			}
		}
		
		if(print != null) {
			writeNewFile();
		}
	}
	
	private void writeNewFile() {
		try{
			for(GeneNode node : genes) {
				print.println(node.toString());
			}
		} catch(Exception e) {
			System.err.println(e.toString());
		}
	}
	
	public void closePrintWriter() { 
		
		if(print != null) {
			try{
				print.close();
			} catch(Exception e) {
				System.err.println(e.toString());
			}
		}
	}
	
	public List<GeneNode> getGenes() { return genes; }
}
