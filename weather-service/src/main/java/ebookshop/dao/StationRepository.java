package ebookshop.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ebookshop.entity.Station;

@Repository
public interface StationRepository extends ReactiveCrudRepository<Station, String> {

}
