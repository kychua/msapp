package ebookshop.dto;

public class Vote {

	private final String voterId;
	private final String candidateId;

	public Vote(String voterId, String candidateId) {
		this.voterId = voterId;
		this.candidateId = candidateId;
	}

	public String getVoterId() {
		return voterId;
	}

	public String getCandidateId() {
		return candidateId;
	}

}
