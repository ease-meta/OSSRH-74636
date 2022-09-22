package io.github.meta.ease.crypto.shuzihanzheng;

public class CommonUtils {
    public static String getAccountantPublicKeyUrl(String applyUrl) {
        String getPublicKeyUrl = null;
        if (applyUrl.contains("://")) {
            String[] applyUrlArr = applyUrl.split("://");
            String applyUrlStr2 = applyUrlArr[1];
            String ipAndPort = applyUrlStr2.substring(0, applyUrlStr2.indexOf("/"));
            if (ipAndPort != null && !"".equals(ipAndPort)) {
                getPublicKeyUrl = String.valueOf(applyUrlArr[0]) + "://" + ipAndPort + "/bpbcapi/v1/confirmation/sCode";
            }
        }
        return getPublicKeyUrl;
    }
}
