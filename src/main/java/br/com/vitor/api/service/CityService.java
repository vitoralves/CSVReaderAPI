package br.com.vitor.api.service;

import java.util.List;

import br.com.vitor.api.entity.City;

public interface CityService {
	
	public List<City> saveAll(List<City> cities);
}
