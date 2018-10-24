package br.com.vitor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vitor.api.entity.City;

@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, Long>{

}
