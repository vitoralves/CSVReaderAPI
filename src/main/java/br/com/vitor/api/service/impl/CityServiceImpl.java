package br.com.vitor.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vitor.api.entity.City;
import br.com.vitor.api.service.CityService;
import br.com.vitor.api.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository repository;

	@Override
	public List<City> saveAll(List<City> cities) {
		return repository.saveAll(cities);
	}

	@Override
	public Optional<City> getById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<City> getByColumnString(String column, String value) {
		switch (column) {
		case "ibge_id":
			return this.getListByIbgeId(Long.valueOf(column));
		case "uf":
			return this.getByUf(value.toUpperCase());
		case "name":
			return this.getByName(value);
		default:
			return new ArrayList<>();
		}
	}

	private List<City> getListByIbgeId(Long id) {
		List<City> list = new ArrayList<>();
		Optional<City> c = this.getById(id);
		if (c.isPresent()) {
			list.add(c.get());
		}

		return list;
	}

	private List<City> getByUf(String uf) {
		return repository.findByUf(uf);
	}

	private List<City> getByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<City> getCapitals() {
		return repository.findByCapitalTrueOrderByNameAsc();
	}

	@Override
	public List<Object[]> countCitiesByUf() {
		return repository.countCitiesByUf();
	}

	@Override
	public List<String> findCityNameByState(String state) {
		return repository.findCityNameByState(state.toUpperCase());
	}

	@Override
	public City persistir(City c) {
		return repository.save(c);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);		
	}

	@Override
	public Integer countDistinctByColName(String col) {
		return repository.countDistinctByColName(col);
	}

	@Override
	public Long count() {
		return repository.count();
	}
	
	@Override
	public Long teste() {
		return repository.teste();
	}
}
