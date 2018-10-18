package com.vaitkevicius.main.common.converter;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Entity, Dto> {

    public List<Dto> toDto(List<Entity> entity) {
        return convert(entity, this::toDto);
    }

    public Dto toDto(Entity entity) {
        return entity == null ? null : convertToDto(entity);
    }

    protected abstract Dto convertToDto(Entity entity);

    public List<Entity> toEntity(List<Dto> dto) {
        return convert(dto, this::toEntity);
    }

    public Entity toEntity(Dto dto) {
        return dto == null ? null : convertToEntity(dto);
    }

    protected abstract Entity convertToEntity(Dto dto);

    protected <In, Out> List<Out> convert(List<In> in, Function<In, Out> converter) {
        if(in == null) {
            return Collections.emptyList();
        }
        return in.stream()
                .map(converter)
                .collect(Collectors.toList());
    }
}
