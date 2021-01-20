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
package com.open.cloud.jackson.databind.ser.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.std.MapProperty;
import org.junit.Test;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings("serial")
public class TestMapFiltering {
	final ObjectMapper MAPPER = new ObjectMapper();

	@Test
	public void testMapFilteringViaProps() throws Exception {
		FilterProvider prov = new SimpleFilterProvider().addFilter("filterX",
				SimpleBeanPropertyFilter.filterOutAllExcept("b"));
		String json = MAPPER.writer(prov).writeValueAsString(new MapBean());
		System.out.println(json);
	}

	@Test
	public void testMapFilteringViaClass() throws Exception {
		FilteredBean bean = new FilteredBean();
		bean.put("a", 4);
		bean.put("b", 3);
		FilterProvider prov = new SimpleFilterProvider().addFilter("filterForMaps",
				SimpleBeanPropertyFilter.filterOutAllExcept("b"));
		String json = MAPPER.writer(prov).writeValueAsString(bean);
		System.out.println(json);
	}

	// [databind#527]
	@Test
	public void testNonNullValueMapViaProp() throws IOException {
		String json = MAPPER.writeValueAsString(new NoNullValuesMapContainer().add("a", "foo")
				.add("b", null).add("c", "bar"));
		System.out.println(json);
	}

	// [databind#522]
	@Test
	public void testMapFilteringWithAnnotations() throws Exception {
		FilterProvider prov = new SimpleFilterProvider().addFilter("filterX", new TestMapFilter());
		String json = MAPPER.writer(prov).writeValueAsString(new MapBean());
		// a=1 should become a=2
		System.out.println(json);

		// and then one without annotation as contrast
		json = MAPPER.writer(prov).writeValueAsString(new MapBeanNoOffset());
		System.out.println(json);
	}

	// [databind#527]
	@Test
	public void testMapNonNullValue() throws IOException {
		String json = MAPPER.writeValueAsString(new NoNullsStringMap().add("a", "foo")
				.add("b", null).add("c", "bar"));
		System.out.println(json);
	}

	// [databind#527]
	@Test
	public void testMapNonEmptyValue() throws IOException {
		String json = MAPPER.writeValueAsString(new NoEmptyStringsMap().add("a", "foo")
				.add("b", "bar").add("c", ""));
		System.out.println(json);
	}

	// Test to ensure absent content of AtomicReference handled properly
	// [databind#527]
	@Test
	public void testMapAbsentValue() throws IOException {
		String json = MAPPER.writeValueAsString(new NoAbsentStringMap().add("a", "foo").add("b",
				null));
		System.out.println(json);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testMapNullSerialization() throws IOException {
		ObjectMapper m = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", null);
		// by default, should output null-valued entries:
		assertEquals("{\"a\":null}", m.writeValueAsString(map));
		// but not if explicitly asked not to (note: config value is dynamic here)

		m = new ObjectMapper();
		m.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		assertEquals("{}", m.writeValueAsString(map));
	}

	// [databind#527]
	@Test
	public void testMapWithOnlyEmptyValues() throws IOException {
		String json;

		// First, non empty:
		json = MAPPER.writeValueAsString(new Wrapper497(new StringMap497().add("a", "123")));
		System.out.println(json);

		// then empty
		json = MAPPER.writeValueAsString(new Wrapper497(new StringMap497().add("a", "").add("b",
				null)));
		System.out.println(json);
	}

	@Test
	public void testMapViaGlobalNonEmpty() throws Exception {
		// basic Map<String,String> subclass:
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDefaultPropertyInclusion(JsonInclude.Value.empty().withContentInclusion(
				JsonInclude.Include.NON_EMPTY));
		System.out
				.println(mapper.writeValueAsString(new StringMap497().add("x", "").add("a", "b")));
	}

    /*
    /**********************************************************
    /* Unit tests
    /**********************************************************
     */

	@Test
	public void testMapViaTypeOverride() throws Exception {
		// basic Map<String,String> subclass:
		ObjectMapper mapper = new ObjectMapper();
		mapper.configOverride(Map.class).setInclude(
				JsonInclude.Value.empty().withContentInclusion(JsonInclude.Include.NON_EMPTY));
		System.out.println(mapper.writeValueAsString(new StringMap497().add("foo", "")
				.add("a", "b")));
	}

	@Target({ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CustomOffset {
		public int value();
	}

	@JsonFilter("filterForMaps")
	static class FilteredBean extends LinkedHashMap<String, Integer> {
	}

	static class MapBean {
		@JsonFilter("filterX")
		@CustomOffset(1)
		public Map<String, Integer> values;

		public MapBean() {
			values = new LinkedHashMap<String, Integer>();
			values.put("a", 1);
			values.put("b", 5);
			values.put("c", 9);
		}
	}

	static class MapBeanNoOffset {
		@JsonFilter("filterX")
		public Map<String, Integer> values;

		public MapBeanNoOffset() {
			values = new LinkedHashMap<String, Integer>();
			values.put("a", 1);
			values.put("b", 2);
			values.put("c", 3);
		}
	}

	static class TestMapFilter implements PropertyFilter {
		@Override
		public void serializeAsField(Object bean, JsonGenerator g, SerializerProvider provider,
									 PropertyWriter writer) throws Exception {
			String name = writer.getName();

			// sanity checks
			assertNotNull(writer.getType());
			assertEquals(name, writer.getFullName().getSimpleName());

			if (!"a".equals(name)) {
				return;
			}
			CustomOffset n = writer.findAnnotation(CustomOffset.class);
			int offset = (n == null) ? 0 : n.value();

			// 12-Jun-2017, tatu: With 2.9, `value` is the surrounding POJO, so
			//    need to do casting
			MapProperty prop = (MapProperty) writer;
			Integer old = (Integer) prop.getValue();
			prop.setValue(Integer.valueOf(offset + old.intValue()));

			writer.serializeAsField(bean, g, provider);
		}

		@Override
		public void serializeAsElement(Object elementValue, JsonGenerator jgen,
									   SerializerProvider prov, PropertyWriter writer)
				throws Exception {
			// not needed for testing
		}

		@Override
		@Deprecated
		public void depositSchemaProperty(PropertyWriter writer, ObjectNode propertiesNode,
										  SerializerProvider provider) throws JsonMappingException {
		}

		@Override
		public void depositSchemaProperty(PropertyWriter writer,
										  JsonObjectFormatVisitor objectVisitor,
										  SerializerProvider provider) throws JsonMappingException {
		}
	}

	// [databind#527]
	static class NoNullValuesMapContainer {
		@JsonInclude(content = JsonInclude.Include.NON_NULL)
		public Map<String, String> stuff = new LinkedHashMap<String, String>();

		public NoNullValuesMapContainer add(String key, String value) {
			stuff.put(key, value);
			return this;
		}
	}

	// [databind#527]
	@JsonInclude(content = JsonInclude.Include.NON_NULL)
	static class NoNullsStringMap extends LinkedHashMap<String, String> {
		public NoNullsStringMap add(String key, String value) {
			put(key, value);
			return this;
		}
	}

	// [databind#527]
	@JsonInclude(content = JsonInclude.Include.NON_ABSENT)
	static class NoAbsentStringMap extends LinkedHashMap<String, AtomicReference<?>> {
		public NoAbsentStringMap add(String key, Object value) {
			put(key, new AtomicReference<Object>(value));
			return this;
		}
	}

	// [databind#527]
	@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
	static class NoEmptyStringsMap extends LinkedHashMap<String, String> {
		public NoEmptyStringsMap add(String key, String value) {
			put(key, value);
			return this;
		}
	}

	// [databind#497]: both Map AND contents excluded if empty
	static class Wrapper497 {
		@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_EMPTY)
		public StringMap497 values;

		public Wrapper497(StringMap497 v) {
			values = v;
		}
	}

	static class StringMap497 extends LinkedHashMap<String, String> {
		public StringMap497 add(String key, String value) {
			put(key, value);
			return this;
		}
	}
}
