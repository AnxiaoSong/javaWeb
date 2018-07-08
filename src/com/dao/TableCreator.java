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
    private static String getConstraints(Constraints constraints) { // ��ȡ�ֶ�Լ������
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
    
    //��߻���Ҫͨ��IO������ָ��model��������ʵ���࣬
    private static ArrayList<String> getTables(String packagePath) {
        ArrayList<String> tables = new ArrayList<String>();
        List<String> classNames=PackageUtil.getClassName(packagePath);
        //���������Լ�д�ģ�Ϊ�������������ݿ���ʱ����Ե������һ��ʵ�����������ݿ��
        if(classNames.size()<1){//�����ȡ���İ��ڵ��������С��1����ֱ�ӽ�·������
        	tables.add(packagePath);
        }else{
	        for (String cl : PackageUtil.getClassName(packagePath)) {//ԭ������Package.getPackage()���Ҳ�֪����ô�ã�������������һ���������Ļ�ȡ����ʵ����Ĺ��ߴ��档
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
            Class<?> cl = Class.forName((String) className);      // ͨ�������õ���ʵ����
            Table dbtable = cl.getAnnotation(Table.class); // ͨ��ע��õ�����
            // �����������ݿ���ʱ�򣬿��ǵ��м̳�������⣬�Ҳ������û�и��õİ취�����Լ��ķ����Ƕ��ڼ̳еĸ��಻��ע�⣬�õ��ı���Ϊ�գ������Ϳ���������
            if(dbtable==null){
            	continue;
            }
            String tableName = dbtable.name().length() > 1 ? dbtable.name() : cl.getName().toUpperCase();
            /* comments
            System.out.println("tableName: " + tableName);
            */
            List<String> columns = new LinkedList<String>();
            for (Field field : cl.getDeclaredFields()) {       // �õ���������������
                String columnName = null;
                Annotation[] annotations = field.getAnnotations();
                if (annotations.length < 1) {
                    continue;
                }
                //�����Ƕ�ʵ�����ע����н����������ɶ���
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
            //����ÿ������ͨ�õ��ֶ�ֱ��д�ˣ�һ������Ƿ��ڸ���ģ���Ϊ�Ҳ�֪����ô����������ʱ��ȥ�Ѹ����һ�����ɣ�����ôд��
            sb.append("\n    id int(12) PRIMARY KEY AUTO_INCREMENT,");
        	sb.append("\n    stat int(10),");
        	sb.append("\n    createTime datetime,");
        	sb.append("\n    userId VARCHAR(50),");
            for (Object column : columns) {
                sb.append("\n    " + column + ",");            // ƴ�Ӹ����ֶεĶ������
            }
            sql = sb.substring(0, sb.length() - 1) +");";
            System.out.println("\n=========\n" + sql + "\n=========\n");// �������
            sqlList.add(sql);
        }
        return sqlList;
    }

}
