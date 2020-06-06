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