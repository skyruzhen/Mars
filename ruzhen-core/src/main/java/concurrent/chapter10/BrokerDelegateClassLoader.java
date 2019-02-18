package com.ruzhen.concurrent.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//需要继承ClassLoader
public class BrokerDelegateClassLoader extends ClassLoader {
    //定义默认的class存放路径
    private final static Path DEAFULT_CLASS_DIR = Paths.get("D:", "classloader1");
    private final Path classDir;

    //使用默认的class路径
    public BrokerDelegateClassLoader() {
        super();
        this.classDir = DEAFULT_CLASS_DIR;
    }

    //允许传入指定路径的class路径
    public BrokerDelegateClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    //指定class路径的同时，指定父类加载器
    public BrokerDelegateClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    //重写父类的finClass方法，这是至关重要的步骤
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //读取class的二进制数据
        byte[] classBytes = this.readClassBytes(name);
        //如果数据为null, 或者没有读到任何信息，则抛出ClassNotFoundException异常
        if(null == classBytes || classBytes.length == 0){
            throw new ClassNotFoundException("Can not load the class "+name);
        }

        //调用defineClass方法定义class
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    //将class文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " occur error.", e);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //①根据类的全部经名称进行加锁，确保每一个类在多线程的情况下只被加载一次。
        synchronized (getClassLoadingLock(name)){
            //②到已加载类的缓存中查看该类是否已经被加载，如果已加载则直接返回
            Class<?> klass = findLoadedClass(name);
            //③如果缓存中没有被加载的类，则需要对其进行首次加载，如果类的全路径以java和javax开头，
            // 则直接委托给系统类加载器对其进行加载
            if(klass == null){
                //④
                if(name.startsWith("java.")||name.startsWith("javax")){
                    try {
                        klass = getSystemClassLoader().loadClass(name);
                    } catch (ClassNotFoundException e) {
                        //ignore 忽略
                    }
                }else{
                    //⑤如果类不是以java和javax开头，则尝试用我们自定义的类加载进行加载
                    try {
                        klass = this.findClass(name);
                    } catch (ClassNotFoundException e) {
                        //ignore
                    }
                    //⑥若自定义类加载仍旧没有完成对类的加载，则委托给其父类加载器进行加载或者系统类加载器进行加载
                    if(klass == null){
                        if(getParent() != null){
                            klass = getParent().loadClass(name);
                        }else{
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }//if结束
            //⑦ 经过若干次的尝试之后，如果还是无法对类进行加载，则抛出无法找到类的异常。
            if(null == klass){
                throw new ClassNotFoundException("The class "+name+" not found.");
            }
            if(resolve){
                resolveClass(klass);
            }
            return klass;
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
