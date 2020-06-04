package com.ant.app_utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.ant.app_base.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: 字符串工具类         </p>
 * <p>Description: </p>
 * <p>包括如下功能：</p>
 * <p>1. 判断字符串是否为空</p>
 * <p>2. 判断是否手机号码</p>
 * <p>3. 判断密码是否可用</p>
 * <p>4. 比较字符串是否相同</p>
 * <p>5. 判断是否为纯数字</p>
 * <p>6. HTML 编码</p>
 * <p>7. String转拼音</p>
 */
public class StringUtil {

    public static String join(Collection<String> s, String delimiter) {
        if (s.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (String str : s) {
            sb.append(str).append(delimiter);
        }
        if (sb.length() > 0)
            sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    /**
     * 是否手机号码
     * @param mobiles
     * @return
     */
	public static boolean isMobile(String mobiles) {
		if (StringUtil.isEmpty(mobiles)) {
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

    /**
     * 密码是否可用
     * 密码最长为20位    panxd
     *
     * @param pwd
     * @return
     */
    public static boolean isPwdValid(String pwd) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]{0,20}");
        Matcher m = p.matcher(pwd);

        return m.matches();
    }

    /**
     * 比较字符串是否相同
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    /**
     * Returns whether the given CharSequence contains only digits
     *
     * @param str
     * @return
     */
    public static boolean isDigitsOnly(CharSequence str) {
        return TextUtils.isDigitsOnly(str);
    }

    /**
     * html encode
     *
     * @param s
     * @return
     */
    public static String htmlEncode(String s) {
        return TextUtils.htmlEncode(s);
    }

    /**
     * 由全角转半角
     *
     * @param
     * @return
     */
    public static String toSBC(String s) {
        if (StringUtil.isEmpty(s)) {
            return "";
        }
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127 && c[i] > 32)
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
    }

    /**
     * 将所有的数字、字母及标点全部转为全角字符，使它们与汉字同占两个字节
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }


    /**
     * 是否为拼音字符串
     *
     * @param str
     * @return
     */
    public static boolean isPinYin(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean containCn(String str) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        return pattern.matcher(str).find();
    }

    /**
     * 特殊字符编码
     */
    public static String encodeUnicode(final String gbString) {
        String encode = null;
        try {
            encode = URLEncoder.encode(gbString, "UTF-8");//进行utf-8编码
            return encode;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return gbString;
    }


    /**
     * 设置价格 格式化
     */
    public static String formarPrice(Double price) {
        //    LogUtil.e("formarPrice" + price);


        try {
            if (!price.toString().contains(".")) {
                return price + "";
            }
            //     String format1 = new DecimalFormat("#.###").format(3.14);

            String format = new DecimalFormat("#.##").format(price);
            //   LogUtil.e("formarPrice  原始==" + price + "    ==" + format + "     sss====" + Double.parseDouble(format));
            return format;

        } catch (Exception e) {
            return price + "";
        }


    }

    public static void copyMessage(Context mcontext, String txt) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) mcontext.getSystemService(Context.CLIPBOARD_SERVICE);

// 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
        ClipData clipData = ClipData.newPlainText(null, txt);

// 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
        ToastUtil.showCenterToast(mcontext, mcontext.getString(R.string.str_copy_success), true);
    }

    public static void setAddressText(TextView tvAddress, String address) {
        if (tvAddress == null || isEmpty(address)) {
            return;
        }

        if (address.length() < 15) {
            tvAddress.setText(address);
        } else {
            String s = address.substring(0, 5) + "..." + address.substring(address.length() - 7, address.length());
            tvAddress.setText(s);
        }
    }
}
