package com.oil.detection.dao;

import com.oil.detection.domain.Dictionary;
import com.oil.detection.domain.page.QueryDictionary;

import java.util.List;

public interface DictionaryMapper {

    /**
     * 查询列表
     * @param dictionary
     * @return
     */
    List<Dictionary> listDictionary(Dictionary dictionary);

    /**
    * 查询总数
    * @param dictionary
    * @return
    */
    int countDictionary(Dictionary dictionary);

        /**
    * 分页列表
    * @param dictionary
    * @return
    */
    List<Dictionary> pageListDictionary(QueryDictionary dictionary);
    
    /**
     * 查询
     * @param dictionary
     * @return
     */
    Dictionary getDictionary(Dictionary dictionary);

    /**
     * 新增
     * @param dictionary
    * @author luolinqiang
    */
    Integer saveDictionary(Dictionary dictionary);

    /**
     * 修改
     * @param dictionary
     */
    void modifyDictionary(Dictionary dictionary);

    /**
     * 删除
     * @param dictionary
     */
    void removeDictionary(Dictionary dictionary);
}