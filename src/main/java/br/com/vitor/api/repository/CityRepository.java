package br.com.vitor.api.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.vitor.api.entity.City;

@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, Long> {
	
	List<City> findAll();
	
	List<City> findByUf(String uf);

	List<City> findByNameContainingIgnoreCase(String name);

	List<City> findByCapitalTrueOrderByNameAsc();

	List<City> findByCapitalFalseOrderByNameAsc();

	List<City> findByLonIsLike(String lon);

	List<City> findByLatIsLike(String lat);

	List<City> findByNoAccentsContainingIgnoreCase(String value);

	List<City> findByAlternativeNamesContainingIgnoreCase(String value);

	List<City> findByMicroregionContainingIgnoreCase(String value);

	List<City> findByMesoregionContainingIgnoreCase(String value);

	@Query(value = "select uf, count(uf) from City group by 1 order by 1")
	List<Object[]> countCitiesByUf();

	@Query(value = "select name from City where upper(uf) = :state order by name")
	List<String> findCityNameByState(@Param("state") String state);

//	@Query()
//	Integer countDistinctByColName(@Param("col") String col);

	@Query(value = "select c.uf, count(c.uf) " + "from City c " + "group by 1 " + "having count(*) in "
			+ "((select max(c) from (select count(*) as c from City ci group by uf) as q),(select min(m) from (select count(*) as m from City ct group by uf) as j)) "
			+ "order by 2 desc", nativeQuery = true)
	List<Object[]> minMaxCitiesByState();
	
	List<City> findByUfIn(Set<String> states);

}
