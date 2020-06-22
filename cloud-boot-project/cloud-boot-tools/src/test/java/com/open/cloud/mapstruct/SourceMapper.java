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
package com.open.cloud.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SourceMapper {

	SourceMapper INSTANCE = Mappers.getMapper(SourceMapper.class);

	@Mapping(source = "totalCount", target = "count")
	@Mapping(source = "subSource", target = "subTarget")
	Target source2target(Source source);

	default SubTarget subSource2subTarget(SubSource subSource) {
		if (subSource == null) {
			return null;
		}
		SubTarget subTarget = new SubTarget();
		subTarget.setResult(!subSource.getDeleted().equals(0));
		subTarget.setName(subSource.getName()==null?"":subSource.getName()+subSource.getName());
		return subTarget;
	}

}