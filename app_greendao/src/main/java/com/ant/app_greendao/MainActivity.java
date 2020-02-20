package com.ant.app_greendao;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ant.app_base.BaseActivity;
import com.ant.app_greendao.DBManager.DBManager;
import com.ant.app_greendao.dataBean.Student;
import com.ant.app_greendao.dataBean.User;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.insert)
    TextView insert;
    @BindView(R.id.insertOrUpdate)
    TextView insertOrUpdate;
    @BindView(R.id.update)
    TextView update;
    @BindView(R.id.delete)
    TextView delete;
    @BindView(R.id.loadAll)
    TextView loadAll;
    @BindView(R.id.deleteAll)
    TextView deleteAll;
    @BindView(R.id.tv_content)
    TextView tvContent;

    User user;

    int num = 1;

    @OnClick({R.id.save, R.id.insert, R.id.insertOrUpdate, R.id.update, R.id.delete, R.id.loadAll, R.id.deleteAll, R.id.tv_content})
    public void onViewClicked(View view) {

//        LogUtil.e("==================================================================");
        switch (view.getId()) {
            case R.id.save:
                user = new User();
                num++;
                user.setUserId("uid23333333333333001" + num);
                user.setUserName("张三");
                user.setPassword("222");
                user.setRegisrTime(System.currentTimeMillis());
                user.setPhone("21212112");

//                DBManager.getInstance().saveUser(user);

                break;
            case R.id.insert:

//                if (user != null) {
//                    user.setUserName("000000000000000000000000000");
////                    user.setUserId("uid2001" + num);
//                }
//                DBManager.getInstance().insertOrUpdateUser(user);

                break;
            case R.id.insertOrUpdate:
                user = new User();
                user.setUserId("uid2001" + num);
                num++;
                user.setUserName("李四");
                user.setPassword("323");
//                user.setStudentId(12l);
                user.setRegisrTime(System.currentTimeMillis());
                user.setPhone("32323");
                Student student = new Student();
                student.setName(11);
                student.setId(1122l);
                user.setStudent(student);
                student.setAddress("hhahahhahhhhhhhhhhhhhhh");

                DBManager.getInstance().insertOrUpdateUser(user);




//                DBManager.getInstance().insertOrUpdateStudent(student);

                break;
            case R.id.update:


                if (user != null) {
                    user.setUserName("修改后的名字");
                }
                DBManager.getInstance().update(user);
//                DBManager.getInstance().saveUser(user);

                break;
            case R.id.delete:

                if (user != null) {
                    DBManager.getInstance().deleteUser(user);
                }
                user = null;

                break;
            case R.id.loadAll:

                List<User> allUserList = DBManager.getInstance().loadAllUserList();

                if (allUserList == null) {

                    tvContent.setText("null");
                    return;
                }

                if (allUserList.size() == 0) {
                    tvContent.setText("0");
                    return;
                }
                tvContent.setText(allUserList.toString());


                break;
            case R.id.deleteAll:
                DBManager.getInstance().deleteAllUser();
                break;

        }
    }

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

    }
}
