package ebookshop.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("candidates")
public class Candidate {

	private @Id String id;
	private String name;
	private String description;

	private static final Logger LOGGER = LoggerFactory.getLogger(Candidate.class);

	public Candidate() {}

	public Candidate(String id, String name, String description) {
		LOGGER.info("Candidate" + id + " " + name + " " + description);

		this.id = id;
		this.name = name;
		this.description = description;
	}

//	public Candidate(String name, String description) {
//		this.id = UUID.randomUUID().toString();
//		this.name = name;
//		this.description = description;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
