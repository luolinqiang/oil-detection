package com.oil.detection.service;

import com.oil.detection.domain.Dictionary;
import com.oil.detection.domain.page.QueryDictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> listDictionary(Dictionary dictionary);

    int countDictionary(Dictionary dictionary);

        List<Dictionary> pageListDictionary(QueryDictionary dictionary);
    
    Dictionary getDictionary(Dictionary dictionary);

    Integer saveDictionary(Dictionary dictionary);

    void modifyDictionary(Dictionary dictionary);

    void removeDictionary(Dictionary dictionary);
}
