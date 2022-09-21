/**
 * Copyright (c) 2018-2020 四川健康久远科技有限公司 All Rights Reserved.
 * FileName: SinglePattern
 *
 * @author: liucr
 * @date: 2022/8/13 19:08
 * @version: 1.0.0
 */
package com.walter.design;

/**
 * Title: 
 * Description: 
 *
 * @author liucr
 * @data 2022/8/13
 *
 * 单例模式
 */
public class SinglePatternDto {

    private volatile static SinglePatternDto dto = null;

    private SinglePatternDto() {
    }

    public static SinglePatternDto getDto(){
        if(dto == null){
            synchronized (SinglePatternDto.class){
                if(dto == null) {
                    dto = new SinglePatternDto();
                }
            }
        }
        return dto;
    }
}
