package br.com.vitor.api.service;

import java.util.List;
import java.util.Optional;

import br.com.vitor.api.entity.City;

public interface CityService {
	
	public List<City> saveAll(List<City> cities);
	
	public Optional<City> getById(Long id);
	
	public List<City> getByColumnString(String column, String value);
	
	public List<City> getCapitals();
	
	public List<Object[]> countCitiesByUf();
	
	public List<String> findCityNameByState(String state);
	
	public City persistir(City c);
	
	void deleteById(Long id);
	
	public Integer countDistinctByColName(String col);
	
	public Long count();
	
	public Long teste();
}
