package com.ant.app_base.user;

import com.ant.app_base.dabaBase.ORMDao;
import com.ant.app_base.dabaBase.table.UserBean;
import com.ant.app_utils.StringUtil;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/8.
 * describe：用户管理累 替换原始的UserSp
 */
public class UserSp {


    private static UserBean userBean;

    private static ORMDao<UserBean> userDao;

    private static ORMDao<UserBean> getUserDao() {
        if (userDao == null) {
            userDao = new ORMDao<>(UserBean.class);
        }
        return userDao;
    }


    /**
     * 获取本地 最新的登陆的用户，如果由多个，取第一个
     * 政策提供给 登陆页面使用
     */
    public static UserBean getLocalLastUser() {
        UserBean userBean = null;

        List<UserBean> query = getUserDao().query(UserDBConfig.columnName_user_now, true);
        if (query == null || query.size() == 0) {
            return null;
        }
        //正常情况下 只会与有一个用户表示为true
        for (int i = 0; i < query.size(); i++) {
            if (i == 0) {
                userBean = query.get(i);
            } else {
                userBean.setIs_new_login(false);
                getUserDao().update(userBean);
            }
        }
        return userBean;
    }


    /**
     * 如果为空 政策情况已经出现 bug\或者乱用
     * 外部地道用时 都必须判空
     */
    public static UserBean getUserBean() {
        //直接内存中 减少不必要操作
        return userBean;
    }

    /**
     * 当用户user_id不一致时，保存，一致时更新
     */
    public static void saveUserBean(UserBean bean) {

        if (bean == null || StringUtil.isEmpty(bean.getUser_id())) {
            return;
        }
        bean.setIs_new_login(true);

        //当前的用户 跟 需要保存的用户 user_id不一致 时，需要更新原始 用户的状态
        if (userBean != null && !userBean.getUser_id().equals(bean.getUser_id())) {
            if (userBean.isIs_new_login()) {
                userBean.setIs_new_login(false);
                userDao.update(userBean);
            }
        }
        userBean = bean;
        UserBean use = getUserDao().queryById(userBean.getUser_id());
        if (use == null) {
            getUserDao().insert(userBean);
        } else {
            getUserDao().update(userBean);
        }
    }

    /**
     * 用户退出登陆时,将 userBean质控
     */
    public static void exitUser() {

        userBean = null;

    }

}
