package org.hjjang.springexample.repository;


import org.hjjang.springexample.domain.PersonProfileVo;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<PersonProfileVo, String> {
}
