package ebookshop.entity;

import org.springframework.data.annotation.Id;

public class Poll {

	private @Id String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
}
