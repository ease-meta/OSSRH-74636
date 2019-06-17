package com.open.cloud;

import com.alibaba.fastjson.JSONObject;
import com.alipay.hessian.generic.model.GenericObject;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.runtime.api.client.ReferenceClient;
import com.alipay.sofa.runtime.api.client.param.BindingParam;
import com.alipay.sofa.runtime.api.client.param.ReferenceParam;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class MessageServlet extends HttpServlet {

    @Setter
    private ClientFactoryBean clientFactory;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        long start = 0L;
        response.setContentType("application/json");
        if (log.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }

        try (InputStream in = request.getInputStream(); OutputStream out = response.getOutputStream()) {
            String outMsg = "0000000006999999";

            /*int msglen = request.getContentLength();
            byte[] msg = new byte[msglen];
            int offset = 0;
            int rlen;
            do {
                rlen = in.read(msg, offset, msglen - offset);
                if (rlen > 0) offset += rlen;

            }
            while ((offset != msglen) && (rlen >= 0));

            log.debug("msg:" + new String(msg, "UTF-8"));
*/
            ClientFactoryBean clientFactory = SpringApplicationConfiguration.getBean(ClientFactoryBean.class);
            ReferenceClient referenceClient = clientFactory.getClientFactory().getClient(ReferenceClient.class);
            ReferenceParam<GenericService> referenceParam = new ReferenceParam<GenericService>();
            referenceParam.setInterfaceType(GenericService.class);

            BindingParam refBindingParam = new BoltBindingParam();
            ((BoltBindingParam) refBindingParam).setGenericInterface("com.open.cloud.api.IRbAcctTransfer");
            referenceParam.setBindingParam(refBindingParam);

            GenericObject genericObject = new GenericObject(
                    "com.open.cloud.api.model.RbAcctTransferIn");
            genericObject.putField("reference", "123456");
            genericObject.putField("acctSystem", "555");


            GenericObject result = (GenericObject) referenceClient.reference(referenceParam).$genericInvoke("rbAcctTransfer",
                    new String[]{"com.open.cloud.api.model.RbAcctTransferIn"},
                    new Object[]{genericObject});
            if (result != null) {
                Object json =  JSONObject.toJSON(result);
                log.info("{}",json);
                byte[] retByte = JSONObject.toJSONBytes(json);
                response.setContentLength(retByte.length);
                out.write(retByte);
                out.flush();
            } else {
                out.write("0000000006999999".getBytes());
                out.flush();
            }
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
        } finally {
            if (log.isDebugEnabled()) {
                log.info("交易执行耗时:{}", System.currentTimeMillis() - start);
            }
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
