
public class Question {
	
	private int quid;
	private int[] answered;
	private int score;
	
	public Question(int quid, int score, int answered[])
	{
		this.quid = quid;
		this.score = score;
		this.answered = answered; 
	}
	
	public int getQuid() {
		return quid;
	}
	public void setQuid(int quid) {
		this.quid = quid;
	}
	public int[] getAnswered() {
		return answered;
	}
	public void setAnswered(int[] answered) {
		this.answered = answered;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
