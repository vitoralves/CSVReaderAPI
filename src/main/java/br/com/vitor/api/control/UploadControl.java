package br.com.vitor.api.control;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.vitor.api.response.Response;
import br.com.vitor.api.util.CsvCityUtil;
import br.com.vitor.api.util.Util;

@RestController
@RequestMapping("/api/upload")
public class UploadControl {
	
	@PostMapping()
	public ResponseEntity<Response<String>> upload(@RequestParam("file") MultipartFile body) {
		Response<String> response = new Response<String>();
		
		try {
			List<CsvCityUtil> list = Util.read(CsvCityUtil.class, body.getInputStream());
			list.forEach(l -> {
				System.out.println(l.toString());
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setData("");
		return ResponseEntity.ok(response);
	}
}
