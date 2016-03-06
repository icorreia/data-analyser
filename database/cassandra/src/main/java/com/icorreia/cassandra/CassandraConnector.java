package com.icorreia.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

/**
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class CassandraConnector {

    /** Cassandra Cluster. */
    private Cluster cluster;

    /** Cassandra Session. */
    private Session session;


    public CassandraConnector(final String node, final int port) {
        this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();
        final Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for (final Host host : metadata.getAllHosts())
        {
            System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }
        session = cluster.connect();
    }

    public void cleanUp() {
        session.close();
    }

}
