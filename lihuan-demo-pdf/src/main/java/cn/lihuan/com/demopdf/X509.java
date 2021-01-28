package cn.lihuan.com.demopdf;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/22 14:00
 * @Created by Dell
 */
//数字证书加密
public class X509 {

    private final PrivateKey privateKey;  //表示一个私钥
    public final X509Certificate certificate;

    public X509(KeyStore keyStore, String certName,String password) {
        try {
            this.privateKey = (PrivateKey) keyStore.getKey(certName,password.toCharArray());
            this.certificate = (X509Certificate) keyStore.getCertificate(certName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //加密
    public byte[] encrypt(byte [] message){
        try {
            Cipher cipher = Cipher.getInstance(this.privateKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE,this.privateKey);
            return cipher.doFinal(message);
        }  catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

    //解密
    public byte[] decrypt (byte [] data){
        try {
            PublicKey publicKey = this.certificate.getPublicKey();
            Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
    //签名
    public byte[] sign(byte [] message){
        try {
            Signature signature = Signature.getInstance(this.certificate.getSigAlgName());
            signature.initSign(this.privateKey);
            signature.update(message);
            return signature.sign();
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //验证签名
    public boolean verify(byte [] message,byte [] sig){
        try {
            Signature signature =Signature.getInstance(this.certificate.getSigAlgName());
            signature.initSign(privateKey);
            signature.update(message);
            return signature.verify(sig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //数字证书
    static  KeyStore loadKeyStore(String keyStoreFile,String password){
        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(keyStoreFile)))) {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(inputStream,password.toCharArray());
            return ks;
        } catch (Exception ce){
            throw new RuntimeException(ce);
        }
    }


    public static void main(String[] args) throws Exception {
        byte [] message ="heloo ,用X.509证书进行加密和签名".getBytes();
        //读取keystore
        KeyStore ks =loadKeyStore("D:\\my.keystore","456789");
        //读取证书
        X509 x509 = new X509(ks,"mycert","123456");

        //加密
        byte[] encrypt = x509.encrypt(message);
        System.out.println("加密：" + Base64.getEncoder().encodeToString(encrypt).toString());

        //解密
        byte[] decrypt = x509.decrypt(encrypt);
        String s = new String(decrypt, "utf-8");
        System.out.println("解密：" + s);

//        //签名
//        byte[] sign = x509.sign(message);
//        System.out.println("Base64.getEncoder().encode(encrypt) = " + Base64.getEncoder().encode(sign));
//
//        //验证签名
//        boolean verify = x509.verify(message, sign);
//        System.out.println("verify = " + verify);

    }

}
