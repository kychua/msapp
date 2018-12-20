package ebookshop.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls")
public class PollController {

//	private PollRepository pollRepository;
//
//	@Autowired
//	public PollController(PollRepository pollRepository) {
//		this.pollRepository = pollRepository;
//	}
//
////	// READ
////	// ------------------------------------------------------------------------------
//	@GetMapping()
//	public Flux<Poll> getAllPolls() {
//		return pollRepository.findAll();
//	}
//
//	// returns a poll:
//	// - name
//	// - {candidate (object), numVotes}
//	@GetMapping(path="/{id}")
//	public Mono<Poll> getPoll(@PathVariable String id) {
//		return pollRepository.findById(id);
//
//	}
//
//	// CREATE
//	// ------------------------------------------------------------------------------
//	//  creates a poll: name, 0 candidates
//	@PostMapping
//	public Mono<ResponseEntity<?>> addPoll(@ModelAttribute Poll poll) {
//		return pollRepository.save(poll)
//				.map(s -> ResponseEntity.ok().body(s));
//	}
//
//	@PutMapping("/{id}")
//	public Mono<ResponseEntity<?>> submitVote(@Valid @RequestBody Vote vote) {
//		return pollRepository.submitVote(id, vote.getCandidateId(), vote.getVoteId());
//	}
//
//	// UPDATE
//	// ------------------------------------------------------------------------------
//	// updates a poll name
//	@PutMapping("/{id}")
//	public Mono<Poll> updatePoll(@PathVariable String id, @Valid @RequestBody Poll pollDetails) {
//		Mono<Poll> poll = pollRepository.findById(id)
//				.doOnNext(s -> new Poll(id,
//						pollDetails.getName(),
//						pollDetails.getLatitude(),
//						pollDetails.getLongitude()))
//				.doOnError(IllegalArgumentException::new)
//				.flatMap(pollRepository::save);
//		return poll;
//	}
//
//	// DELETE
//	// ------------------------------------------------------------------------------
//	// deletes a poll
//	@DeleteMapping("/{id}")
//	public Mono<ResponseEntity<Object>> removePoll(@PathVariable String id) {
//		return pollRepository.deleteById(id).then(Mono.just(ResponseEntity.ok().build()));
//	}

}