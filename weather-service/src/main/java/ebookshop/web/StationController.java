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

import ebookshop.dao.StationRepository;
import ebookshop.entity.Station;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/stations")
public class StationController {

	private StationRepository stationRepository;

	@Autowired
	public StationController(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

//	// READ
//	// ------------------------------------------------------------------------------
	@GetMapping()
	public Flux<Station> getAllStations() {
		return stationRepository.findAll();
	}

	// @PathVariable to handle dynamic id
	@GetMapping(path="/{id}")
	public Mono<Station> getStation(@PathVariable String id) {
		return stationRepository.findById(id);
	}

	// CREATE
	// ------------------------------------------------------------------------------
	@PostMapping
	public Mono<ResponseEntity<?>> addStation(@ModelAttribute Station station) {
		//  missing .location?
		return stationRepository.save(station)
				.map(s -> ResponseEntity.ok().body(s));
	}

	// UPDATE
	// ------------------------------------------------------------------------------
	@PutMapping("/{id}")
	public Mono<Station> updateStation(@PathVariable String id, @Valid @RequestBody Station stationDetails) {
		Mono<Station> station = stationRepository.findById(id)
				.doOnNext(s -> new Station(id,
						stationDetails.getName(),
						stationDetails.getLatitude(),
						stationDetails.getLongitude()))
				.doOnError(IllegalArgumentException::new)
				.flatMap(stationRepository::save);
		return station;
	}

	// DELETE
	// ------------------------------------------------------------------------------
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Object>> removeStation(@PathVariable String id) {
		return stationRepository.deleteById(id).then(Mono.just(ResponseEntity.ok().build()));
	}

}