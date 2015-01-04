package com.gor.sellphotos.utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dto.EcoleDTO;


public class MapperUtils {

    private static MapperFacade mapper;
    
    static {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.registerClassMap(factory.classMap(Ecole.class, EcoleDTO.class).byDefault().toClassMap());

        mapper = factory.getMapperFacade();
    }

    public static <S, D> D convert(S source, Class<D> clazz) {
        return mapper.map(source, clazz);
    }

}
