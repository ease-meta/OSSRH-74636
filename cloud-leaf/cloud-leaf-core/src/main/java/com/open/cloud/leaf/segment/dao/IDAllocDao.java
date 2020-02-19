package com.open.cloud.leaf.segment.dao;

import com.open.cloud.leaf.segment.model.LeafAlloc;

import java.util.List;

public interface IDAllocDao {
    List<LeafAlloc> getAllLeafAllocs();

    LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);

    LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);

    List<String> getAllTags();
}
