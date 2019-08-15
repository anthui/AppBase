package com.ant.antfiction.utils.version;


import android.content.Context;
import androidx.fragment.app.FragmentManager;

import com.ant.app_base.http.HttpManager;
import com.ant.app_home.R;
import com.ant.app_http.Bean.ErrBean;
import com.ant.app_http.Bean.TokenInfoBean;
import com.ant.app_http.listener.OnHttpRequestListener;
import com.ant.app_utils.AppHelper;
import com.ant.app_utils.PermissionsHelper;
import com.ant.app_utils.ToastUtil;
import com.ant.app_views.EmptyView;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/1/21.
 * describe：
 */
public class NewVersionHelper {

    PermissionsHelper permissionsHelper;
    Context mContext;
    EmptyView emptyView;
    public NewVersionDialog newVersionDialog;


    public NewVersionHelper(Context context, EmptyView emptyView, PermissionsHelper permissionsHelper) {

        mContext = context;
        this.emptyView = emptyView;
        this.permissionsHelper = permissionsHelper;
    }


    public void checkNewVersion(final FragmentManager fragmentManager, final boolean toast) {
        if (toast) {
            emptyView.setState(EmptyView.STATE_LOADING);
        }
        HttpManager.getHttpAction().getCurrentVersion(new OnHttpRequestListener<VersionBean>() {

            @Override
            public void onSuccess(VersionBean versionBean, TokenInfoBean tokenInfoBean) {
                String version = AppHelper.getVersionName(mContext);
                emptyView.setState(EmptyView.STATE_NONE);

                String currenVersionCode = "";
                String[] split = version.split("\\.");
                for (String s : split) {
                    currenVersionCode = currenVersionCode + s;
                }

                String serviceVersionCode = "";
                for (String s : versionBean.getVersion().split("\\.")) {
                    serviceVersionCode = serviceVersionCode + s;
                }

                try {
                    if (Integer.parseInt(currenVersionCode) < Integer.parseInt(serviceVersionCode)) {

                        newVersionDialog = new NewVersionDialog();
                        newVersionDialog.setData(versionBean, permissionsHelper);
                        newVersionDialog.show(fragmentManager);
                    } else {
                        if (toast) {
//                            mContext.getshowToast(getString(R.string.str_news_version));
                            ToastUtil.showToast(mContext, mContext.getString(R.string.str_news_version));
                        }
                    }
                } catch (Exception e) {
                    //   initLoad(null);
                }
            }

            @Override
            public void onFailure(ErrBean errBean) {
                emptyView.setState(EmptyView.STATE_NONE);

            }
        });
    }


}
