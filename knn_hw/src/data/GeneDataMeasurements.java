package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class GeneDataMeasurements {
	
	private static final int NUM_CHROMOSOMES  = 18; // all chromosomes + 'null' value
	
	private List<GeneNode> geneList;
	private HashMap<String, Integer> geneCountMap, essentialCountMap, classCountMap, complexCountMap; 
	private HashMap<String, Integer> motifCountMap, phenotypeCountMap;// functionCountMap;// localizationCountMap;
	private int[] chromosomeCounts;
	//private int essenentialCount;

	public GeneDataMeasurements(List<GeneNode> geneList) {
		this.geneList = geneList;
		
		buildCounts();
	}
	
	public void buildCounts()
	{
		buildGeneCountMap();
		buildEssentialCountMap();
		buildClassCountMap();
		buildComplexCountMap();
		buildMotifCountMap();
		buildPhenotypeCountMap();
		//buildFunctionCountMap();
		//buildLocalizationCountMap();
		countChromosomeOccurance();		
	}
	
	public void setGeneList(List<GeneNode> geneList) { 
		this.geneList = geneList;
		buildCounts();
	}
	
	// counting methods
	private void buildGeneCountMap() {
		geneCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = geneCountMap.get(node.getGeneId());
				if(nodeCount == null) {
					geneCountMap.put(node.getGeneId(), 1);
				} else {
					geneCountMap.put(node.getGeneId(), nodeCount+1);
				}
			}
		}
	}
	
	private void buildClassCountMap() {
		classCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = classCountMap.get(node.getGeneClass());
				if(nodeCount == null) {
					classCountMap.put(node.getGeneClass(), 1);
				} else {
					classCountMap.put(node.getGeneClass(), nodeCount+1);
				}
			}
		}
	}
	
	private void buildEssentialCountMap() {
		essentialCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = essentialCountMap.get(node.getGeneClass());
				if(nodeCount == null) {
					essentialCountMap.put(node.getEssential(), 1);
				} else {
					essentialCountMap.put(node.getEssential(), nodeCount+1);
				}
			}
		}
	}
	
	private void buildComplexCountMap() {
		complexCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = complexCountMap.get(node.getComplex());
				if(nodeCount == null) {
					complexCountMap.put(node.getComplex(), 1);
				} else {
					complexCountMap.put(node.getComplex(), nodeCount+1);
				}
			}
		}
	}
	
	private void buildMotifCountMap() {
		motifCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = motifCountMap.get(node.getComplex());
				if(nodeCount == null) {
					motifCountMap.put(node.getMotif(), 1);
				} else {
					motifCountMap.put(node.getMotif(), nodeCount+1);
				}
			}
		}
	}
	
	private void buildPhenotypeCountMap() {
		phenotypeCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = phenotypeCountMap.get(node.getPhenotype());
				if(nodeCount == null) {
					phenotypeCountMap.put(node.getPhenotype(), 1);
				} else {
					phenotypeCountMap.put(node.getPhenotype(), nodeCount+1);
				}
			}
		}
	}
	
	/*private void buildFunctionCountMap() {
		functionCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = phenotypeCountMap.get(node.getFunction());
				if(nodeCount == null) {
					functionCountMap.put(node.getFunction(), 1);
				} else {
					functionCountMap.put(node.getFunction(), nodeCount+1);
				}
			}
		}
	}
	
	
	private void buildLocalizationCountMap() {
		localizationCountMap = new HashMap<String, Integer>();
		if(geneList != null) {
			for(GeneNode node : geneList) {
				Integer nodeCount = phenotypeCountMap.get(node.getLocalization());
				if(!phenotypeCountMap.containsKey(node.getLocalization())) {
					phenotypeCountMap.put(node.getLocalization(), 1);
				} else {
					phenotypeCountMap.put(node.getLocalization(), nodeCount+1);
				}
			}
		}
	}	*/
	
	private void countChromosomeOccurance() {
		chromosomeCounts = new int[NUM_CHROMOSOMES];
		if(geneList != null) {
			for(GeneNode node : geneList) {
				try {
					chromosomeCounts[node.getChromosome()]++;
				} catch(Exception e) {
					System.out.println("Strange chromosome value");
				}
			}
		}
	}	
	
	// get maps
	public HashMap<String, Integer> getGeneIdMap() { return geneCountMap; }
	public HashMap<String, Integer> getEssentialMap() { return essentialCountMap; }
	public HashMap<String, Integer> getClassMap() { return classCountMap; }
	public HashMap<String, Integer> getComplexMap() { return complexCountMap; }
	public HashMap<String, Integer> getPhenotypeMap() { return phenotypeCountMap; }
	public HashMap<String, Integer> getMotifMap() { return motifCountMap; }
	//public HashMap<String, Integer> getLocalizationMap() { return localizationCountMap; }
	
	public int[] getChromosomeCount() { return chromosomeCounts; }
	
	
	// get distinct value count
	public int geneIdCount()  { return geneCountMap.containsKey("?")  ? geneCountMap.size()-1 : geneCountMap.size(); }
	
	public int essentialCount() { return essentialCountMap.containsKey("?")  ? essentialCountMap.size()-1 : essentialCountMap.size(); }
	
	public int classCount() { return classCountMap.containsKey("?")  ? classCountMap.size()-1 : classCountMap.size(); }
	
	public int complexCount() { return complexCountMap.containsKey("?")  ? complexCountMap.size()-1 : complexCountMap.size(); }
	
	public int phenotypeCount() { return phenotypeCountMap.containsKey("?")  ? phenotypeCountMap.size()-1 : phenotypeCountMap.size(); }
	
	public int motifCount() { return motifCountMap.containsKey("?")  ? motifCountMap.size()-1 : motifCountMap.size(); }
	
	public int chromosomeCount() {
		int referencedChromosomes = 0;
		for(int i : chromosomeCounts) {
			if(i > 0) {
				referencedChromosomes++;
			}
		}
		return referencedChromosomes;
	}
	
	// modes
	public String getGeneMode() { return getMostCommonMapElement(geneCountMap); }
	public String getEssentialMode() { return getMostCommonMapElement(essentialCountMap); }
	public String getClassMode() { return getMostCommonMapElement(classCountMap); }
	public String getComplexMode() { return getMostCommonMapElement(complexCountMap); }
	public String getPhenotypeMode() { return getMostCommonMapElement(phenotypeCountMap); }
	public String getMotifMode() { return getMostCommonMapElement(motifCountMap); }
	
	
	private String getMostCommonMapElement(HashMap<String, Integer> map) {
		if(map == null) { return ""; }
		
		Set<String> strs = map.keySet();
		
		int currNum, max = 0;
		String currMode = "";
		
		for(String str : strs) {
			currNum = map.get(str);
			
			if(currNum > max && !str.equals("?")) {
				max = currNum;
				currMode = str;
			}
		}
		
		return currMode;
	}
	
	public int getChromosomeMode() {
		int mode = 0, max = 0;
		for(int i = 0; i < chromosomeCounts.length; i++) {
			if(chromosomeCounts[i] > max) {
				max = chromosomeCounts[i];
				mode = i;
			}
		}
		return mode;
	}
	
	// sorted count by distinct values
	public String[] getGeneCounts() {
		String[] counts = getSortedCountsFromMap(geneCountMap);
		counts[0] = "GeneID" + counts[0];
		
		return counts;
	}
	
	public String[] getEssentialCounts() {
		String[] counts = getSortedCountsFromMap(essentialCountMap);
		counts[0] = "Essential" + counts[0];
		
		return counts;
	}
	
	public String[] getClassCounts() {
		String[] counts = getSortedCountsFromMap(classCountMap);
		counts[0] = "Class" + counts[0];
		
		return counts;
	}
	
	public String[] getComplexCounts() {
		String[] counts = getSortedCountsFromMap(complexCountMap);
		counts[0] = "Complex" + counts[0];
		
		return counts;
	}
	
	public String[] getPhenotypeCounts() {
		String[] counts = getSortedCountsFromMap(phenotypeCountMap);
		counts[0] = "Phenotype" + counts[0];
		
		return counts;
	}
	
	public String[] getMotifCounts() {
		String[] counts = getSortedCountsFromMap(motifCountMap);
		counts[0] = "Motif" + counts[0];
		
		return counts;
	}
	
	private String[] getSortedCountsFromMap(HashMap<String, Integer> map) {
		
		System.out.println("Before get sorted counts array");
		@SuppressWarnings("unchecked")
		Entry<String, Integer>[] data = (Entry<String, Integer>[]) map.entrySet().toArray(new Map.Entry[map.size()]);
		System.out.println("after get sorted counts array");
		Entry<String, Integer> tempEntry;
			
		// Data set is small so a slow sort should be acceptable
		for(int i = 0; i < data.length-1; i++) {
			for(int j = 0; j < data.length-1; j++) {
				if(data[j].getValue() < data[j+1].getValue()) {
					tempEntry = data[j];
					data[j] = data[j+1];
					data[j+1] = tempEntry;
				}
			}
		}
			
		// first string to be a title set in calling method
		// 2nd is just a seperator
		String[] distinctValCounts = new String[data.length+2];
		distinctValCounts[0] = "\t\t\tCount";
		distinctValCounts[1] = "-------------------------------------------------";
		for(int i = 2; i < data.length; i++) {
			distinctValCounts[i] = String.format("%s\t\t\t\t%s",data[i].getKey(), data[i].getValue()); 
		}
		
		return distinctValCounts;	
	}
	
	public String[] getChromosomeCounts() {
		String[] counts = new String[chromosomeCounts.length+2];
		
		counts[0] = "\t\t\tCount";
		counts[1] = "-------------------------------------------------";
		for(int i = 0; i < chromosomeCounts.length; i++) {
			counts[i+2] = String.format("%d\t\t\t\t%d", i, chromosomeCounts[i]); 
		}
		
		return counts;
	}
}
