package br.com.vitor.api.service.impl;

import java.util.List;

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
}
