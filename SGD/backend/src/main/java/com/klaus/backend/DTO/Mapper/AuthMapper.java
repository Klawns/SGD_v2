package com.klaus.backend.DTO.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.klaus.backend.DTO.request.AuthRequestDTO;
import com.klaus.backend.DTO.response.AuthResponseDTO;
import com.klaus.backend.Model.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expenses", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toEntity(AuthRequestDTO dto);

    AuthResponseDTO toDTO(User user);

}