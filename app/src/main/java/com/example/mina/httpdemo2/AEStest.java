package com.example.mina.httpdemo2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

/**
 * Created by Mina on 2017/5/8.
 */

public class AEStest extends Activity {


    private final static String IvAES = "85026020";
    private final static String KeyAES = "12345678901234567890123456789012";
    private final static String TextAES = "小黑人的Android教室 !";
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes2);
        tv = (TextView) findViewById(R.id.tv_result);
        Button btn1 = (Button)findViewById(R.id.btne);
        Button btn2 = (Button)findViewById(R.id.btnd);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //加密------------------------------------------------------
                    //將IvAES、KeyAES、TextAES轉成byte[]型態帶入EncryptAES進行加密，再將回傳值轉成字串
                    byte[] TextByte;
                    TextByte = UtilTEST.EncryptAES(IvAES.getBytes("UTF-8"), KeyAES.getBytes("UTF-8"), TextAES.getBytes("UTF-8"));
                    String TEXT = Base64.encodeToString(TextByte, Base64.DEFAULT);
                    //加密字串結果為 : xq/WqrKuXIqLxw1BM4GJoAqPQp6Zh+vqLykVAj2GHFY=
                    //---------------------------------------------------------------------------------

                 tv.setText("加密" + TEXT);

                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        /*
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //將IvAES、KeyAES、Text(加密文字:xq/WqrKuXIqLxw1BM4GJoAqPQp6Zh+vqLykVAj2GHFY=)轉成byte[]型態帶入DecryptAES進行解密，再將回傳值轉成字串
                    byte[] TextByteDe = UtilTEST.DecryptAES(IvAES.getBytes("UTF-8"), KeyAES.getBytes("UTF-8"),Base64.decode(TEXT.getBytes("UTF-8"), Base64.DEFAULT));
                    String TEXTDe = new String(TextByteDe,"UTF-8");
                    //解密字串結果為 : 小黑人的Android教室 !
                    tv.setText("加密" + TEXTDe);

                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
*/







    }



}