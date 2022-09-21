/**
 * Copyright (c) 2018-2020 四川健康久远科技有限公司 All Rights Reserved.
 * FileName: GenerateCalculationAuestions
 *
 * @author: liucr
 * @date: 2022/5/29 20:28
 * @version: 1.0.0
 */
package com.walter.study.util;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * Title: 
 * Description: 
 *
 * @author liucr
 * @data 2022/5/29
 */
public class GenerateCalculationAuestions {


    public static void main(String[] args) throws IOException, XDocReportException {
//        GenerateCalculationAuestions action = new GenerateCalculationAuestions();
//        action.test();


        List<String> result =   GenerateCalculationAuestions.generateArithmeticalFormula(
                8,90 ,false,false,"+", 10);

        System.out.println(result.size());
        for (String str :
                result) {
            System.out.println(str);
        }
    }

    /** 计算符号 */
    public static List<String> symbolList = Arrays.asList("+","-");


    /**
     * 生成算式
     * @param startNum 起始数
     * @param endNum    结束数
     * @param carry 是否进位
     * @param abdication   是否退位
     * @param operation 计算符号
     * @param num   题数
     * @return
     */
    public static List<String> generateArithmeticalFormula  (Integer startNum,Integer endNum,
                                       Boolean carry ,Boolean abdication,String operation,

                                       Integer num){
        if(startNum>endNum){
            throw  new RuntimeException("起始数不能大于结束数！！");
        }
//        if(StringUtils.isBlank(operation)){
//            throw new RuntimeException("计算符号不能为空！！");
//        }

        List<String> result = new ArrayList<>();

        for (int i = 0;i < num ; i++){
            Integer prevNum =  randomNum( startNum, endNum);
            Integer nextNum =  randomNum( startNum, endNum);

//            if(StringUtils.isBlank(operation)){
//                operation = i/2 == 0 ? symbolList.get(0) : symbolList.get(1);
//            }

            if(StringUtils.equals(operation,"-") ){
                if(prevNum<nextNum || (prevNum-nextNum <15) ){
                    i--;
                    continue;
                }
            }else if(StringUtils.equals(operation,"+") ){
//                if(prevNum+nextNum >= 100){
//                    i--;
//                    continue;
//                }
            }

//            if(String.valueOf(prevNum).startsWith(String.valueOf(nextNum).substring(0,1))){
//                i--;
//                continue;
//            }

            String item = prevNum + " " + operation + " " + nextNum + " = ";
            if (result.contains(item)){
                i--;
                continue;
            }else {
                result.add(item);
            }
        }

        //算式去重
        HashSet<String> set=new HashSet<>(result);
        if(set.size() == result.size() && set.size() == num){
            return result;
        }else {
            HashSet<String> tempSet =  new HashSet<>(GenerateCalculationAuestions.generateArithmeticalFormula(
                    8,95,false,false,"+",num-set.size()));
            set.addAll(tempSet);
            return new ArrayList<>(set);
        }
    };

    /**
     * 生成随机数
     * @param startNum
     * @param endNum
     * @return
     */
    public static  Integer randomNum(Integer startNum,Integer endNum ){
        Random rand=new Random();
//      2.符合在[min,max]范围内的整数
        int  num = startNum + (int)(Math.random() * (endNum-startNum+1));

        return num;
    }


     public void test() throws  IOException, XDocReportException {
        generateWord();
    }

    public void generateWord() throws  IOException, XDocReportException  {
        //获取Word模板，模板存放路径在项目的resources目录下
        InputStream ins = this.getClass().getResourceAsStream("/模板.docx");
        //注册xdocreport实例并加载FreeMarker模板引擎
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(ins,
                TemplateEngineKind.Freemarker);
        //创建xdocreport上下文对象
        IContext context = report.createContext();

        //创建要替换的文本变量
        context.put("city", "北京市");
        context.put("startDate", "2020-09-17");
        context.put("endDate", "2020-10-16");
        context.put("totCnt", 3638763);
        context.put("totAmt", "6521");
        context.put("onCnt", 2874036);
        context.put("onAmt", "4768");
        context.put("offCnt", 764727);
        context.put("offAmt", "1753");
        context.put("typeCnt", 36);

        Map map1 = new HashMap<>();
        map1.put("num",1);
        map1.put("type","臭美毁肤");
        map1.put("sv",675512);
        map1.put("sa","589");

        Map map2 = new HashMap<>();
        map2.put("num",1);
        map2.put("type","臭美毁肤");
        map2.put("sv",675512);
        map2.put("sa","589");

        Map map3 = new HashMap<>();
        map3.put("num",2);
        map3.put("type","手机");
        map3.put("sv",789452);
        map3.put("sa","369");

        Map map4 = new HashMap<>();
        map4.put("num",3);
        map4.put("type","女装");
        map4.put("sv",8456);
        map4.put("sa","104");

        Map map5 = new HashMap<>();
        map5.put("num",4);
        map5.put("type","家具家装");
        map5.put("sv",528604);
        map5.put("sa","405");

        List  goodsList =  Arrays.asList(map1,map2,map3,map4,map5);

        context.put("goods", goodsList);

        //创建字段元数据
        FieldsMetadata fm = report.createFieldsMetadata();
        //Word模板中的表格数据对应的集合类型
        fm.load("goods", HashMap.class, true);

        //输出到本地目录
        FileOutputStream out = new FileOutputStream(new File("D://商品销售报表.docx"));
        report.process(context, out);
    }

}
