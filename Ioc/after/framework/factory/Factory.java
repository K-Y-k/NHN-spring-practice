package org.example.framework.factory;

import org.example.framework.annotations.Part;
import org.example.framework.annotations.First;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factory {

    static Map<Class<?>, Object> beanMap = new HashMap<>();

    //TODO-2 사용자가 객체를 직접 초기화하지 않고 요청을 하면 @Part 가 붙은 클래스의 객체를 반환한다.
    public static <T> T getInstance(Class<T> clazz) throws Exception {
        T object = (T) beanMap.get(clazz);
        if (object == null) {
            object = (T) scan(clazz);
            beanMap.put(clazz, object);
        }
        return object;
    }

    public static Object scan(Class<?> clazz) throws Exception {
        String packageName = "org.example.customcode";
        List<Class<?>> annotatedClasses = scanDirectoryForAnnotatedClasses(packageName, clazz);

        if (annotatedClasses.isEmpty()) {
            throw new RuntimeException("no object " + clazz.getName() + " selected");
        } else if (annotatedClasses.size() > 1) {
            throw new RuntimeException("more than one object " + clazz.getName() + "  selected");
        }

        return annotatedClasses.getFirst().getDeclaredConstructor().newInstance();
    }

    private static <T> List<Class<? extends T>> scanDirectoryForAnnotatedClasses(String packageName, Class<?> interfaceName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        File directory = new File(java.util.Objects.requireNonNull(classLoader.getResource(path)).getFile());

        List<Class<? extends T>> annotatedClasses = new ArrayList<>();
        scanDirectory(directory, packageName, annotatedClasses, interfaceName);

        annotatedClasses = annotatedClasses.stream().filter(it -> it.isAnnotationPresent(Part.class) || it.isAnnotationPresent(First.class)).toList();

        if (annotatedClasses.size() > 1) {
            annotatedClasses = annotatedClasses.stream().filter(it -> it.isAnnotationPresent(First.class)).toList();
        }

        return annotatedClasses;
    }

    private static <T> void scanDirectory(File directory, String packageName, List<Class<? extends T>> annotatedClasses, Class<?> interfaceName) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 재귀 호출로 하위 디렉토리 탐색
                    scanDirectory(file, packageName + "." + file.getName(), annotatedClasses, interfaceName);
                } else if (file.getName().endsWith(".class")) {
                    // .class 파일 처리
                    String className = getClassNameWithPackage(packageName, file);
                    try {
                        Class<?> clazz = Class.forName(className);
                        if (interfaceName.isAssignableFrom(clazz)) {  // 인터페이스 구현 확인
                            annotatedClasses.add((Class<? extends T>) clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        // 클래스를 찾을 수 없는 경우 예외 처리
                    }
                }
            }
        }
    }

    private static String getClassNameWithPackage(String packageName, File file) {
        String fileName = file.getName();
        String className = fileName.substring(0, fileName.length() - 6); // ".class" 제거
        return packageName + '.' + className;
    }

}