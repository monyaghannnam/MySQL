package com.example.mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.txt);
        Thread t1=new Thread();
        t1.execute();


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


  class Thread extends AsyncTask<Void,Void,String> {

      @Override
      protected String doInBackground(Void... voids) {
          String url1="http://192.168.1.109/SLOTS.php";
          String result="";
          try {
              if(isNetworkConnected()) {
                  URL url = new URL(url1);
                  URLConnection connection = url.openConnection();
                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                  String line = "";

                  while ((line = bufferedReader.readLine()) != null) {
                      result += line;
                  }
                  bufferedReader.close();
              }
              else{
                  Toast.makeText(MainActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
              }

          } catch (Exception e) {
              e.printStackTrace();

          }
          return result;
      }

      @Override
      protected void onPreExecute() {
          super.onPreExecute();
      }

      @Override
      protected void onPostExecute(String result) {
          super.onPostExecute(result);
          if(isNetworkConnected()) {
              txt.setText(result);
          }
          else{Toast.makeText(MainActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();}
      }
  }
}
