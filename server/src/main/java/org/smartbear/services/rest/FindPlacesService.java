package org.smartbear.services.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.smartbear.modal.ElevationSearchModal;
import org.smartbear.modal.PlaceSearchModal;
import org.smartbear.modal.ResponseModal;
import org.smartbear.util.SBUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class FindPlacesService {

	@RequestMapping(method = RequestMethod.POST, value = "/findPlaces/{location}/{radius}")
	public ResponseEntity<List<ResponseModal>> getPlaceForMe(@PathVariable String location, @PathVariable String radius, @RequestParam(value="type") String type, @RequestParam(value="keyword") String keyword) throws JSONException, JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		vars.put("location", location);
		vars.put("radius", radius);
		vars.put("type", type);
		vars.put("keyword", keyword);
		vars.put("key", "AIzaSyD9GhIz_uwO_xLKFoffiwmF0RIL05WLckM");
		String resultPlaceSearch = restTemplate
		.getForObject("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&radius={radius}&type={type}&keyword={keyword}&key={key}",
		String.class, vars);
		
		System.out.println(resultPlaceSearch);
		PlaceSearchModal placeSearchModal = mapper.readValue(resultPlaceSearch, PlaceSearchModal.class);
		
		
		vars.put("key", "AIzaSyD3EHtHKQxOAxxtoVIGbc0aSb4DuRYV4QY");
		String resultElevationSearch = restTemplate
		.getForObject("https://maps.googleapis.com/maps/api/elevation/json?locations={location}&key={key}",
		String.class, vars);
		
		ElevationSearchModal elevationSearchModal = mapper.readValue(resultElevationSearch, ElevationSearchModal.class);
		System.out.println(resultElevationSearch);
		
		List<ResponseModal> responseModalList = SBUtil.getResponseModalList(placeSearchModal, elevationSearchModal);

		System.out.println(responseModalList.size());
		
		return new ResponseEntity<>(responseModalList, HttpStatus.OK);
	}
	
}
