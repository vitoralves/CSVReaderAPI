package br.com.vitor.api.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitor.api.dto.CityDTO;
import br.com.vitor.api.entity.City;
import br.com.vitor.api.response.Response;
import br.com.vitor.api.service.CityService;
import br.com.vitor.api.util.CsvCityUtil;

@RestController
@RequestMapping("/api/city")
public class CityControl {

	@Autowired
	private CityService service;

	/**
	 * 
	 * @param ibgeid -> task 5 get IBGE id
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<Response<List<CityDTO>>> getByIbgeId(@RequestParam(value = "ibgeid") Long ibgeid) {
		Response<List<CityDTO>> response = new Response<List<CityDTO>>();
		List<CityDTO> cities = new ArrayList<>();

		if (ibgeid != null && ibgeid > 0) {
			Optional<City> c = service.getById(ibgeid);
			if (c.isPresent()) {
				cities.add(this.convertEntityToDto(c.get()));
			}
		}

		response.setData(cities);
		return ResponseEntity.ok(response);
	}

	/**
	 * task 9 get chosing column by string
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	@GetMapping(value = "column")
	public ResponseEntity<Response<List<CityDTO>>> getByColumnValue(@RequestParam(value = "column") String column,
			@RequestParam(value = "value") String value) {
		Response<List<CityDTO>> response = new Response<List<CityDTO>>();
		List<CityDTO> cities = new ArrayList<>();

		if (column != null && !column.isEmpty() && value != null && !value.isEmpty()) {
			List<City> list = service.getByColumnString(column, value);
			list.forEach(l -> {
				cities.add(this.convertEntityToDto(l));
			});
		}

		response.setData(cities);
		return ResponseEntity.ok(response);
	}

	/**
	 * task 2 - return capitals ordered by name
	 * @return
	 */
	@GetMapping(value = "capitals")
	public ResponseEntity<Response<List<CityDTO>>> getByColumnValue() {
		Response<List<CityDTO>> response = new Response<List<CityDTO>>();
		List<CityDTO> cities = new ArrayList<>();

		List<City> list = service.getCapitals();
		list.forEach(l -> {
			cities.add(this.convertEntityToDto(l));
		});

		response.setData(cities);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "max-min-count")
	public ResponseEntity<Response<Long>> getMaxAndMinCount() {
		Response<Long> response = new Response<Long>();

		Long l = service.teste();

		response.setData(l);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * task 4 count cities by state
	 * @return
	 */
	@GetMapping(value = "count-cities-uf")
	public ResponseEntity<Response<Map<String, Integer>>> getByCitiesUf() {
		Response<Map<String, Integer>> response = new Response<Map<String, Integer>>();
		Map<String, Integer> map = new HashMap<>();
		
		List<Object[]> list = service.countCitiesByUf();
		list.forEach(l -> {
			map.put(l[0].toString(), Integer.parseInt(l[1].toString()));
		});

		response.setData(map);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * task 6 get cities name by state
	 * @return
	 */
	@GetMapping(value = "name-cities-state")
	public ResponseEntity<Response<List<String>>> getCitiesNameByState(@RequestParam(value = "state") String state) {
		Response<List<String>> response = new Response<List<String>>();
		List<String>  list= new ArrayList<>();
		
		list = service.findCityNameByState(state);

		response.setData(list);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * task 10
	 * @param state
	 * @return
	 */
	@GetMapping(value = "count-itens-column")
	public ResponseEntity<Response<Map<String, Integer>>> getCountItensByColumn(@RequestParam(value = "column") String col) {
		Response<Map<String, Integer>> response = new Response<Map<String, Integer>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		Integer count = service.countDistinctByColName(col);
		
		map.put(col, count);
		
		response.setData(map);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * task 11 -> total itens
	 * @param col
	 * @return
	 */
	@GetMapping(value = "total-itens")
	public ResponseEntity<Response<Long>> totalItens() {
		
		Response<Long> response = new Response<Long>();
		
		Long count = service.count();
		response.setData(count);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * teask 7 - add cities
	 * @param CityDTO
	 * @param result
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Response<CityDTO>> cadastrar(@Valid @RequestBody CityDTO dto,
			BindingResult result) {
		Response<CityDTO> response = new Response<CityDTO>();
		
		this.checkIfExists(dto, result);
		
		City c = this.convertDtoToEntity(dto);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		c = this.service.persistir(c);
		response.setData(this.convertEntityToDto(c));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * task 8 -> delete cities
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		
		Optional<City> city = service.getById(id);
		
		if(!city.isPresent()) {
			response.getErrors().add("Id "+id+" not found");
			return ResponseEntity.badRequest().body(response);
		}
		
		service.deleteById(id);
		
		response.setData("");
		return ResponseEntity.ok(response);
	}

	private City convertCsvEntityToJpaEntity(CsvCityUtil csv) {
		City city = new City();
		city.setAlternativeNames(csv.getAlternative_names());
		city.setCapital(csv.getCapital());
		city.setIbgeId(csv.getIbge_id());
		city.setLat(csv.getLat());
		city.setLon(csv.getLon());
		city.setMesoregion(csv.getMesoregion());
		city.setMicroregion(csv.getMicroregion());
		city.setName(csv.getName());
		city.setNoAccents(csv.getNo_accents());
		city.setUf(csv.getUf());

		return city;
	}

	private CityDTO convertEntityToDto(City c) {
		CityDTO city = new CityDTO();

		city.setAlternativeNames(c.getAlternativeNames());
		city.setCapital(c.getCapital());
		city.setIbgeId(c.getIbgeId());
		city.setLat(c.getLat());
		city.setLon(c.getLon());
		city.setMesoregion(c.getMesoregion());
		city.setMicroregion(c.getMicroregion());
		city.setName(c.getName());
		city.setNoAccents(c.getNoAccents());
		city.setUf(c.getUf());

		return city;
	}
	
	private City convertDtoToEntity(CityDTO c) {
		City city = new City();

		city.setAlternativeNames(c.getAlternativeNames());
		city.setCapital(c.getCapital());
		city.setIbgeId(c.getIbgeId());
		city.setLat(c.getLat());
		city.setLon(c.getLon());
		city.setMesoregion(c.getMesoregion());
		city.setMicroregion(c.getMicroregion());
		city.setName(c.getName());
		city.setNoAccents(c.getNoAccents());
		city.setUf(c.getUf());

		return city;
	}
	
	private void checkIfExists(CityDTO dto, BindingResult result) {
		Optional<City> c = this.service.getById(dto.getIbgeId());
		if (c.isPresent()) {
			result.addError(new ObjectError("City", "IBGE "+dto.getIbgeId()+" already registered"));
		}
	}
}
