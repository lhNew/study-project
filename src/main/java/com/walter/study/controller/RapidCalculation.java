/**
 * Copyright (c) 2018-2020 四川健康久远科技有限公司 All Rights Reserved.
 * FileName: RapidCalculation
 *
 * @author: liucr
 * @date: 2022/7/9 14:18
 * @version: 1.0.0
 */
package com.walter.study.controller;

import com.walter.study.util.GenerateCalculationAuestions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Title: 
 * Description: 
 *
 * @author liucr
 * @data 2022/7/9
 */
@Controller
public class RapidCalculation {

    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/rapid")
    public String rapid(Model model, Map<String,Object> map){
         List<Map> list = new ArrayList<>();
        List<String> listQuestion = getQuestion(25,26);
        for (int i=0;i<17;i++){
            int j = i*3;
            List<String> rapidList = listQuestion.subList(j, j + 3);
            Map map1 = new HashMap();
            map1.put("row",rapidList);
            list.add(map1);
        }
        model.addAttribute("rapidList",list);
         return "rapid";
    }
    public List<String> getQuestion(int addnum,int subnum){
        List<String> list = new ArrayList<>();
        List<String> addList = getQuestionAdd(addnum);
        List<String> subList = getMathQuestionSub(subnum);
        list.addAll(addList);
        list.addAll(subList);
        Collections.shuffle(list);
        return list;
    }

    public List<String> getQuestionAdd(int num){
        List<String> rapidList =   GenerateCalculationAuestions.generateArithmeticalFormula(
                25,99,false,false,"+",num );
       return rapidList;
    }
    public List<String> getMathQuestionSub(int num){
        List<String> rapidList =   GenerateCalculationAuestions.generateArithmeticalFormula(
                15,99,false,false,"-",num );
            return rapidList;
    }


}
