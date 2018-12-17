package ebookshop.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspaceName}")
	private String KEYSPACE;

	@Value("${spring.data.cassandra.contactPoints}")
	private String CONTACT_POINTS;

	@Override
	  protected String getContactPoints() {
	    return CONTACT_POINTS;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}


	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		final CreateKeyspaceSpecification specification =
				CreateKeyspaceSpecification.createKeyspace(KEYSPACE)
				.ifNotExists()
				.with(KeyspaceOption.DURABLE_WRITES, true)
				.withSimpleReplication();
		return Arrays.asList(specification);
	}

//	@Override
//	protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//		return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
//	}

	@Override
	protected String getKeyspaceName() {
		return KEYSPACE;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[]{"ebookshop.entity"};
	}
}


