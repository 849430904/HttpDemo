package com.example.mina.httpdemo2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.example.mina.httpdemo2.UtilTEST;

import static com.example.mina.httpdemo2.UtilTEST.DecryptAES;
import static com.example.mina.httpdemo2.UtilTEST.EncryptAES;

public class MainActivity extends AppCompatActivity {

    /*
    private static final String NAMESPACE = "http://tempuri.org/" ;
    private static final String URL="https://www.arriba-tpe.com:89/ws_app.asmx";
    private static final String METHOD_NAME = "get_notice";
    private static final String SOAP_ACTION = "http://tempuri.org/get_notice";
*/

    private static final String NAMESPACE = "http://tempuri.org/" ;
    private static final String URL="https://www.arriba-tpe.com:89/ws_app.asmx";
    private static final String METHOD_NAME = "HelloWorld";
    private static final String SOAP_ACTION = "http://tempuri.org/HelloWorld";

/*
    private static final String SOAP_ACTION = "http://footballpool.dataaccess.eu/TopGoalScorers";
    private static final String METHOD_NAME = "TopGoalScorers";
    private static final String NAMESPACE = "http://footballpool.dataaccess.eu";
    private static final String URL = "http://footballpool.dataaccess.eu/data/info.wso?WSDL";
*/

    private EditText editText;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println("TESTTEST");
        editText = (EditText)findViewById(R.id.editText1);
        button = (Button)findViewById(R.id.button1);
        textView = (TextView)findViewById(R.id.textView);

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


    }



    public String convert(String type){
        String receivedString="not work"; //預設回傳值
        SSLConection.allowAllSSL();
        try
        {
            //1. Initialize soap request + add parameters
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            //2. Use this to add parameters
            /*
            PropertyInfo pi = new PropertyInfo();
            //PName

            pi.setName("PName");
            pi.setValue("ks");
            pi.setType(PropertyInfo.STRING_CLASS);
            request.addProperty(pi);
            //Type
            pi = new PropertyInfo();
            pi.setName("Type");
            pi.setValue(0);
            pi.setType(PropertyInfo.INTEGER_CLASS);
            request.addProperty(pi);
            request.addProperty("iTopN", type);
            */

            request.addProperty("sEncrypt","xq/WqrKuXIqLxw1BM4GJoAqPQp6Zh+vqLykVAj2GHFY=");
            request.addProperty("sNorma", "小黑人的Android教室 !");
            /*


            pi.setName("account");
            pi.setValue("Q/+LY3wn4h1oNAoCDNfAZu3EBSzHJuUGetouOhdXMc8=");
            request.addProperty(pi);

            pi.setName("password");
            pi.setValue("HTTk8MERFmFayiZNFcYXxslDEfLWfhrovNvjtAPOxIZP0QM7U/EbHYkDLHgRUexm");
            request.addProperty(pi);

            pi.setName("login_ip");
            pi.setValue("Po7AhWRi5R16L//74h9LjVtbSBQrQMeNfM0n51rKJv61Lze3N7p2CrBql59VifTE");
            request.addProperty(pi);

            pi.setName("login_time");
            pi.setValue("2017-01-21 23:59:59");
            request.addProperty(pi);
            */
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


            //Log.d("App",""+result.getProperty(1).toString());
            //receivedString = result.getProperty(1).toString();
            //Log.d("App",""+result.toString());
            //receivedString = result.toString();

            //HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            //androidHttpTransport.call(SOAP_ACTION, envelope);
           // SoapObject response = (SoapObject) envelope.bodyIn;

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
            textView.setText(result); //執行doInBackground後執行
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
