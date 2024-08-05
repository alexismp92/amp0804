package com.demo.rental.mapper;

import com.demo.rental.dto.ToolChargesDTO;
import com.demo.rental.dto.ToolDTO;
import com.demo.rental.entity.Tool;
import com.demo.rental.entity.ToolCharges;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ToolMapper {
    ToolMapper INSTANCE = Mappers.getMapper(ToolMapper.class);

    @Mapping(source = "toolCharges", target = "toolCharges")
    ToolDTO toolToToolDTO(Tool tool);

    ToolChargesDTO toolChargesToToolChargesDTO(ToolCharges toolCharges);

    Tool toolDTOToTool(ToolDTO toolDTO);

    ToolCharges toolChargesDTOToToolCharges(ToolChargesDTO toolChargesDTO);
}
