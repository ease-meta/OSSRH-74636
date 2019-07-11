package com.open.cloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.common.tracer.core.utils.TracerUtils;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.runtime.api.client.ReferenceClient;
import com.alipay.sofa.runtime.api.client.param.ReferenceParam;
import com.open.cloud.api.model.BaseResponse;
import com.open.cloud.api.model.Head;
import com.open.cloud.api.model.HeadOut;
import com.open.cloud.api.model.Result;
import com.open.cloud.api.model.SuperBaseRequest;
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
        log.info(TracerUtils.getTraceId());
        try (InputStream in = request.getInputStream(); OutputStream out = response.getOutputStream()) {

            String str = RequestUtils.getRequestBody(in);
            str="{\n" +
                    "  \"acctExec\": \"8000\",\n" +
                    "  \"acctExecName\": \"经理4\",\n" +
                    "  \"reference\": \"0\",\n" +
                    "  \"appHead\": {\n" +
                    "    \"currentNum\": \"string\",\n" +
                    "    \"pgupOrPgdn\": \"string\",\n" +
                    "    \"totalFlag\": \"string\",\n" +
                    "    \"totalNum\": \"string\",\n" +
                    "    \"totalRows\": \"string\"\n" +
                    "  },\n" +
                    "  \"branch\": \"01\",\n" +
                    "  \"collatMgrInd\": \"0\",\n" +
                    "  \"contactTel\": \"string\",\n" +
                    "  \"documentId\": \"002\",\n" +
                    "  \"documentType\": \"02\",\n" +
                    "  \"issCountry\": \"ZH\",\n" +
                    "  \"localHead\": {},\n" +
                    "  \"manager\": \"jingli\",\n" +
                    "  \"option\": \"03\",\n" +
                    "  \"profitCentre\": \"string\",\n" +
                    "  \"sysHead\": {\n" +
                    "    \"apprFlag\": \"string\",\n" +
                    "    \"apprUserId\": \"string\",\n" +
                    "    \"authFlag\": \"string\",\n" +
                    "    \"authUserId\": \"string\",\n" +
                    "    \"branchId\": \"01\",\n" +
                    "    \"company\": \"dcits\",\n" +
                    "    \"destBranchNo\": \"string\",\n" +
                    "    \"filePath\": \"string\",\n" +
                    "    \"macValue\": \"string\",\n" +
                    "    \"messageCode\": \"9110\",\n" +
                    "    \"messageType\": \"string\",\n" +
                    "    \"programId\": \"string\",\n" +
                    "    \"reference\": \"string\",\n" +
                    "    \"runDate\": \"string\",\n" +
                    "    \"sceneId\": \"string\",\n" +
                    "    \"seqNo\": \"string\",\n" +
                    "    \"serviceCode\": \"1200\",\n" +
                    "    \"sourceBranchNo\": \"string\",\n" +
                    "    \"sourceType\": \"string\",\n" +
                    "    \"systemId\": \"string\",\n" +
                    "    \"threadNo\": \"string\",\n" +
                    "    \"tranDate\": \"string\",\n" +
                    "    \"tranMode\": \"string\",\n" +
                    "    \"tranTimestamp\": \"string\",\n" +
                    "    \"userId\": \"string\",\n" +
                    "    \"userLang\": \"string\"\n" +
                    "  }\n" +
                    "}";
            log.info("{}", str);
            Head head = new Head();
            head.setApprFlag("1");
            JSONObject jsonObject = JSON.parseObject(str);
            log.info("{}", jsonObject);

            SuperBaseRequest object = (SuperBaseRequest) JSONObject.parseObject(str, Class.forName("com.open.cloud.api.model.RbAcctTransferIn"));
            object.setHead(head);
            log.info("{}", object);
            log.info("{}", object.getHead());
            String outMsg = "0000000006999999";

            ClientFactoryBean clientFactory = SpringApplicationConfiguration.getBean(ClientFactoryBean.class);
            ReferenceClient referenceClient = clientFactory.getClientFactory().getClient(ReferenceClient.class);

            ReferenceParam<GenericService> referenceParam = new ReferenceParam<GenericService>();
            referenceParam.setInterfaceType(GenericService.class);

            BoltBindingParam refBindingParam = new BoltBindingParam();
            ((BoltBindingParam) refBindingParam).setGenericInterface("com.open.cloud.api.IRbAcctTransfer");
            referenceParam.setBindingParam(refBindingParam);
            referenceParam.setJvmFirst(false);
/*

            GenericObject genericObject = new GenericObject(
                    "com.open.cloud.api.model.RbAcctTransferIn");
            genericObject.putField("reference", "123456");
            genericObject.putField("acctSystem", "555");
            genericObject.putField("transId","nihao");
*/

            BaseResponse result = (BaseResponse) referenceClient.reference(referenceParam).$genericInvoke("rbAcctTransfer",
                    new String[]{"com.open.cloud.api.model.RbAcctTransferIn"},
                    new Object[]{object},Class.forName("com.open.cloud.api.model.RbAcctTransferOut"));

            if (result != null) {
                Object json = JSONObject.toJSON(result);
                log.info("{}", json);
                byte[] retByte = JSONObject.toJSONBytes(json);
                response.setContentLength(retByte.length);
                out.write(retByte);
                out.flush();
            } else {
                Result result1 = new Result("9999", "交易异常");
                HeadOut headOut = new HeadOut("S", result1);
                Object json = JSONObject.toJSON(headOut);
                out.write(JSONObject.toJSONBytes(json));
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
