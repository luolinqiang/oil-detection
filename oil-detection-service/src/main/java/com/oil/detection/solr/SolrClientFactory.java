package com.oil.detection.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrClientFactory {
    private static HttpSolrClient solrClient = null;
    private static SolrClientFactory solrClientFactory = null;
    private static String solrHost = "http://123.57.236.102:8983/solr/area2";
    private static int solrClientTimeout = 20000;
    private static int solrConnectTimeout = 5000;
    private static int maxConnectionsPerHost = 100;
    private static int maxTotalConnection = 100;

    private SolrClientFactory() {
    }

    public synchronized HttpSolrClient getSolrClient() {
        if (solrClient == null) {
            solrClient = new HttpSolrClient(solrHost);

            solrClient.setSoTimeout(solrClientTimeout);  // socket read timeout
            solrClient.setConnectionTimeout(solrConnectTimeout);
            solrClient.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
            solrClient.setMaxTotalConnections(maxTotalConnection);
            solrClient.setFollowRedirects(false);  // defaults to false
            //allowCompression defaults to false.
            //Server side must support gzip or deflate for this to have any effect.
            solrClient.setAllowCompression(true);
        }
        return solrClient;
    }

    public static synchronized SolrClientFactory getInstance() {
        if (solrClientFactory == null) {
            solrClientFactory = new SolrClientFactory();
        }
        return solrClientFactory;
    }
}

