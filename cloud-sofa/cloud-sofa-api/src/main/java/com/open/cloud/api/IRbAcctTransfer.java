package com.open.cloud.api;

import com.open.cloud.api.model.RbAcctTransferIn;
import com.open.cloud.api.model.RbAcctTransferOut;

public interface IRbAcctTransfer {
    /**
     *账户转账
     **/

    RbAcctTransferOut rbAcctTransfer(RbAcctTransferIn in);
}
