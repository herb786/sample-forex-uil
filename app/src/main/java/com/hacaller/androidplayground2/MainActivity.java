package com.hacaller.androidplayground2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hacaller.androidplayground2.activities.ActivityAlfa;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_launch1) Button btnLaunch1;
    @BindView(R.id.btn_launch2) Button btnLaunch2;
    @BindView(R.id.btn_launch3) Button btnLaunch3;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.img_test) ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);

        String url = "http://www.riverbottomfarms.com/03e_pat_justchar_col.jpg";
        btnLaunch1.setOnClickListener(new LaunchListener(url));

        url = "http://jellystoneamboy.com/images/y&bb_goin_fishing.jpg";
        btnLaunch2.setOnClickListener(new LaunchListener(url));

        url = "http://www.campjellystone.com/wp/wp-content/uploads/2012/07/grp_11_readingbook_col.jpg";
        btnLaunch3.setOnClickListener(new LaunchListener(url));

        btnNext.setOnClickListener(new NextListener());

    }

    private class LaunchListener implements View.OnClickListener{

        private DisplayImageOptions options;
        private String url;

        public LaunchListener(String url){
            this.url = url;
        }

        @Override
        public void onClick(View v) {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(android.R.drawable.sym_contact_card)
                    .showImageForEmptyUri(android.R.drawable.stat_notify_error)
                    .showImageOnFail(android.R.drawable.star_big_off)
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    //.displayer(new FadeInBitmapDisplayer(300))
                    .build();


            ImageLoader.getInstance().displayImage(url,imageView,options);
        }
    }

    private class NextListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ActivityAlfa.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }


}
