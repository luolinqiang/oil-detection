package com.oil.detection.web.controller;

import com.oil.detection.domain.Dictionary;
import com.oil.detection.service.DictionaryService;
import com.oil.detection.web.base.BaseControllor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-9-29
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SearchController extends BaseControllor {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/items-{type}")
    public String searchdItems(@PathVariable("type") String type, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("dic", getDictionary(type));
        return "product/items-list";
    }

    @RequestMapping(value = "/search-{type}")
    public String searchdiesel(@PathVariable("type") String type, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("dic", getDictionary(type));
        return "search/search";
    }


    @RequestMapping(value = "/zhaoche")
    public String findTruck(Model model, HttpServletRequest request) throws Exception {


        return "product/zhaoche";
    }

    private Dictionary getDictionary(String type) {
        Dictionary dictionary = new Dictionary();
        dictionary.setGroupCode("item_class");
        dictionary.setCode(type);
        return dictionaryService.getDictionary(dictionary);
    }
}
