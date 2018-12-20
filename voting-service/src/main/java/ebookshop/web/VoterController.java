package ebookshop.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebookshop.dao.VoterRepository;
import ebookshop.entity.Voter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

	private VoterRepository voterRepository;

//	private VoteRepository voteRepository;
//
//	private CandidateRepository candidateRepository;

//	@Autowired
//	public VoterController(VoterRepository voterRepository, VoteRepository voteRepository,
//			CandidateRepository candidateRepository) {
//		this.voterRepository = voterRepository;
//		this.voteRepository = voteRepository;
//		this.candidateRepository = candidateRepository;
//	}

	@Autowired
	public VoterController(VoterRepository voterRepository) {
		this.voterRepository = voterRepository;
	}

	// READ
	// ------------------------------------------------------------------------------
	// Get all voters (name, email)
	@GetMapping()
	public Flux<Voter> getAllVoters() {
		return voterRepository.findAll();
	}

	// returns a voter (name, email, votes)
	@GetMapping(path="/{id}")
	public Mono<Voter> getVoter(@PathVariable String id) {
		return voterRepository.findById(id);
	}

//	@GetMapping(path="/{voterId}/votes/{pollId}")
//	public Flux<Candidate> getVotes(@PathVariable String voterId, @PathVariable String pollId) {
//		return voteRepository.getVotes(voterId, pollId).flatMap(c -> candidateRepository.findById());
//	}
	// CREATE
	// ------------------------------------------------------------------------------
	//  creates a voter: name, 0 candidates
	@PostMapping
	public Mono<ResponseEntity<?>> addVoter(@ModelAttribute Voter voter) {
		return voterRepository.save(voter)
				.map(s -> ResponseEntity.ok().body(s));
	}

	// UPDATE
	// ------------------------------------------------------------------------------
	// updates a voter name, email
	@PutMapping("/{id}")
	public Mono<Boolean> updateVoter(@PathVariable String id, @Valid @RequestBody Voter voterDetails) {
		Mono<Boolean> voter = voterRepository.findById(id)
				.map(s -> new Voter(id,
						voterDetails.getName(),
						voterDetails.getEmail()))
				.doOnError(IllegalArgumentException::new)
				.flatMap(voterRepository::save);
		return voter;
	}

	// DELETE
	// ------------------------------------------------------------------------------
	// deletes a voter
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Object>> removeVoter(@PathVariable String id) {
		return voterRepository.deleteById(id).then(Mono.just(ResponseEntity.ok().build()));
	}

}