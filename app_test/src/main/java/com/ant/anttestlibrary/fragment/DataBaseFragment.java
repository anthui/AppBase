package com.ant.anttestlibrary.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ant.anttestlibrary.DB.ORMDao;
import com.ant.anttestlibrary.DB.table.UserBean;
import com.ant.anttestlibrary.R;
import com.ant.app_base.AppExceptionHelper;
import com.ant.app_base.BaseFragment;
import com.ant.app_utils.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/8.
 * describe：
 */
public class DataBaseFragment extends BaseFragment {

    ORMDao<UserBean> userDao;
    private TextView tvContent;


    int nameNum = 1;

    @Override
    public void initComponents(Bundle savedInstanceState, View view) {


        userDao = new ORMDao<UserBean>(UserBean.class);

        tvContent = view.findViewById(R.id.tv_content);
        view.findViewById(R.id.tv_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserBean userBean = new UserBean();

                userBean.setCountry("中国");
                userBean.setNickname("哈哈" + nameNum);
                nameNum++;
                userBean.setTelephone(System.currentTimeMillis() + "");

                long l = System.currentTimeMillis();

                int insert = userDao.insert(userBean);
                loge("插入时间=========" + insert);


            }
        });


        view.findViewById(R.id.tv_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long l = System.currentTimeMillis();
                UserBean userBean1 = userDao.queryById(1);

                long l1 = System.currentTimeMillis();
                LogUtil.e("查询时间：" + (l1 - l));

                if (userBean1 != null) {

                    userBean1.setNickname("000000000000000");
                    userDao.update(userBean1);
                    LogUtil.e("修改时间：" + (System.currentTimeMillis() - l1));


                }


//                List<UserBean> query = userDao.query("nikename", "哈哈3");
//
//                if (query == null) {
//                    loge("数据空==========");
//                    return;
//                }
//                String msg = "原始数据" + query.toString();
//                for (UserBean userBean : query) {
//
//                    userBean.setNickname("啦啦啦啦啦");
//
//                    userDao.update(userBean);
//
//
//
//                }
//
//                List<UserBean> userBeans = userDao.queryForAll();
//
//                tvContent.setText("新数据===" + userBeans + "\n" + msg);
//
//
//            }


            }

        });


        view.findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<String> strings = null;
                strings.add("=================");

//                for (int i = 0; i < 30; i++) {
//                    ArrayList<Object> objects;
//                    objects.add()
//                }

            }
        });

        view.findViewById(R.id.tv_chaxun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initData();

                List<UserBean> userBeans = userDao.queryForAll();
                if (userBeans != null) {


                    tvContent.setText(userBeans.toString() + "\n=========================================");
                } else {
                    tvContent.setText("空数据");
                }


            }
        });
    }

    @Override
    public void initData() {


        File logFile = AppExceptionHelper.getInstance().getLogFile();


        File[] allLogFile = AppExceptionHelper.getInstance().getAllLogFile();


        char[] buf = new char[1024];

        StringBuilder stringBuilder = new StringBuilder();

        if (allLogFile != null) {

            stringBuilder.append("\n文件数量===" + allLogFile.length);


        } else {
            stringBuilder.append("\n文件数量0");

        }
        FileReader fileReader = null;
//        if (allLogFile != null) {
//            for (int i = 0; i < allLogFile.length; i++) {
        File file = logFile;
        if (file == null) {
            return;
        }

        stringBuilder.append("\n文件地址" + file.getAbsolutePath());
        stringBuilder.append("\n文件大小" + file.length());


        try {
            fileReader = new FileReader(file);

            int num = 0;
            while ((num = fileReader.read(buf)) != -1) //读取文件并把它存入buf中，用num返回读到字符的个数，一直读到结尾
            {
//                        System.out.print((new String(buf,0,num)));//字符数组里仍有空白没有读入的位置，所以不要了
                String str = new String(buf, 0, num);
                LogUtil.e(str);
                stringBuilder.append(str);
                //new String(字符串，开始位置，结尾位置)
            }

            stringBuilder.append("\n\n");
//                    int read = fileReader.read(buf);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//            }

//        }

        tvContent.setText("错误信息：\n" + stringBuilder.toString());
    }


    @Override
    public int getMainContentViewId() {
        return R.layout.fragment_db;
    }
}
