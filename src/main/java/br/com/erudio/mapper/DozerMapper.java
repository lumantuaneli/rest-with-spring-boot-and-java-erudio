package br.com.erudio.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    
    private DozerMapper() {
        throw new UnsupportedOperationException();
    }
    
    public static <ORIGIN, DESTINATION> DESTINATION parseObject(ORIGIN pOrigin, Class<DESTINATION> pDestinationClass) {
        return mapper.map(pOrigin, pDestinationClass);
    }
    
    public static <ORIGIN, DESTINATION> List<DESTINATION> parseObjects(List<ORIGIN> pOriginObjects, Class<DESTINATION> pDestinationClass) {
        List<DESTINATION> vDestinationObjects = new ArrayList<>();
        for (ORIGIN vOrigin : pOriginObjects) {
            vDestinationObjects.add(mapper.map(vOrigin, pDestinationClass));
        }
        return vDestinationObjects;
    }
    
}
