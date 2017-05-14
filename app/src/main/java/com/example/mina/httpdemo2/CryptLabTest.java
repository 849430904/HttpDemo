package com.example.mina.httpdemo2;

/**
 * Created by Mina on 2017/5/8.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mina.httpdemo2.CryptLib;

public class CryptLabTest extends AppCompatActivity {
String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypt);
        Button btnE=(Button)findViewById(R.id.cryE);
        Button btnD = (Button)findViewById(R.id.cryD);


        btnE.setOnClickListener(new View.OnClickListener() {
            TextView tv1 = (TextView)findViewById(R.id.tv_result);

            @Override
            public void onClick(View v) {

                try {
                    CryptLib _crypt = new CryptLib();
                  //  String output = "";
                    String plainText = "This is the text to be encrypted.";
                    String key = CryptLib.SHA256("AT_WS_App", 32); //32 bytes = 256 bit
                    String iv = CryptLib.generateRandomIV(16); //16 bytes = 128 bit
                    output = _crypt.encrypt(plainText, key, iv); //encrypt
                    System.out.println("encrypted text=" + output);
                    tv1.setText("encrypted text=" + output);


                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            TextView tv2 = (TextView)findViewById(R.id.tv_result2);
            @Override
            public void onClick(View v) {
                try {
                    CryptLib _crypt = new CryptLib();
                    String output = "";
                    String plainText = "This is the text to be encrypted.";
                    String key = CryptLib.SHA256("AT_WS_App", 32); //32 bytes = 256 bit
                    String iv = CryptLib.generateRandomIV(16); //16 bytes = 128 bit
                    output = _crypt.encrypt(plainText, key, iv); //encrypt
                    output = _crypt.decrypt(output, key, iv); //decrypt
                    System.out.println("decrypted text=" + output);
                    tv2.setText("decrypted text=" + output);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
