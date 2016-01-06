package com.oil.detection.solr;

import com.google.common.collect.Lists;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.domain.Area;
import com.oil.detection.domain.page.QueryArea;
import com.oil.detection.exception.CustomRuntimeException;
import com.oil.detection.log.RunLog;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SolrAreaUtils {
    private static Logger logger = Logger.getLogger(SolrAreaUtils.class);

    private final static HttpSolrClient solrClient = SolrClientFactory
            .getInstance().getSolrClient();

    public static void addIndexs(List<Area> records) {
        int count = 0;
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        int size = records.size();
        for (int i = 0; i < size; i++) {
            SolrInputDocument item = new SolrInputDocument();
            Area area = records.get(i);
            logger.debug("Start adding solr index for " + area.toString());
            item.addField("id", area.getId());
            item.addField("pid", area.getPid());
            item.addField("areaLevel", area.getAreaLevel());
            item.addField("areaCode", area.getAreaCode());
            item.addField("areaName", area.getAreaName());
            item.addField("areaSort", area.getAreaSort());
            item.addField("longitude", area.getLongitude());
            item.addField("latitude", area.getLatitude());
            item.addField("createTime", area.getCreateTime());
            item.addField("updateTime", area.getUpdateTime());
            item.addField("state", area.getState());
            item.addField("remark", area.getRemark());
            docs.add(item);
            count++;
            if (count % 50 == 0 || count == size) {
                logger.debug("Begin commit " + count + " records");
                try {
                    solrClient.add(docs);
                    solrClient.commit();
                } catch (Exception e) {
                    RunLog.LOG.error(e.getMessage(), e);
                    throw new CustomRuntimeException(ReturnCode.ERROR_SOLR_SERVER);
                }
                docs.clear();
                logger.debug("End commit " + count + " records");
            }
        }
    }

    public static List<Area> searchIndexs(QueryArea queryArea) {
        if (queryArea == null || StringUtils.isBlank(queryArea.getAreaName())) {
            return new ArrayList<Area>(0);
        }

        String searchParam = String.format(" areaName:*%s*", queryArea.getAreaName());
        SolrQuery sQuery = new SolrQuery();

        sQuery.setQuery(searchParam);
        sQuery.setStart(queryArea.getStartIdx());
        sQuery.setRows(queryArea.getPageRows());
//        sQuery.addSort("sn", ORDER.desc);

        List<Area> areas = Lists.newArrayList();
        try {
            QueryResponse qrsp = solrClient.query(sQuery);
            SolrDocumentList results = qrsp.getResults();
            for (int i = 0; i < results.size(); ++i) {
                SolrDocument entries = results.get(i);
                Area area = new Area();
                String areaName = entries.getFieldValue("areaName") + "";
                String id = entries.getFieldValue("id") + "";
                area.setAreaName(areaName);
                area.setId(Long.parseLong(id));
                areas.add(area);
                System.out.println(entries);
//            System.out.println(entries);
            }
        } catch (Exception e) {
            RunLog.LOG.error(e.getMessage(), e);
            throw new CustomRuntimeException(ReturnCode.ERROR_SOLR_SERVER);
        }
        return areas;
    }

}

