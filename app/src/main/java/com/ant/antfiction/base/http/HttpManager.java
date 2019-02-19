package com.ant.antfiction.base.http;


import com.ant.antfiction.utils.version.VersionBean;
import com.ant.http.BaseHttpAction;
import com.ant.http.listener.OnHttpRequestListener;
import com.ant.utils.bean.ShareBean;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/7/19
 * describe： 网络请求 单利模式
 */
@SuppressWarnings("unchecked")
public class HttpManager extends BaseHttpAction {
    private static HttpManager httpManager;

    PostApiService postApiService;

    private HttpManager() {
        super(HttpConfig.HttpConnectionTime, HttpConfig.getHttpBaseUrl(), HttpConfig.isDebug);
        postApiService = retrofit.create(PostApiService.class);
    }

    public static HttpManager getHttpAction() {
        if (httpManager == null)
            synchronized (HttpManager.class) {
                if (httpManager == null)
                    httpManager = new HttpManager();
            }
        return httpManager;
    }

    public void getCurrentVersion(OnHttpRequestListener<VersionBean> onHttpRequestListener) {
        enqueue(postApiService.getCurrentVersion("1"), onHttpRequestListener);

    }

//    public void getWalletList(OnHttpRequestListener<WalletBean> onHttpRequestListener) {
//
//        enqueue(postApiService.getWalletList(), onHttpRequestListener);
//    }
//
//    public void createWallet(String symbol, OnHttpRequestListener<WalletBean> onHttpRequestListener) {
//
//        enqueue(postApiService.createWallet(symbol), onHttpRequestListener);
//    }
//
//    public void getTotalWalletList(OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getTotalWalletList(), onHttpRequestListener);
//
//
//    }
//
//    public void hide(String symbol, OnHttpRequestListener<WalletBean> onHttpRequestListener) {
//        enqueue(postApiService.hide(symbol), onHttpRequestListener);
//
//    }

    public void upClientId(String clientid, OnHttpRequestListener onHttpRequestListener) {
        enqueue(postApiService.upClientId(clientid), onHttpRequestListener);

    }

//    public void getAsseFree(String symbo, OnHttpRequestListener onHttpRequestListener) {
//
//        enqueue(postApiService.getAsseFree(symbo), onHttpRequestListener);
//
//    }
//
//    //
//    public void transCheck(HashMap<String, String> hashMap, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.transCheck(hashMap), onHttpRequestListener);
//
//    }
//
//
//    public void transPay(HashMap hashMap, OnHttpRequestListener onHttpRequestListener) {
//
//        enqueue(postApiService.transPay(hashMap), onHttpRequestListener);
//
//    }
//
//    public void getTransActionDetail(String transaction_sn, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getTransActionDetail(transaction_sn), onHttpRequestListener);
//
//    }
//
//    public void getTransActionList(HashMap<String, String> hashMap, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getTransActionList(hashMap), onHttpRequestListener);
//
//    }
//
//    public void getNotifyList(int page, OnHttpRequestListener onHttpRequestListener) {
//
//        enqueue(postApiService.getNotifyList(page), onHttpRequestListener);
//
//    }
//

//
//    public void gerMineralList(OnHttpRequestListener paramOnHttpRequestListener) {
//        enqueue(this.postApiService.gerMineralList(), paramOnHttpRequestListener);
//
//    }
//
//    public void getMining(String paramString1, String paramString2, OnHttpRequestListener paramOnHttpRequestListener) {
//        enqueue(this.postApiService.getMining(paramString1, paramString2), paramOnHttpRequestListener);
//    }

    public void getNewMineral(String paramString, OnHttpRequestListener<ShareBean> paramOnHttpRequestListener) {
        enqueue(this.postApiService.getNewMineral(paramString), paramOnHttpRequestListener);
    }

    public void readMessage(String paramString, OnHttpRequestListener onHttpRequestListener) {
        enqueue(this.postApiService.readMessage(paramString), onHttpRequestListener);

    }

//    public void getShareUrl(OnHttpRequestListener onHttpRequestListener) {
//        enqueue(this.postApiService.getShareUrl(), onHttpRequestListener);
//
//    }
//
//    public void getMessageNum(OnHttpRequestListener onHttpRequestListener) {
//        enqueue(this.postApiService.getMessageNum(), onHttpRequestListener);
//
//    }
//
//    public void getFlashList(int page, int type, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getFlashList(page, type), onHttpRequestListener);
//
//
//    }
//
//    public void getBili(String symbol, OnHttpRequestListener biLiListener) {
//        enqueue(postApiService.getBili(symbol), biLiListener);
//
//    }
//
//    public void getFlashTransList(int pageIndex, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getFlashTransList(pageIndex), onHttpRequestListener);
//
//
//    }
//
//    public void buyOrSell(HashMap<String, String> hashMap, OnHttpRequestListener<Object> onHttpRequestListener) {
//        enqueue(postApiService.buyOrSell(hashMap), onHttpRequestListener);
//
//    }
//
//    public void getPendingList(int pageIndex, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getPendingList(pageIndex), onHttpRequestListener);
//
//    }

    public void cancle(String id, OnHttpRequestListener onHttpRequestListener) {
        enqueue(postApiService.cancle(id), onHttpRequestListener);


    }

    public void exChange(String num, String id, OnHttpRequestListener onHttpRequestListener) {
        enqueue(postApiService.exChange(num, id), onHttpRequestListener);

    }

//    public void getExchangeDetail(String stringExtra, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getExchangeDetail(stringExtra), onHttpRequestListener);
//
//    }
//
//    public void onCheckIn(OnHttpRequestListener onCheckInListener) {
//        enqueue(postApiService.onCheckIn(), onCheckInListener);
//
//
//    }
//
//    public void horsepowerInit(OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.horsepowerInit(), onHttpRequestListener);
//
//
//    }
//
//    public void finishTask(HashMap<String, String> type, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.finishTask(type), onHttpRequestListener);
//
//
//    }
//
//
//    public void submitVerification(HashMap<String, String> hashMap, OnHttpRequestListener onHttpRequestListener) {
//
//        enqueue(postApiService.submitVerification(hashMap), onHttpRequestListener);
//
//    }
//
//    public void userInfoInit(OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.userInfoInit(), onHttpRequestListener);
//
//
//    }
//
//    public void getList(int pageIndex, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getList(pageIndex), onHttpRequestListener);
//
//
//    }
//
//    //邀请妖后
//    public void getShare(OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getShare(), onHttpRequestListener);
//
//
//    }
//
//    public void getReward(int pageIndex, OnHttpRequestListener onHttpRequestListener) {
//        enqueue(postApiService.getReward(pageIndex), onHttpRequestListener);
//
//
//    }
//
//    public void getAdList(int page, int pageType, OnHttpRequestListener onHttpRequestListener) {
//
//        if (pageType == 0) {
//            enqueue(postApiService.getAdList(page), onHttpRequestListener);
//        } else {
//            enqueue(postApiService.getZbList(page), onHttpRequestListener);
//
//        }
//    }
//
//    public void getTeamList(int page, int type, OnHttpRequestListener onHttpRequestListener) {
//
//        if (type == 2) {
//            enqueue(postApiService.getTeamList(HttpConfig.GETE_TEAM_LIST, page), onHttpRequestListener);
//
//        } else {
//
//            enqueue(postApiService.getTeamList(HttpConfig.GETE_COMPANY_LIST, page), onHttpRequestListener);
//        }
//    }
//
//    public void getTeamDetail(String id, int pageType, OnHttpRequestListener<Object> onHttpRequestListener) {
//        enqueue(postApiService.getTeamDetail(id, pageType), onHttpRequestListener);
//    }


}
