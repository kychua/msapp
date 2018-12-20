package ebookshop.dao;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import ebookshop.entity.Voter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


// Reactive Repositories are not supported by Redis! https://jira.spring.io/browse/DATAREDIS-831
@Repository
public class VoterRepository {
	@Autowired
	private ReactiveRedisTemplate<String, Voter> reactiveVoterRedisTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(VoterRepository.class);

	// CANDIDATES, voterId, {name, email}
	private ReactiveHashOperations<String, String, Voter> hashOps;

	private final String KEY = "voters";

	@PostConstruct
	private void init() {
		hashOps = reactiveVoterRedisTemplate.opsForHash();
	}

	public Mono<Voter> findById(String id) {
		return hashOps.get(KEY, id); // value or null???
	}

	public Flux<Voter> findAll() {
		return hashOps.entries(KEY).map(m -> m.getValue());
	}

	public Mono<Boolean> save(Voter voter) {
		LOGGER.info("Voter" + voter.getId() + " " + voter.getName() + " " + voter.getEmail());

		if (voter.getId() == null || voter.getId().isEmpty()) {
			voter.setId(UUID.randomUUID().toString());
		}
		return hashOps.put(KEY, voter.getId(), voter);
	}

	public Mono<Long> deleteById(String id) {
		return hashOps.remove(KEY, id); // number of fields removed
	}



}
