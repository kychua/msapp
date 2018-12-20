package ebookshop.dao;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import ebookshop.entity.Candidate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


// Reactive Repositories are not supported by Redis! https://jira.spring.io/browse/DATAREDIS-831
@Repository
public class CandidateRepository {
	@Autowired
	private ReactiveRedisTemplate<String, Candidate> reactiveCandidateRedisTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateRepository.class);

	// CANDIDATES, candidateId, {name, email}
	private ReactiveHashOperations<String, String, Candidate> hashOps;

	private final String KEY = "candidates";

	@PostConstruct
	private void init() {
		hashOps = reactiveCandidateRedisTemplate.opsForHash();
	}

	public Mono<Candidate> findById(String id) {
		return hashOps.get(KEY, id); // value or null???
	}

	public Flux<Candidate> findAll() {
		return hashOps.entries(KEY).map(m -> m.getValue());
	}

	public Mono<Boolean> save(Candidate candidate) {
		LOGGER.info("Candidate" + candidate.getId() + " " + candidate.getName() + " " + candidate.getDescription());

		if (candidate.getId() == null || candidate.getId().isEmpty()) {
			candidate.setId(UUID.randomUUID().toString());
		}
		return hashOps.put(KEY, candidate.getId(), candidate);
	}

	public Mono<Long> deleteById(String id) {
		return hashOps.remove(KEY, id); // number of fields removed
	}



}
