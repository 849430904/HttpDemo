package com.example.mina.httpdemo2;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class UtilTEST{

    //16位的英數組合位元，可自行填寫 (下為小黑人暫訂)
    //32位的英數組合Key欄位，可自行填寫 (下為小黑人暫訂)
    //欲進行加密的文字字串
 /*   private final static String IvAES = "1234567890abcdef" ;
    private final static String KeyAES = "12345678901234567890123456789012";
    private final static String TextAES = "小黑人的Android教室 !";
    */
    private final static String IvAES = "1234567890abcdef" ;
    private final static String KeyAES = "12345678901234567890123456789012";
    private final static String TextAES = "ks";




    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            //加密------------------------------------------------------
            //將IvAES、KeyAES、TextAES轉成byte[]型態帶入EncryptAES進行加密，再將回傳值轉成字串
            byte[] TextByte;
            TextByte = EncryptAES(IvAES.getBytes("UTF-8"), KeyAES.getBytes("UTF-8"), TextAES.getBytes("UTF-8"));
            String TEXT = Base64.encodeToString(TextByte, Base64.DEFAULT);
            //加密字串結果為 : xq/WqrKuXIqLxw1BM4GJoAqPQp6Zh+vqLykVAj2GHFY=
            //---------------------------------------------------------------------------------





            //將IvAES、KeyAES、Text(加密文字:xq/WqrKuXIqLxw1BM4GJoAqPQp6Zh+vqLykVAj2GHFY=)轉成byte[]型態帶入DecryptAES進行解密，再將回傳值轉成字串
            byte[] TextByteDe = DecryptAES(IvAES.getBytes("UTF-8"), KeyAES.getBytes("UTF-8"),Base64.decode(TEXT.getBytes("UTF-8"), Base64.DEFAULT));
            String TEXTDe = new String(TextByteDe,"UTF-8");
            //解密字串結果為 : 小黑人的Android教室 !


        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }



    public static byte[] EncryptAES(byte[] iv, byte[] key,byte[] text)
    {
        try
        {
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(key, "AES");
            Cipher mCipher = null;
            mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //可選擇有加IV或不IV
            //mCipher.init(Cipher.ENCRYPT_MODE,mSecretKeySpec,mAlgorithmParameterSpec);
            mCipher.init(Cipher.ENCRYPT_MODE,mSecretKeySpec);

            return mCipher.doFinal(text);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    //AES解密，帶入byte[]型態的16位英數組合文字、32位英數組合Key、需解密文字
    public static byte[] DecryptAES(byte[] iv,byte[] key,byte[] text)
    {
        try
        {
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(key, "AES");
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //可選擇有加IV或不IV
            //mCipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec, mAlgorithmParameterSpec);
            mCipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec);
            return mCipher.doFinal(text);
        }
        catch(Exception ex)
        {
            return null;
        }
    }


}
