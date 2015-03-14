package data;

public class GeneNode 
{
	private String geneId, geneClass, complex, phenotype, motif, function, localization, essential;
	//private boolean essential;
	private int chromosome;
	
	public GeneNode(String geneId) {
		this.geneId = geneId;
	}
	
	public void setGeneId(String geneId) { this.geneId = geneId; }
	public String getGeneId() { return geneId; }
	
	public void setGeneClass(String geneClass) { this.geneClass = geneClass; }
	public String getGeneClass() { return geneClass; }
	
	public void setComplex(String complex) { this.complex = complex; }
	public String getComplex() { return complex; }
	
	public void setPhenotype(String phenotype) { this.phenotype = phenotype; }
	public String getPhenotype() { return phenotype; }
	
	public void setMotif(String motif) { this.motif = motif; }
	public String getMotif() { return motif; }
	
	public void setFunction(String function) { this.function = function; }
	public String getFunction() { return function; }
	
	public void setLocalization(String localization) { this.localization = localization; }
	public String getLocalization() { return localization; }
	
	public void setEssential(String essential) { this.essential = essential; }
	public String getEssential() { return essential; }

	public void setChromosome(int chromosome) { this.chromosome = chromosome; }
	public int getChromosome() { return chromosome; }
	
	public String toString() {
		String resp = geneId + ",";
		resp.concat(essential + ",");
		resp.concat(geneClass + ",");
		resp.concat(complex + ",");
		resp.concat(phenotype + ",");
		resp.concat(motif + ",");
		resp.concat(chromosome + ",");
		resp.concat(function + ",");
		resp.concat(localization + ",");

		
		return resp;
	}
}
