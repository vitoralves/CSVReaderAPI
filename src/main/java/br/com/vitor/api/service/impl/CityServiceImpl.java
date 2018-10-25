package br.com.vitor.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	public List<City> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<City> getById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<City> getByColumnString(String column, String value) {
		switch (column) {
		case "ibge_id":
			return this.getListByIbgeId(Long.valueOf(value));
		case "uf":
			return this.getByUf(value.toUpperCase());
		case "name":
			return this.getByName(value);
		case "capital":
			return this.getByCapital(value);
		case "lon":
			return repository.findByLonIsLike(value);
		case "lat":
			return repository.findByLatIsLike(value);
		case "no_accents":
			return repository.findByNoAccentsContainingIgnoreCase(value);
		case "alternative_names":
			return repository.findByAlternativeNamesContainingIgnoreCase(value);
		case "microregion":
			return repository.findByMicroregionContainingIgnoreCase(value);
		case "mesoregion":
			return repository.findByMesoregionContainingIgnoreCase(value);
		default:
			return new ArrayList<>();
		}

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

//	@Override
//	public List<City> countDistinctByColName(String col) {
//		Path<City> item = Expressions.path(City.class, "item");
//		Path<String> itemColumnName = Expressions.path(String.class, item, "uf");
//		Expression<String> itemValueExpression = Expressions.constant("sp");
//		BooleanExpression fieldEqualsExpression = Expressions.predicate(Ops.EQ, itemColumnName, itemValueExpression);
//
//		return repository.findAll(fieldEqualsExpression);
//	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public List<Object[]> minMaxCitiesByState() {
		return repository.minMaxCitiesByState();
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

	private List<City> getByCapital(String value) {
		if (value.equals("true")) {
			return repository.findByCapitalTrueOrderByNameAsc();
		} else {
			return repository.findByCapitalFalseOrderByNameAsc();
		}
	}

	@Override
	public List<City> findByUfIn(Set<String> states) {
		return repository.findByUfIn(states);
	}

}
