package br.com.vitor.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.vitor.api.entity.City;

public interface CityService {
	
	public List<City> findAll();
	
	public List<City> saveAll(List<City> cities);
	
	public Optional<City> getById(Long id);
	
	public List<City> getByColumnString(String column, String value);
	
	public List<City> getCapitals();
	
	public List<Object[]> countCitiesByUf();
	
	public List<String> findCityNameByState(String state);
	
	public City persistir(City c);
	
	void deleteById(Long id);
	
//	public List<City> countDistinctByColName(String col);
	
	public Long count();
	
	public List<Object[]> minMaxCitiesByState();
	
	List<City> findByUfIn(Set<String> states);
}
