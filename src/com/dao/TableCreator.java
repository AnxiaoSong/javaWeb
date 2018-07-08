package com.dao;

import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;
 
import annotiation.Constraints;
import annotiation.SQLDate;
import annotiation.SQLInteger;
import annotiation.Table;
import annotiation.SQLString;

public class TableCreator {
    private static String getConstraints(Constraints constraints) { // 获取字段约束属性
        String cons = "";
        if (!constraints.allowNull()) {
            cons += " NOT NULL";
        }
        if (constraints.primaryKey()) {
            cons += " PRIMARY KEY";
        }
        if (constraints.unique()) {
            cons += " UNIQUE";
        }
        if(constraints.auto()){
	    cons+=" AUTO_INCREMENT";
	    
	}
   
        	return cons; 
    }
    
    //这边还需要通过IO来遍历指定model包下所有实体类，
    private static ArrayList<String> getTables(String packagePath) {
        ArrayList<String> tables = new ArrayList<String>();
        List<String> classNames=PackageUtil.getClassName(packagePath);
        //这里是我自己写的，为的是在生成数据库表的时候可以单独针对一个实体类生成数据库表
        if(classNames.size()<1){//如果获取到的包内的类的数量小于1个，直接将路径返回
        	tables.add(packagePath);
        }else{
	        for (String cl : PackageUtil.getClassName(packagePath)) {//原方法是Package.getPackage()，我不知道怎么用，就在往上找了一个第三方的获取包内实体类的工具代替。
	            tables.add(cl);
	        }
        }
		return tables;
    }
    
    
    public static List<String> getSql(String packagePath) throws ClassNotFoundException {
       List<String> sqlList=new ArrayList<>();
        String sql = null;
        ArrayList<String> tables = getTables(packagePath);
//        System.out.println("tables: " + tables);
//        String[] tables = {"com.qoniu.test.pojo.Head"};
        for (Object className : tables) {
            Class<?> cl = Class.forName((String) className);      // 通过类名得到该实体类
            Table dbtable = cl.getAnnotation(Table.class); // 通过注解得到表名
            // 批量生成数据库表的时候，考虑到有继承类的问题，我不清楚有没有更好的办法，我自己的方案是对于继承的父类不作注解，得到的表明为空，这样就可以跳过了
            if(dbtable==null){
            	continue;
            }
            String tableName = dbtable.name().length() > 1 ? dbtable.name() : cl.getName().toUpperCase();
            /* comments
            System.out.println("tableName: " + tableName);
            */
            List<String> columns = new LinkedList<String>();
            for (Field field : cl.getDeclaredFields()) {       // 得到该类下所有属性
                String columnName = null;
                Annotation[] annotations = field.getAnnotations();
                if (annotations.length < 1) {
                    continue;
                }
                //这里是对实体类的注解进行解析，可自由定义
                if (annotations[0] instanceof SQLString) {
                    SQLString sStr = (SQLString)annotations[0];
                    columnName = sStr.name().length() < 1 ? field.getName() : sStr.name();
                
                    columns.add(columnName + " VARCHAR(" + sStr.size() + ")" + getConstraints(sStr.constraints()));
                }
                if (annotations[0] instanceof SQLInteger) {
                    SQLInteger sStr = (SQLInteger)annotations[0];
                    columnName = sStr.name().length() < 1 ? field.getName() : sStr.name();
                    columns.add(columnName + " int(" + sStr.size() + ")" + getConstraints(sStr.constraints()));
                }
                if (annotations[0] instanceof SQLDate) {
                    SQLDate sStr = (SQLDate)annotations[0];
                    columnName = sStr.name().length() < 1 ? field.getName() : sStr.name();
                    columns.add(columnName + " datetime" );
                }
            }
            
            StringBuilder sb = new StringBuilder("Create Table if not exists " + tableName + "(");
            //对于每个表里通用的字段直接写了，一般这个是放在父类的，因为我不知道怎么在生成语句的时候去把父类的一起生成，就这么写了
            sb.append("\n    id int(12) PRIMARY KEY AUTO_INCREMENT,");
        	sb.append("\n    stat int(10),");
        	sb.append("\n    createTime datetime,");
        	sb.append("\n    userId VARCHAR(50),");
            for (Object column : columns) {
                sb.append("\n    " + column + ",");            // 拼接各个字段的定义语句
            }
            sql = sb.substring(0, sb.length() - 1) +");";
            System.out.println("\n=========\n" + sql + "\n=========\n");// 测试输出
            sqlList.add(sql);
        }
        return sqlList;
    }

}
