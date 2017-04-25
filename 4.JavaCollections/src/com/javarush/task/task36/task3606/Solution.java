package com.javarush.task.task36.task3606;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException
    {
        try {
            ClassLoader classLoader = Solution.class.getClassLoader();
            String pathWithDot1 = packageName.replaceAll("[/\\\\]",
                    Matcher.quoteReplacement(File.separator)).replaceAll(Matcher.quoteReplacement(File.separator), ".");
            String subPackage1 = pathWithDot1.substring(pathWithDot1.indexOf(Solution.class.getPackage().getName()));
            //   System.out.println(Solution.class.getPackage().getName());
            for (File file : new File(packageName).listFiles()) {
             //   System.out.println(file.getName());
                if (!file.isDirectory() && file.getName().endsWith(".class")) {
                    String className = subPackage1 + "." + file.getName().substring(0, file.getName().lastIndexOf(".class"));
                    Class clazz = classLoader.loadClass(className);
                    if (HiddenClass.class.isAssignableFrom(clazz))
                        hiddenClasses.add(clazz);
                }
            }
        }
        catch (Exception e)
        {
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }
    public HiddenClass getHiddenClassObjectByKey(String key)
    {
        for (Class clazz : hiddenClasses)
        {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
            {
                try
                {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    constructors[0].setAccessible(true);
                    return (HiddenClass) constructors[0].newInstance(null);
                }
                catch (Exception e)
                {
                }
            }
        }
        return null;
    }
}

