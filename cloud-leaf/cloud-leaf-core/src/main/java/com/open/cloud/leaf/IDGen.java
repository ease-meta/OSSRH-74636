package com.open.cloud.leaf;


import com.open.cloud.leaf.core.common.Result;

public interface IDGen {
	Result get(String key);

	boolean init();
}
