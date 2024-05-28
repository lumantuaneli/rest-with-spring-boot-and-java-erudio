package br.com.erudio.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v2.PersonVO;
import br.com.erudio.model.Person;
import br.com.erudio.util.ConstantsUtil;

/**
 * V2
 */
@Service
public class PersonMapper {
    
    public Person toEntity(PersonVO pPersonVo) {
        Person vPersonEntity = new Person();
        vPersonEntity.setAddress(pPersonVo.getAddress());
        vPersonEntity.setFirstName(pPersonVo.getFirstName());
        vPersonEntity.setGender(pPersonVo.getGender());
        vPersonEntity.setId(pPersonVo.getId());
        vPersonEntity.setLastName(pPersonVo.getLastName());
//        vPersonEntity.setBirthday(pPersonVo.getBirthDay());
        return vPersonEntity;
    }

    public PersonVO toVo(Person pPersonEntity) {
        PersonVO vPersonVo = new PersonVO();
        vPersonVo.setAddress(pPersonEntity.getAddress());
        vPersonVo.setFirstName(pPersonEntity.getFirstName());
        vPersonVo.setGender(pPersonEntity.getGender());
        vPersonVo.setId(pPersonEntity.getId());
        vPersonVo.setLastName(pPersonEntity.getLastName());
        vPersonVo.setBirthday(ConstantsUtil.DATE_EPOCH);
        return vPersonVo;
    }

}
