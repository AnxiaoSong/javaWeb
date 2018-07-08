package com.dao;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
 
public class PackageUtil {
 
	public static void main(String[] args) throws Exception {
		String packageName = "";
		// List<String> classNames = getClassName(packageName);
	
		List<String> classNames = getClassName(packageName, false);
		if (classNames != null) {
			for (String className : classNames) {
				System.out.println(className);
			}
		}
	}
 
	/**
	 * ��ȡĳ���£������ð��������Ӱ���������
	 * @param packageName ����
	 * @return �����������
	 */
	public static List<String> getClassName(String packageName) {
		return getClassName(packageName, true);
	}
 
	/**
	 * ��ȡĳ����������
	 * @param packageName ����
	 * @param childPackage �Ƿ�����Ӱ�
	 * @return �����������
	 */
	public static List<String> getClassName(String packageName, boolean childPackage) {
		List<String> fileNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		System.out.println(url);
		if (url != null) {
			String type = url.getProtocol();
			System.out.println(type);
			if (type.equals("file")) {
				fileNames = getClassNameByFile(url.getPath(), null, childPackage);
				
			} else if (type.equals("jar")) {
				fileNames = getClassNameByJar(url.getPath(), childPackage);
			}
		} else {
			fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
		}
		return fileNames;
	}
 
	/**
	 * ����Ŀ�ļ���ȡĳ����������
	 * @param filePath �ļ�·��
	 * @param className ��������
	 * @param childPackage �Ƿ�����Ӱ�
	 * 
	 * @return �����������
	 */
	private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		System.out.println("�Ƿ���Ŀ¼:"+file.isDirectory());
		File[] childFiles = file.listFiles();
		System.out.println(childFiles);
	  
		if(childFiles!=null)
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				if (childPackage) {
					myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
				}
			} else {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(".class")) {
					childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
					childFilePath = childFilePath.replace("\\", ".");
					myClassName.add(childFilePath);
				}
			}
		}
 
		return myClassName;
	}
 
	/**
	 * ��jar��ȡĳ����������
	 * @param jarPath jar�ļ�·��
	 * @param childPackage �Ƿ�����Ӱ�
	 * @return �����������
	 */
	private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		String[] jarInfo = jarPath.split("!");
		String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
		String packagePath = jarInfo[1].substring(1);
		try {
			JarFile jarFile = new JarFile(jarFilePath);
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry jarEntry = entrys.nextElement();
				String entryName = jarEntry.getName();
				if (entryName.endsWith(".class")) {
					if (childPackage) {
						if (entryName.startsWith(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					} else {
						int index = entryName.lastIndexOf("/");
						String myPackagePath;
						if (index != -1) {
							myPackagePath = entryName.substring(0, index);
						} else {
							myPackagePath = entryName;
						}
						if (myPackagePath.equals(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClassName;
	}
 
	/**
	 * ������jar�������ð�������ȡ�ð���������
	 * @param urls URL����
	 * @param packagePath ��·��
	 * @param childPackage �Ƿ�����Ӱ�
	 * @return �����������
	 */
	private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		if (urls != null) {
			for (int i = 0; i < urls.length; i++) {
				URL url = urls[i];
				String urlPath = url.getPath();
				// ��������classes�ļ���
				if (urlPath.endsWith("classes/")) {
					continue;
				}
				String jarPath = urlPath + "!/" + packagePath;
				myClassName.addAll(getClassNameByJar(jarPath, childPackage));
			}
		}
		return myClassName;
	}
}