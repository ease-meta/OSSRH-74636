/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.meta.ease.core.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * map扩展，增加类似xpath操作（put和get）、易读的toString、toString按asc排序、toUrlString生成按asc排序的url模式
 *
 * @param <K>
 * @param <V>
 * @author limne
 */
public class FormatHashMap<K, V> extends HashMap<K, V> {
    private static final long serialVersionUID = -1289427183568570036L;
    private StringBuilder prefix = new StringBuilder("  ");

    public FormatHashMap(Map<K, V> map) {
        super(map);
    }

    public FormatHashMap() {
        super();
    }

    public static void main(String[] args) {
        FormatHashMap<String, String> map = new FormatHashMap<>();
        map.put("cest2", "2");
        map.put("best2", "2");
        map.put("test2", "2");
        map.put("test1", "2");
        map.put("atest1", "2");
        map.put("btest1", "2");
        map.put("2test1", "2");
        map.put("Xtest1", "2");
        map.put("a", "2");
        map.put("abc---", "2");
        map.put("abc   ", "2");
        System.out.println(map);
        System.out.println(map.toUrlString());
    }

    public String getPrefix() {
        return prefix.toString();
    }

    public void setPrefix(String prefix) {
        this.prefix.append(prefix);
    }

    @Override
    public V put(K key, V value) {
        if (key.toString().indexOf(".") > 0) {
            String k = key.toString();
            String[] kps = k.split("\\.");
            Map t = this;
            Map n = null;
            for (int i = 0; i < kps.length; i++) {
                if (t.get(kps[i]) == null) {
                    if (i == kps.length - 1) {
                        t.put(kps[i], value);
                    } else {
                        n = new FormatHashMap();
                        t.put(kps[i], n);
                        t = n;
                    }
                } else {
                    if (i == kps.length - 1) {
                        t.put(kps[i], value);
                    } else {
                        t = (Map) t.get(kps[i]);
                    }

                }
            }
            return value;
        } else {
            return super.put(key, value);
        }
    }

    @Override
    public V get(Object key) {
        if (key.toString().indexOf(".") < 0) {
            return super.get(key);
        } else {
            String k = key.toString();
            String[] kps = k.split("\\.");
            Map<K, V> t = this;
            for (int i = 0; i < kps.length; i++) {
                if (t.get(kps[i]) == null) {
                    return null;
                } else {
                    if (i == kps.length - 1) {
                        return t.get(kps[i]);
                    } else {
                        if (t.get(kps[i]) instanceof Map) {
                            t = (Map<K, V>) t.get(kps[i]);
                        } else {
                            return null;
                        }

                    }
                }
            }
        }
        return null;

    }

    public String toString() {
        Map.Entry<K, V>[] sEntries = entrySet().toArray(new Map.Entry[]{});

        sortByKey(sEntries);

        if (sEntries.length == 0)
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append(this.getPrefix().substring(0, this.getPrefix().length() - 1));
        sb.append('{').append('\n');
        for (int i = 0; i < sEntries.length; i++) {
            Map.Entry<K, V> e = sEntries[i];
            K key = e.getKey();
            V value = e.getValue();
            if (value instanceof FormatHashMap) {
                ((FormatHashMap) value).setPrefix(this.getPrefix());
            }
            if (value instanceof List) {
                List l = (List) value;
                for (int m = 0; m < l.size(); m++) {
                    if (l.get(m) instanceof FormatHashMap) {
                        ((FormatHashMap) l.get(m)).setPrefix(this.getPrefix());
                    }
                }
            }
            sb.append(this.getPrefix());
            sb.append(key == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (i == sEntries.length - 1) {
                sb.append('\n')
                        .append(this.getPrefix().substring(0, this.getPrefix().length() - 1))
                        .append('}');
            } else {
                sb.append(',').append('\n');
            }
        }
        return sb.toString();
    }

    public String toUrlString() {
        Map.Entry<K, V>[] sEntries = entrySet().toArray(new Map.Entry[]{});

        sortByKey(sEntries);

        if (sEntries.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(this.getPrefix().substring(0, this.getPrefix().length() - 1));
        for (int i = 0; i < sEntries.length; i++) {
            Map.Entry<K, V> e = sEntries[i];
            K key = e.getKey();
            V value = e.getValue();
            if (value instanceof FormatHashMap) {
                ((FormatHashMap) value).setPrefix(this.getPrefix());
            }
            if (value instanceof List) {
                List l = (List) value;
                for (int m = 0; m < l.size(); m++) {
                    if (l.get(m) instanceof FormatHashMap) {
                        ((FormatHashMap) l.get(m)).setPrefix(this.getPrefix());
                    }
                }
            }
            sb.append(key == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (i != sEntries.length - 1) {
                sb.append('&');
            }
        }
        return sb.toString();
    }

    private void sortByKey(java.util.Map.Entry<K, V>[] sEntries) {

        for (int i = 0; i < sEntries.length - 1; i++) {

            for (int j = i + 1; j < sEntries.length; j++) {
                Map.Entry<K, V> first = sEntries[i];
                String a = first.getKey().toString();
                String b = sEntries[j].getKey().toString();
                if (a.compareTo(b) > 0) {
                    Map.Entry<K, V> temp = first;
                    sEntries[i] = sEntries[j];
                    sEntries[j] = temp;
                }
            }
        }
    }
}
