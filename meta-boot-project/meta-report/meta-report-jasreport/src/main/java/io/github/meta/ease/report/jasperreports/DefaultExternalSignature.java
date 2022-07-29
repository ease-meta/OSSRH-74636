package io.github.meta.ease.report.jasperreports;

import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.itextpdf.text.pdf.security.ExternalSignature;

import java.security.GeneralSecurityException;

/**
 * <p>文件名称:  DefaultExternalSignature</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/7/29</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class DefaultExternalSignature implements ExternalSignature {
    @Override
    public String getHashAlgorithm() {
        return "SHA256";
    }

    @Override
    public String getEncryptionAlgorithm() {
        return "RSA";
    }

    @Override
    public byte[] sign(byte[] message) throws GeneralSecurityException {
        String signMode = getHashAlgorithm() + "with" + getEncryptionAlgorithm();
        Sign sign = SignUtil.sign(SignAlgorithm.valueOf(signMode));
        return sign.sign(message);
    }
}
