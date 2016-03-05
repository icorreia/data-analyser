package com.icorreia.cassandra;

import com.datastax.driver.core.Cluster;
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


    public CassandraConnector() {

    }

}
