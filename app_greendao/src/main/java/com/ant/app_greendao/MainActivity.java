package com.ant.app_greendao;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ant.app_base.BaseActivity;
import com.ant.app_greendao.DBManager.DBManager;
import com.ant.app_greendao.dataBean.User;
import com.ant.app_greendao.dataBean.UserSon;
import com.ant.app_utils.LogUtil;
import com.google.gson.Gson;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    User user;
    MyAdapter myAdapter;
    int num = 1;
    private ArrayList<User> datas;


    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        datas = new ArrayList<>();
        myAdapter = new MyAdapter(mContext, R.layout.layout_item, datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myAdapter);

    }


    @OnClick({R.id.load, R.id.save, R.id.insert, R.id.insertOrUpdate, R.id.update, R.id.delete, R.id.loadAll, R.id.deleteAll})
    public void onViewClicked(View view) {

//        LogUtil.e("==================================================================");
        switch (view.getId()) {

            case R.id.load:
                pageIndex++;
                List<User> a = DBManager.getInstance().queryPaging(pageIndex, pageSize);


                datas.addAll(a);
                myAdapter.notifyDataSetChanged();


                ArrayList<User> users1 = new ArrayList<>();

                for (int i = 0; i < datas.size(); i++) {
                    try {
                        BeanUtils.copyProperties(users1, datas);
                        LogUtil.e("msg=====" + users1.toString());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.save:

                pageIndex = 0;
                List<User> d = DBManager.getInstance().queryPaging(pageIndex, pageSize);

                datas.clear();
                datas.addAll(d);
                myAdapter.notifyDataSetChanged();

//                DBManager.getInstance().saveUser(user);

                break;
            case R.id.insert:


                List<User> users = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    User user = new User();
                    user.setRegisrTime(i);
                    user.setUserId("no000" + i);
                    users.add(user);
                }


                DBManager.getInstance().insertOrReplaceInTx(users);

                break;
            case R.id.insertOrUpdate:
                user = new User();

//                UserSon son;
//                son= (UserSon) user;
                user.setUserId("11111" + num);
                num++;
                user.setUserName("李四");
                user.setPassword("323");
//                user.setStudentId(12l);
                user.setRegisrTime(System.currentTimeMillis());
                user.setPhone("32323");

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

                datas.clear();
                datas.addAll(allUserList);
                myAdapter.notifyDataSetChanged();

                UserSon son = new UserSon();

//                ArrayList<UserSon> userSons = new ArrayList<>();

                long currentTimeMillis = System.currentTimeMillis();
//                LogUtil.e("msg=================== " +);
                ArrayList<UserSon> arrayList = modelA2B(allUserList, ArrayList.class);


                LogUtil.e("=================  " + (System.currentTimeMillis() - currentTimeMillis) + "\n" + arrayList.toString());

                if (datas.size() != 0) {

                    long currentTimeMillis1 = System.currentTimeMillis();
                    User user = datas.get(0);
                    UserSon son1 = modelA2B(user, UserSon.class);
                    LogUtil.e("=================  " + (System.currentTimeMillis() - currentTimeMillis1) + " \nson1 == " + son1.toString());

                }

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

    public static <B> B modelA2B(Object modelA, Class<B> bClass) {
        try {
            Gson gson = new Gson();
            String gsonA = gson.toJson(modelA);
            B instanceB = gson.fromJson(gsonA, bClass);

//            Log.d(TAG, "modelA2B A=" + modelA.getClass() + " B=" + bClass + " 转换后=" + instanceB);
            return instanceB;
        } catch (Exception e) {
//            Log.e(TAG, "modelA2B Exception=" + modelA.getClass() + " " + bClass + " " + e.getMessage());
            return null;
        }
    }

}
