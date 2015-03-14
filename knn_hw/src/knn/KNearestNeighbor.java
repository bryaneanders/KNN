package knn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.GeneNode;


public class KNearestNeighbor 
{
	private List<GeneNode> trainingSet;
	private GeneNode testGene;
	private int k;
	private static final int DEFAULT_K = 10;
	
	public KNearestNeighbor(List<GeneNode> trainingSet,int k) {
		this.trainingSet = trainingSet;
		
		// make sure that k is not impossible
		if(k <= 0 || k > this.trainingSet.size()) {
			this.k = DEFAULT_K;
		} else {
			this.k = k;
		}
	}
	
	// find the localization of the testGene
	public String predictLocalization(GeneNode testGene) {
		this.testGene = testGene;
		
		// if the gene id of the testGene matches the id of any in the
		// test set return the localization of the test set gene
		for(GeneNode node : trainingSet) {
			if(node.getGeneId().equals(testGene.getGeneId())) {
				return node.getLocalization();
			}
		}
		
		// find the k nearest neighbors
		GeneNode[] nearest = getKNearest();
		
		// find majority vore from the knearest
		return kNearestLocalization(nearest);
	}
	
	// compare test gene to each gene in the testing set
	// and compute their distance
	public GeneNode[] getKNearest() {
		double[] distances = new double[trainingSet.size()];
		
		for(int i = 0; i < distances.length; i++) {
			distances[i] = calculateDistance(i);
		}
		
		GeneNode[] nodes = (GeneNode[]) trainingSet.toArray(new GeneNode[trainingSet.size()]);
		for(int i = 0; i < distances.length-1; i++) { 
			for(int j = 0; j < distances.length-1; j++) {
				if(distances[j]>distances[j+1]) {
					double tempDist = distances[j];
					GeneNode tempNode = nodes[j];
					distances[j] = distances[j+1];
					nodes[j] = nodes[j+1];
					distances[j+1] = tempDist;
					nodes[j+1] = tempNode;
				}
			}
		}
		
		// get the k nearest
		GeneNode[] knearest = new GeneNode[k];
		
		for(int i = 0; i < k; i++) {
			knearest[i] = nodes[i];
		}
		
		return knearest;
	}
	
	// calculate the distance
	public double calculateDistance(int i) {
		double distance = .6;	// 6 elements, excluding geneID, function and localization
		
		GeneNode node = trainingSet.get(i);
		
		// subtract from the distance for each  match
		// Do not test the geneID because if it matches its an automatic match
		// Do not test the function because it is withheld
		
		if(node.getEssential() == testGene.getEssential()) {
			distance -= .1;
		}
		
		if(node.getClass().equals(testGene.getClass())) {
			distance -= .1;
		}
		
		if(node.getComplex().equals(testGene.getComplex())) {
			distance -= .1;
		}
		
		if(node.getPhenotype().equals(testGene.getPhenotype())) {
			distance -= .1;
		}
		
		if(node.getMotif().equals(testGene.getMotif())) {
			distance -= .1;
		}
		
		if(node.getChromosome() == testGene.getChromosome()) {
			distance -= .1;
		}
		
		return distance;
	}
	
	
	// find majority vote from the k nearest
	public String kNearestLocalization(GeneNode[] nearestGenes) {
		HashMap<String, Integer> localizations = new HashMap<String, Integer>();
		
		// count up the occurances of localizations in the k nearest
		for(GeneNode node : nearestGenes) {
			String localization = node.getLocalization();
			if(localizations.containsKey(localization)) {
				int currCount = localizations.get(localization);
				localizations.put(localization, currCount++);
			} else {
				localizations.put(localization, 1);
			}
		}
		
		// find the localization with the highest count
		String maxLocalization = "";
		@SuppressWarnings("unchecked")
		Entry<String, Integer>[] nearestLocations = (Entry<String, Integer>[]) localizations.entrySet().toArray(new Map.Entry[localizations.size()]);
		
		int max = 0;
		
		for(int i = 0; i < nearestLocations.length; i++) {
			Entry<String, Integer> mapEntry = nearestLocations[i];
			
			if(mapEntry.getValue() > max) {
				maxLocalization = mapEntry.getKey();
				max = mapEntry.getValue();
			}
		}

		return maxLocalization;
	}
}
