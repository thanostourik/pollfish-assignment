package com.tnt.logging.kafka.config;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.PlainTextAuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;

//@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.contact-points:placeholder}")
	private String contactPoints;

	@Value("${spring.data.cassandra.port:0000}")
	private int port;

	@Value("${spring.data.cassandra.username}")
	private String username;

	@Value("${spring.data.cassandra.password}")
	private String password;

	@Value("${spring.data.cassandra.keyspace-name:placeholder}")
	private String keySpace;

	@Value("${spring.data.cassandra.schema-action}")
	private String schemaAction;

	@Override
	protected String getContactPoints() {
		return contactPoints;
	}

	@Override
	protected int getPort() {
		return port;
	}

	@Override
	protected AuthProvider getAuthProvider() {
		return new PlainTextAuthProvider(username, password);
	}

	@Override
	protected String getKeyspaceName() {
		return keySpace;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.valueOf(schemaAction);
	}

	@Override
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = super.cluster();
		cluster.setJmxReportingEnabled(false);
		return cluster;
	}
}