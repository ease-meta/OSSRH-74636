package com.open.cloud.core.xml;

import java.util.Iterator;

public interface CommonData {
    public abstract String getName();

    public abstract Object getValue();

    public abstract void setValue(Object paramObject);

    public abstract Object getAttribute(String paramString);

    public abstract void setAttribute(String paramString, Object paramObject);

    public abstract Iterator attributeNames();

    public abstract CommonData addChild(String paramString);

    public abstract CommonData getChild(String paramString);

    public abstract CommonData getChild(int paramInt);

    public abstract int childCount();

    public abstract CommonData getParent();
}
