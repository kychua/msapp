package ebookshop.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Station {

	@PrimaryKey
	private String id;

	private String name;
	private float latitude;
	private float longitude;

	public Station(String id, String name, float latitude, float longitude) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	 @Override
	  public String toString() {
	    return String.format("{ @type = %1$s, id = %2$s, name = %3$s, latitude = %4$d, longitude = %5$d }",
	      getClass().getName(), getId(), getName(), getLatitude(), getLongitude());
	  }
}
