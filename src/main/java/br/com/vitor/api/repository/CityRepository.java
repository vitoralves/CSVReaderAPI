package br.com.vitor.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.vitor.api.entity.City;

@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, Long>{
	
	List<City> findByUf(String uf); 
	
	List<City> findByNameContainingIgnoreCase(String name);
	
	List<City> findByCapitalTrueOrderByNameAsc();
	
	@Query(value = "select uf, count(uf) from City group by 1 order by 1")
	List<Object[]> countCitiesByUf();
	
	@Query(value = "select name from City where upper(uf) = :state order by name")
	List<String> findCityNameByState(@Param("state") String state);
	
	@Query(value = "select distinct cast(count(uf) as integer) from City")
	Integer countDistinctByColName(@Param("col") String col);
	
	@Query (value = "select max(count(uf)) from City")
	Long teste();
	
}
