package com.kostya.taskmanager.mapper;


import com.kostya.taskmanager.dto.taskdto.TaskPatchDto;
import com.kostya.taskmanager.model.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchTaskFromDto(TaskPatchDto dto, @MappingTarget Task task);



}
