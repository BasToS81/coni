package com.gor.sellphotos.utils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gor.sellphotos.dao.CommandeEcole;
import com.gor.sellphotos.dao.CommandeFamille;
import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dto.EcoleDTO;
import com.gor.sellphotos.dto.ecole.CommandeEcoleDTO;
import com.gor.sellphotos.dto.ecole.CommandeFamilleDTOEcole;

public class MapperUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperUtils.class);

    private static MapperFacade mapper;

    static {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Ecole.class, EcoleDTO.class).byDefault().register();
        LOGGER.debug("register map Ecole to EcoleDTO");
        factory.classMap(CommandeEcole.class, CommandeEcoleDTO.class).byDefault().customize(

                        new CustomMapper<CommandeEcole, CommandeEcoleDTO>() {

                            /*
                             * (non-Javadoc)
                             * @see ma.glasnost.orika.CustomMapper#mapAtoB(java.lang.Object, java.lang.Object, ma.glasnost.orika.MappingContext)
                             */
                            @Override
                            public void mapAtoB(CommandeEcole a, CommandeEcoleDTO b, MappingContext context) {
                                // TODO Auto-generated method stub
                                super.mapAtoB(a, b, context);

                                // add your custom mapping code here
                                b.setDateCommandeFromDate(a.getDateCommande());
                                b.setDateLivraisonFromDate(a.getDateLivraison());
                                b.setDateValidationFromDate(a.getDateValidation());

                                LOGGER.debug("map CommandeEcole to CommandeEcoleDTO {}", a);
                            }

                        }
                        ).register();
        factory.classMap(CommandeFamille.class, CommandeFamilleDTOEcole.class).byDefault().customize(

                        new CustomMapper<CommandeFamille, CommandeFamilleDTOEcole>() {

                            /*
                             * (non-Javadoc)
                             * @see ma.glasnost.orika.CustomMapper#mapAtoB(java.lang.Object, java.lang.Object, ma.glasnost.orika.MappingContext)
                             */
                            @Override
                            public void mapAtoB(CommandeFamille a, CommandeFamilleDTOEcole b, MappingContext context) {
                                // TODO Auto-generated method stub
                                super.mapAtoB(a, b, context);

                                // add your custom mapping code here
                                b.setDateCommandeFromDate(a.getDateCommande());
                                b.setDateLivraisonFromDate(a.getDateLivraison());
                                b.setDateValidationFromDate(a.getDateValidation());

                                LOGGER.debug("map CommandeEcole to CommandeEcoleDTO {}", a);
                            }

                        }
                        ).register();

        LOGGER.debug("register map CommandeEcole to CommandeEcoleDTO");
        mapper = factory.getMapperFacade();
    }

    public static <S, D> D convert(S source, Class<D> clazz) {
        LOGGER.debug("Mapping de {} to {}", source.getClass(), clazz);
        return mapper.map(source, clazz);
    }

}
