package com.hacaller.androidplayground2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hacaller.androidplayground2.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class ActivityDelta extends AppCompatActivity {

    @BindView(R.id.btn_launch) Button btnLaunch;
    @BindView(R.id.btn_launch2) Button btnLaunch2;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.txt_content) TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_delta);
        ButterKnife.bind(this);

        btnLaunch.setOnClickListener(new LaunchListener(txtContent,1));
        btnLaunch2.setOnClickListener(new LaunchListener(txtContent,2));

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivitySigma.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

    private class LaunchListener implements View.OnClickListener{

        private TextView textView;
        private int choice;

        public LaunchListener(TextView textView, int choice){
            this.textView = textView;
            this.choice = choice;
        }

        @Override
        public void onClick(View v) {
            ForexApiTest task = new ForexApiTest(textView);
            Integer[] params = new Integer[]{choice};
            task.execute(params);
        }
    }


    public class Rates {
        float USD;
        float GBP;
    }

    public class Quotes {
        float USDAUD;
        float USDCHF;
        float USDEUR;
        float USDGBP;
        float USDPLN;
    }

    public class ForEx {
        String base;
        String date;
        Rates rates;
    }

    public class ForEx2 {
        String source;
        Quotes quotes;
    }

    //http://data.fixer.io/api/latest?access_key=e27bfeb93b3f5447c1a0c9d8147b71f7&base=USD&symbols=EUR,GBP
    public interface FixerService {
        @GET("latest?")
        Call<ForEx> getRate(@QueryMap Map<String,String> options);
    }

    //http://apilayer.net/api/live?access_key=YOUR_ACCESS_KEY&currencies=AUD,CHF,EUR,GBP,PLN
    public interface CurrencyLayerService {
        @GET("live?")
        Call<ForEx2> getRate(@QueryMap Map<String,String> options);
    }


    private ForEx getForex() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://data.fixer.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FixerService service = retrofit.create(FixerService.class);
        Map<String,String> options = new HashMap<String,String>();
        options.put("symbols","USD,GBP");
        options.put("access_key","7dc9416145c122d633eca551592585e8");
        Call<ForEx> forex = service.getRate(options);
        try {
            return forex.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ForEx2 getForex2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apilayer.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CurrencyLayerService service = retrofit.create(CurrencyLayerService.class);
        Map<String,String> options = new HashMap<String,String>();
        options.put("currencies","AUD,CHF,EUR,GBP,PLN");
        options.put("access_key","e27bfeb93b3f5447c1a0c9d8147b71f7");
        Call<ForEx2> forex = service.getRate(options);
        try {
            return forex.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class ForexApiTest extends AsyncTask<Integer,Void,Integer> {

        TextView text;
        ForEx mForex;
        ForEx2 mForex2;

        public ForexApiTest(TextView text){
            this.text = text;
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int choice = params[0];
            switch (choice){
                case 1:
                    mForex = getForex();
                    return 1;
                case 2:
                    mForex2 = getForex2();
                    return 2;
                default:
                    return 0;
            }
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            switch (aVoid){
                case 1:
                    text.setText(String.format("GBP : %f\nUSD : %f",mForex.rates.GBP,mForex.rates.USD));
                    break;
                case 2:
                    text.setText(String.format("EUR : %f\nGBP : %f",mForex2.quotes.USDEUR,mForex2.quotes.USDGBP));
                    break;
            }

        }
    }


}
