package com.example.mina.httpdemo2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class MainActivityCrypt2 extends AppCompatActivity {

    private static final String NAMESPACE = "http://tempuri.org/" ;
    private static final String URL="https://www.arriba-tpe.com:89/ws_app.asmx";
    private static final String METHOD_NAME = "get_member_info";
    private static final String SOAP_ACTION = "http://tempuri.org/get_member_info";
    private EditText editText;
    private Button button;
    private TextView textView,textView3;
    String output;
    String outputpass;
    String Dnoutput;
    String Dnoutputpass;
    String outputmcode;
String outputks;
    String Jsonresult;
    String n;
  String IMEI;
    String token ="rSoaI6SvtLHDOW5qa3xobo3zrE2SAqIy0kjFA8Lm49AaSXzsUr9tVL7uzdqE6m4aJhSe21Phhp/HTSNXSXG6SlgF+eLxiYDFZid4F9twDBQV8SO4AqncKysdMEkFHIvHFonX927xF7g9Sm+dzfmuRJkYE4a0e/JCxD68qeSOzR0tmSl6royqnoOxw8qXO9v3VZTPnt0Vgo0NRN1igfpf5941Y/yp39feD/CQXo7yEacIMYGLu7OE79S91cmvjvYwHze5gmagFS6gi6TCL0BIDtxMVadXZHSdo8rjUW/2MZ0aik9R0ExRah0UDyT0W4jUTFcrKX1zBoQX9Lod1Xp2ZoSEVVvauWnL1cFRoME0CCtj46If4ocNhwRuULQ8O4wObKdVOf4BTBNoymcAVV7GJ2YIEn9XP4gRb8dd9gSV1mDoJ7qGGhSmD03zaM8U6Ne9FgTjyZ7ptvlkDvqQBDtKuCp3H5abR3ewBWMyYDWpEXPCWmcDHiTmckg74Q3U7PyuXKSJEuaqLi2gFRqBcvELRYkhT3oN8lwsWuFx+yX6gacqYsZwleQev8CCfyrHzoaWPBuhxXH4iBSeYKzSHkFCgDXLo7swdk0dxstd/RdlqZ6XawSto6Qr6TH+7EqV4FG9";


   // TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
  //  String deviceId = tm.getDeviceId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    //   IMEI = mTelManager.getDeviceId();
        //System.out.println("TESTTEST");
        editText = (EditText)findViewById(R.id.editText1);
        button = (Button)findViewById(R.id.button1);
        textView = (TextView)findViewById(R.id.textView);
        textView3 = (TextView)findViewById(R.id.textView3);
        Button btnE=(Button)findViewById(R.id.cryE);
        Button btnD = (Button)findViewById(R.id.cryD);
        Button btn = (Button)findViewById(R.id.button);
       // TextView textView8 = (TextView)findViewById(R.id.textView8);

        btnE.setOnClickListener(En);
        btnD.setOnClickListener(Dn);

        button.setOnClickListener(new Button.OnClickListener()
        {

            public void onClick(View v) {
                String value;

                value = editText.getText().toString();

                if(value==null || "".equals(value)){
                    textView.setText("No value");
                    editText.setFocusable(true);
                }else{
                    AsyncCallWS task = new AsyncCallWS();
                    task.execute(value);

                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    JSONObject jObj = new JSONObject(Dnoutput);
                    n = jObj.getString("qq");
                    textView3.setText(n);
                    Log.d("unexpected ",n);

                } catch (JSONException e) {
                    Log.e("MYAPP", "unexpected JSON exception----------------------------------------------------------------------------------------------------------------",e);
                    // Do something to recover ... or kill the app.
                }
            }
        });
    }


    private View.OnClickListener En= new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv1 = (TextView)findViewById(R.id.tv_result);
            TextView textView4 = (TextView)findViewById(R.id.textView4);
            TextView textView6 = (TextView)findViewById(R.id.textView6);
            TextView textView7 = (TextView)findViewById(R.id.textView7);
            try {
                CryptLib _crypt = new CryptLib();
                //  String output = "";
                String plainText = "ks";
                String key = CryptLib.SHA256("AT_WS_App", 32); //32 bytes = 256 bit
                String iv = "85026020"; //16 bytes = 128 bit
                outputks = _crypt.encrypt(plainText, key, iv); //encrypt
                System.out.println("encrypted text=" + outputks);
                tv1.setText("encrypted text=" + outputks);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                        CryptLib _crypt = new CryptLib();
                        //  String output = "";
                        String plainText = "kstest";
                        String key = CryptLib.SHA256("AT_WS_App", 32); //32 bytes = 256 bit
                        String iv = "85026020"; //16 bytes = 128 bit
                        output = _crypt.encrypt(plainText, key, iv); //encrypt
                        System.out.println("encrypted text=" + output);
                textView4.setText("encrypted text=" + output);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

            try {
                CryptLib _crypt = new CryptLib();
                //  String output = "";
                String plainText = "aa123456";
                String key = CryptLib.SHA256("AT_WS_App", 32); //32 bytes = 256 bit
                String iv = "85026020"; //16 bytes = 128 bit
                outputpass = _crypt.encrypt(plainText, key, iv); //encrypt
                System.out.println("encrypted text=" + outputpass);
                textView6.setText("encrypted text=" + outputpass);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                CryptLib _crypt = new CryptLib();
                //  String output = "";
                String plainText = "1234567890";
                String key = CryptLib.SHA256(token, 32); //32 bytes = 256 bit
                String iv = "85026020"; //16 bytes = 128 bit
                outputmcode = _crypt.encrypt(plainText, key, iv); //encrypt
                System.out.println("encrypted text=" + outputmcode);
                textView7.setText("encrypted text=" + outputmcode);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                    }
        };

    private View.OnClickListener Dn= new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv2 = (TextView)findViewById(R.id.tv_result2);
           // TextView textView5 = (TextView)findViewById(R.id.textView5);
            TextView textView7 = (TextView)findViewById(R.id.textView7);

                    try {
                        CryptLib _crypt = new CryptLib();
                        String output = "";
                        String plainText = Jsonresult;
                        String key = CryptLib.SHA256(token, 32); //32 bytes = 256 bit
                        String iv ="85026020" ; //16 bytes = 128 bit
                        Dnoutput = _crypt.decrypt(Jsonresult, key, iv); //decrypt
                        System.out.println("decrypted text=" + Dnoutput +"------------------------------------------------------------------");
                        tv2.setText("decrypted text=" + Dnoutput);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            try {
                CryptLib _crypt = new CryptLib();
                String output = "";
                String plainText = "password";
                String key = CryptLib.SHA256("AT_WS_App", 32); //32 bytes = 256 bit
                String iv = "85026020"; //16 bytes = 128 bit
                output = _crypt.encrypt(plainText, key, iv); //encrypt
                Dnoutputpass = _crypt.decrypt(output, key, iv); //decrypt
                System.out.println("decrypted text=" + Dnoutputpass);
             //   textView5.setText("decrypted text=" + Dnoutputpass);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    };


    public String convert(String type){
        String receivedString="not work"; //預設回傳值
        SSLConection.allowAllSSL();
        try
        {
            //1. Initialize soap request + add parameters
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


           request.addProperty("m_code", outputmcode);
            request.addProperty("token",token);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            envelope.dotNet = true;
            HttpTransportSE httpTransport = new HttpTransportSE(URL);

            httpTransport.debug = true;
            try {
                httpTransport.call(SOAP_ACTION, envelope);
            } catch (HttpResponseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } //send request
            Object result = null;
            try {
                result = (Object)envelope.getResponse();
            } catch (SoapFault e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Object response = (Object)envelope.getResponse();
            receivedString = response.toString();

            System.out.println("===================="+receivedString);

        }catch(Exception e) {
            e.printStackTrace();
            receivedString= e.toString();
            return receivedString;
        }
        return receivedString;
    }

    private class AsyncCallWS extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String returnValue = convert(params[0]); //呼叫WS
            return returnValue;

        }
        @Override
        protected void onPostExecute(String result) {
            Jsonresult =  result;
            textView.setText(Jsonresult); //執行doInBackground後執行

        }
        @Override
        protected void onPreExecute() {
            textView.setText("Receiving..."); //執行doInBackground前執行
        }
        @Override
        protected void onProgressUpdate(String... values) {
        }
    }
}
