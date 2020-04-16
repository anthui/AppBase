package com.ant.modul.glide;

import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/8.
 * describe：
 */
public class GlideTestActivity extends BaseActivity {
    private TextView tvContent;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_glide;
    }

    @Override
    public void initData() {


    }

    ImageView ivPic;
    ImageView ivPic2;
    ImageView ivPic3;

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        ivPic = findViewById(R.id.iv_pic);
        ivPic2 = findViewById(R.id.iv_pic2);
        ivPic3 = findViewById(R.id.iv_pic3);
        tvContent = findViewById(R.id.tv_content);

        ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPic();
            }
        });
    }

    private void loadPic() {

        BackgroundColorSpan
//        Glide.with(this).clear(ivPic);

//        String data1 = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1586827509&di=e0beddee252fd2a9c2618615bca6f333&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg";
//        String data = "https://ss.bscstorage.com/miguan/qutianqi/%E5%8A%A0%E5%AF%86%E6%96%87%E4%BB%B6.jpg";
        //非加密文件 2M
        String data = "https://ss.bscstorage.com/xiyou/pro-img-xiyou/20191031/15724815701325.jpg";
        //加密文件 2M
//        String data = "https://xiyou.sc.diyixin.com/pro-img-xiyou/20191031/15869355437033.jpg";

        Photo photo = new Photo(data);
//        photo.setUrl(d

//        GlideApp.with(this).load(new Photo(data1)).error(R.mipmap.bg_ad).into(ivPic);
        GlideApp.with(this).load(photo).error(R.mipmap.bg_ad).into(ivPic);

//        GlideApp.with(this).load(data).error(R.mipmap.bg_ad).into(ivPic3);

//        Glide.with(this).load(data).error()

//        try {
//            InputStream open = getAssets().open("ic_glide.jpg");
//            byte[] data = input2byte(open);
//
//            tvContent.setText(new String(data));
//            byte[] bytes = Arrays.copyOfRange(data, 6, data.length);
//            data = null;
//            LogUtil.e("msg== " + new String(bytes));
//            Glide.with(this).load(data).error(R.mipmap.bg_ad).into(ivPic);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // 将输入流解析成字节数组
    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}
