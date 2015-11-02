import java.util.ArrayList;

public class Cohort {
	
	private String subject;
	private int sample;
	private int response;
	private ArrayList<Question> questions;
	
	public Cohort(String subject, int sample, int response)
	{
		this.subject = subject;
		this.sample = sample;
		this.response = response;
		this.questions = new ArrayList<Question>();
	}	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getSample() {
		return sample;
	}
	public void setSample(int sample) {
		this.sample = sample;
	}
	public int getResponse() {
		return response;
	}
	public void setResponse(int response) {
		this.response = response;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	public void addQuestions(Question question)
	{
		this.questions.add(question);
	}

}