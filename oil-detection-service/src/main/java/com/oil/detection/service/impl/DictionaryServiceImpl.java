package com.oil.detection.service.impl;

import com.oil.detection.dao.DictionaryMapper;
import com.oil.detection.domain.Dictionary;
import com.oil.detection.domain.page.QueryDictionary;
import com.oil.detection.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> listDictionary(Dictionary dictionary) {
        return dictionaryMapper.listDictionary(dictionary);
    }

    @Override
    public int countDictionary(Dictionary dictionary) {
        return dictionaryMapper.countDictionary(dictionary);
    }

        @Override
    public List<Dictionary> pageListDictionary(QueryDictionary dictionary) {
        return dictionaryMapper.pageListDictionary(dictionary);
    }
    
    @Override
    public Dictionary getDictionary(Dictionary dictionary) {
        return dictionaryMapper.getDictionary(dictionary);
    }

    @Override
    public Integer saveDictionary(Dictionary dictionary) {
        return dictionaryMapper.saveDictionary(dictionary);
    }

    @Override
    public void modifyDictionary(Dictionary dictionary) {
        dictionaryMapper.modifyDictionary(dictionary);
    }

    @Override
    public void removeDictionary(Dictionary dictionary) {
        dictionaryMapper.removeDictionary(dictionary);
    }
}
