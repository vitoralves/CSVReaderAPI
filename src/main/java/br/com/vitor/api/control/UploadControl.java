package br.com.vitor.api.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.vitor.api.dto.CityDTO;
import br.com.vitor.api.entity.City;
import br.com.vitor.api.response.Response;
import br.com.vitor.api.service.CityService;
import br.com.vitor.api.util.CsvCityUtil;
import br.com.vitor.api.util.Util;

@RestController
@RequestMapping("/api/upload")
public class UploadControl {
	
	@Autowired
	private CityService service;
	
	@PostMapping()
	public ResponseEntity<Response<List<CityDTO>>> upload(@RequestParam("file") MultipartFile body) {
		Response<List<CityDTO>> response = new Response<List<CityDTO>>();
		List<CityDTO> result = new ArrayList<>();
		
		try {
			List<CsvCityUtil> list = Util.read(CsvCityUtil.class, body.getInputStream());
			List<City> cities = new ArrayList<>();
			list.forEach(l -> {
				cities.add(this.convertCsvEntityToJpaEntity(l));
			});
			 
			service.saveAll(cities).forEach(r -> {
				result.add(this.convertEntityToDto(r));
			});;
			
		} catch (IOException e) {
			response.getErrors().add(e.getMessage());
		}
		
		response.setData(result);
		return ResponseEntity.ok(response);
	}
	
	private City convertCsvEntityToJpaEntity(CsvCityUtil csv){
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
}
