import java.util.Map;
import java.util.TreeMap;


public class Institution {
	
	private String institution;
	private String ukpn;
	private TreeMap<String,Cohort> cohorts;
	
	
	public Institution(String ukrn, String inst)
	{
		this.institution = inst.trim();
		this.ukpn = ukrn.trim();
		this.cohorts = new TreeMap<String,Cohort>();
	}
	
	
	public String getInstitution() {
		return institution;
	}
	
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	public String getUkpn() {
		return ukpn;
	}
	
	public void setUkpn(String ukpn) {
		this.ukpn = ukpn;
	}
	
	public TreeMap<String,Cohort> getCohorts() {
		return cohorts;
	}
	
	public void setCohorts(TreeMap<String,Cohort> cohorts) {
		this.cohorts = cohorts;
	}
	
	public void addCohort(String key, Cohort cohort)
	{
		this.cohorts.put(key, cohort);
	}

}
