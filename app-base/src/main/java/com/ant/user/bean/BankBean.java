package com.ant.user.bean;

import com.ant.base.BaseBean;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/8/27
 * describe：
 */
public class BankBean extends BaseBean {

    private List<BankAccountListBean> bankAccountList;

    public List<BankAccountListBean> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccountListBean> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    public static class BankAccountListBean {
        /**
         * open_bank : 工商银行
         * user_bank_account_id : 00001
         * account : 538526853355563355
         * account_name : 155把
         * bank_id :
         */

        private String open_bank;
        private String user_bank_account_id;
        private String account;
        private String account_name;
        private String bank_id;

        public String getOpen_bank() {
            return open_bank;
        }

        public void setOpen_bank(String open_bank) {
            this.open_bank = open_bank;
        }

        public String getUser_bank_account_id() {
            return user_bank_account_id;
        }

        public void setUser_bank_account_id(String user_bank_account_id) {
            this.user_bank_account_id = user_bank_account_id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

    }
}
