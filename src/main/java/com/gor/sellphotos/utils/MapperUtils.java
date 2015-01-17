package com.gor.sellphotos.utils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dto.CommandeEleveDTO;
import com.gor.sellphotos.dto.EcoleDTO;

public class MapperUtils {

    private static MapperFacade mapper;

    static {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.registerClassMap(factory.classMap(Ecole.class, EcoleDTO.class).byDefault().toClassMap());
        factory.registerClassMap(factory.classMap(CommandeEleveDTO.class, CommandeEleve.class).customize(new CustomMapper<CommandeEleveDTO, CommandeEleve>() {

            // create your custom mapper
            @Override
            public void mapBtoA(CommandeEleve b, CommandeEleveDTO a, ma.glasnost.orika.MappingContext context) {
                super.mapBtoA(b, a, context);
                a.setIdentifiantEleve(b.getEleve().getIdentifiant());
            }
        }
                        ));

        mapper = factory.getMapperFacade();
    }

    public static <S, D> D convert(S source, Class<D> clazz) {
        return mapper.map(source, clazz);
    }

}
