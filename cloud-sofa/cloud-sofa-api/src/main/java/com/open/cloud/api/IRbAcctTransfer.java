package com.open.cloud.api;

import com.open.cloud.api.model.HeadOut;
import com.open.cloud.api.model.RbAcctTransferIn;

public interface IRbAcctTransfer {
    /**
     *账户转账
     **/

    HeadOut rbAcctTransfer(RbAcctTransferIn in);
}
