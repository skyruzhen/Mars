package com.ruzhen.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.SimpleByteSource;

import java.io.UnsupportedEncodingException;

/**
 * 〈一句话功能简述〉<br>
 * 〈覆写HashedCredentialsMatcher 密码加密 〉
 *
 * @author lizhen
 * @create 2018/7/5
 * @since 1.0.0
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
    public MyHashedCredentialsMatcher(){
        this.setHashAlgorithmName("SHA-1");
    }

    /**
     *
     * @param credentials 密码
     * @param salt        盐值
     * @param hashIterations hash加密次数
     * @return
     */
    @Override
    protected Hash hashProvidedCredentials(Object credentials, Object salt, int hashIterations) {
        try {
            //shiro sha1加密规则适配spring security sha1加密规则， 只针对后台管理系统eHour的加密规则有效
            //new String()中调用了StringCoding的decode()方法
            return new SimpleHash(this.getHashAlgorithmName(),
                    new String((char[]) credentials)+"{"
                    + new String(((SimpleByteSource)salt).getBytes(), 0,  (((SimpleByteSource)salt).getBytes()).length, "UTF-8")+"}",
                    null,hashIterations );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}