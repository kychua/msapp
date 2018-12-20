package ebookshop.dao;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveSetOperations;
import org.springframework.data.redis.core.ReactiveZSetOperations;

import ebookshop.entity.Candidate;
import ebookshop.entity.Poll;
import ebookshop.entity.Voter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VoteRepository {

	@Autowired
	private ReactiveRedisTemplate<String, String> redisTemplate;

	@Autowired
	private ReactiveRedisTemplate<String, String> reactiveCandidateRedisTemplate;

	@Autowired
	private PollRepository pollRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateRepository.class);

	// VOTES:voterId {pollId}
	// VOTES:voterId:pollId {candidateId} // view voter's votes
	private ReactiveSetOperations<String, String> setOps;
	private ReactiveZSetOperations<String, String> zSetOps;

	@PostConstruct
	private void init() {
		setOps = reactiveCandidateRedisTemplate.opsForSet();
		zSetOps = reactiveCandidateRedisTemplate.opsForZSet();
	}

	public Flux<Long> submitVote(String voterId, String pollId, String candidateId) {
		// add poll to voter's votes
		// add candidate to voter's poll votes
//		return Flux.concat(setOps.add("votes:" + voterId + ":polls:" + pollId, candidateId),
//				setOps.add("votes:" + voterId + ":polls", pollId)),
//				zSetOps.incrementScore("poll:" + pollId, candidateId, 1);
		// TODO
		return null;
	}

	public Flux<Poll> findVotedPolls(String voterId) {
		return setOps.members("votes:" + voterId + ":polls")
				.flatMap(pollId -> pollRepository.findById(pollId));
	}

	public Flux<Candidate> findVotesForPoll(String voterId, String pollId) {
		return setOps.members("votes:" + voterId + ":polls:"+ pollId)
				.flatMap(candidateId -> candidateRepository.findById(candidateId));
	}

	public Mono<Voter> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Flux<Voter> save(Voter voter) {
		// TODO Auto-generated method stub
		return null;
	}
}
