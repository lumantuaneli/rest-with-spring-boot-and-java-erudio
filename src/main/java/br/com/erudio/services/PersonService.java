package br.com.erudio.services;

import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;

public interface PersonService {

    List<PersonVO> findAll();

    PersonVO findById(Long pId);

    PersonVO create(PersonVO pPersonVo);

    PersonVO update(PersonVO pPersonVo);

    PersonVO update(Long pId, PersonVO pPersonVo);

    void delete(Long pId);

}
