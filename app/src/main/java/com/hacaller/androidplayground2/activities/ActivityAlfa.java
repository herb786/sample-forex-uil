package com.hacaller.androidplayground2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hacaller.androidplayground2.R;
import com.hacaller.androidplayground2.models.Taxon;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActivityAlfa extends AppCompatActivity {

    @BindView(R.id.btn_launch) Button btnLaunch;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.txt_content) TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_alfa);
        ButterKnife.bind(this);

        btnLaunch.setOnClickListener(new LaunchListener(txtContent));
        btnNext.setOnClickListener(new NextListener());

    }


    private class LaunchListener implements View.OnClickListener{

        private TextView textView;

        public LaunchListener(TextView textView){
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            PaleoBioApiTest task = new PaleoBioApiTest(textView);
            task.execute();
        }
    }


    private class NextListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ActivityBeta.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }


    public class RecordTaxon {
        ArrayList<Taxon> records;
    }

    private class PaleoBioApiTest extends AsyncTask<Void,Void,Void>{

        TextView text;
        Taxon taxon;

        public PaleoBioApiTest(TextView text){
            this.text = text;
        }

        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            try {
                //RequestBody body = RequestBody.create(JSON, json);
                String url = "https://paleobiodb.org/data1.2/taxa/single.json?name=Dascillidae&show=attr";
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Response response = client.newCall(request).execute();
                InputStream inputStream = response.body().byteStream();
                RecordTaxon records = gson.fromJson(IOUtils.toString(inputStream, Charset.forName("UTF-8")), RecordTaxon.class);
                taxon = records.records.get(0);
                //InputStream inputStream = response.body().byteStream();
                //JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
                //JsonReader jsonReader = new JsonReader(response.body().charStream());
                //String jsonValue = response.body().string();
                //JSONObject jsonObject = new JSONObject(jsonValue);
                //parseJSONobject(jsonObject);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (taxon != null) {
                text.setText(taxon.getAtt() + '\n' + taxon.getNam());
            }
        }
    }



}
