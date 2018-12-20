package ebookshop.web;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebookshop.dao.CandidateRepository;
import ebookshop.entity.Candidate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

	private CandidateRepository candidateRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	public CandidateController(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleMyException(Exception  exception) {
		return ResponseEntity.badRequest().body(exception.toString());
	}


	//	// READ
	//	// ------------------------------------------------------------------------------
	@GetMapping()
	public Flux<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}

	@GetMapping(path="/{id}")
	public Mono<Candidate> getCandidate(@PathVariable String id) {
		return candidateRepository.findById(id);

	}

	// CREATE
	// ------------------------------------------------------------------------------
	//  creates a candidate: name, 0 candidates
	@PostMapping
	public Mono<ResponseEntity<?>> addCandidate(@ModelAttribute Candidate candidate) {
		//		LOGGER.info("Candidate" + candidate.getId() + " " + candidate.getName() + " " + candidate.getDescription());
		return candidateRepository.save(candidate) // if not exists???
				.map(s -> ResponseEntity.ok().body(s));
	}

	// UPDATE
	// ------------------------------------------------------------------------------
	// updates a candidate name
	@PutMapping("/{id}")
	public Mono<Boolean> updateCandidate(@PathVariable String id, @Valid @RequestBody Candidate candidateDetails) {
		LOGGER.info("Candidate" + candidateDetails.getId() + " " + candidateDetails.getName() + " " + candidateDetails.getDescription());
		Mono<Boolean> result = candidateRepository.findById(id)
				.map(s -> new Candidate(id, candidateDetails.getName(), candidateDetails.getDescription()))
				.doOnError(IllegalArgumentException::new)
				.flatMap(c -> candidateRepository.save(c));
		return result;
	}

	// DELETE
	// ------------------------------------------------------------------------------
	// deletes a candidate
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Object>> removeCandidate(@PathVariable String id) {
		return candidateRepository.deleteById(id).then(Mono.just(ResponseEntity.ok().build()));
	}

}