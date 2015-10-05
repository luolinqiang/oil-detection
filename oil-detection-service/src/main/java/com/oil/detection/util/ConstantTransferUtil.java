package com.oil.detection.util;

public class ConstantTransferUtil {
    public static String purchaseType(int type) {
        if (type == 1) {
            return "采购需求";
        }
        if (type == 2) {
            return "代采申请";
        }
        return "";
    }

    public static String purchaseState(int type) {
        if (type == 1) {
            return "审核中";
        }
        if (type == 2) {
            return "审核通过";
        }
        return "";
    }
}
