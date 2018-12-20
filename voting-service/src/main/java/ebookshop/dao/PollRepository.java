package ebookshop.dao;

import ebookshop.entity.Poll;
import reactor.core.publisher.Mono;

public class PollRepository {

	public Mono<Poll> findById(String pollId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Autowired
//	private ReactiveRedisTemplate<String, String> redisTemplate;
//
//	@Autowired
//	private VoterRepository voterRepository;
//
//	public Mono<?> submitVote(Poll poll, Candidate candidate, Voter voter) {
//		Mono<?> result = redisTemplate.opsForZSet().incrementScore(poll.getId(), candidate.getId(), 1)
//				.flatMap(d -> voterRepository.saveVote(voter, candidate));
//		return result;
//	}
}
