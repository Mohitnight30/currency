package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    List<String> keysList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView date =findViewById(R.id.date_and_time);
        Button update = findViewById(R.id.button2);

        Date CurrentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateTimeInstance().format(CurrentTime);
        date.setText(formattedDate);

        update.setOnClickListener(view -> {
            Date CurrentTime1 = Calendar.getInstance().getTime();
            String formattedDate1 = DateFormat.getDateTimeInstance().format(CurrentTime1);
            date.setText(formattedDate1);
        });

        EditText edtEuroValue = (EditText) findViewById(R.id.editText4);
        Button btnConvert = findViewById(R.id.button);


        btnConvert.setOnClickListener(v -> {
            edtEuroValue.setImeOptions(EditorInfo.IME_ACTION_DONE);
            if (!edtEuroValue.getText().toString().isEmpty()) {
               double euroVlaue = Double.parseDouble(edtEuroValue.getText().toString());
                try {
                    convertCurrency(euroVlaue);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(MainActivity.this, "Please Enter a Value to Convert..", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void convertCurrency( double euroVlaue) throws IOException {

        TextView textViews = (TextView) findViewById(R.id.textView7);

        String url = "https://lufickdev.bitbucket.io/api/latest";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String mMessage = Objects.requireNonNull(e.getMessage()).toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String mMessage = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                  ArrayList result = new ArrayList<Double>();

                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject b = obj.getJSONObject("rates");

                            Iterator keysToCopyIterator = b.keys();
                            keysList = new ArrayList<>();

                            while(keysToCopyIterator.hasNext()) {

                                String key = (String) keysToCopyIterator.next();

                                keysList.add(key);

                            }
                            for (int i = 0; i < keysList.size(); i++){
                            String val = b.getString(String.valueOf(keysList.get(i)));
                                result.add(euroVlaue * Double.parseDouble(val));

                              }

                            for(int i =0;i<keysList.size();i++){
                                textViews.append(keysList.get(i)+" : "+result.get(i));
                                textViews.append("\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }


        });
    }
}
