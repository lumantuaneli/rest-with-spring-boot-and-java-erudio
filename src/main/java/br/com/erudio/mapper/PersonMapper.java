package br.com.erudio.mapper;

import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;

public class PersonMapper {
    
    private PersonMapper() {
        throw new UnsupportedOperationException();
    }

    public static Person toEntity(PersonVO pPersonVo) {
        return DozerMapper.parseObject(pPersonVo, Person.class);
    }

    public static PersonVO toVo(Person pPersonEntity) {
        return DozerMapper.parseObject(pPersonEntity, PersonVO.class);
    }

    public static List<Person> toEntityList(List<PersonVO> pPersonVoList) {
        return DozerMapper.parseListObjects(pPersonVoList, Person.class);
    }

    public static List<PersonVO> toVoList(List<Person> pPersonEntityList) {
        return DozerMapper.parseListObjects(pPersonEntityList, PersonVO.class);
    }

}
