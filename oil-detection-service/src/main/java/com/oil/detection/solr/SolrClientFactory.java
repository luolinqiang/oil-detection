package com.oil.detection.solr;

import com.oil.detection.util.PropertyUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrClientFactory {
    private static HttpSolrClient solrClient = null;
    private static SolrClientFactory solrClientFactory = null;
    private static String solrHost = PropertyUtil.getString("solr.host");
    private static int solrClientTimeout = PropertyUtil.getIntValue("solr.client.timeout");
    private static int solrConnectTimeout = PropertyUtil.getIntValue("solr.connect.timeout");
    private static int maxConnectionsPerHost = PropertyUtil.getIntValue("solr.max.connect.perhost");
    private static int maxTotalConnection = PropertyUtil.getIntValue("solr.max.total.connect");

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

