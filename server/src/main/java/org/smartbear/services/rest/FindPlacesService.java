package org.smartbear.services.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartbear.modal.ElevationSearchModal;
import org.smartbear.modal.PlaceSearchModal;
import org.smartbear.modal.ResponseModal;
import org.smartbear.util.SBUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

/**
 * 
 * @author KalpDev
 *
 */

@RestController
@RequestMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
@PropertySource("classpath:config.properties")
public class FindPlacesService {

	private static final Logger logger = LoggerFactory.getLogger(FindPlacesService.class);
	
	@Value("${SEARCH.API.KEY}")
	private String SEARCH_API_KEY;
	
	@Value("${ELEVATION.API.KEY}")
	private String ELEVATION_API_KEY;
	/**
	 * 
	 * @param location
	 * @param radius
	 * @param type
	 * @param keyword
	 * @return
	 * @throws JSONException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/findPlaces/{location}/{radius}")
	public ResponseEntity<List<ResponseModal>> getPlaceForMe(@PathVariable String location, @PathVariable String radius, @RequestParam(value="type") String type, @RequestParam(value="keyword") String keyword) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		List<ResponseModal> responseModalList = new ArrayList<ResponseModal>();

		vars.put("location", location);
		vars.put("radius", radius);
		vars.put("type", type);
		vars.put("keyword", keyword);
		vars.put("key", SEARCH_API_KEY);
		
		try {
		String resultPlaceSearch = restTemplate
		.getForObject("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&radius={radius}&type={type}&keyword={keyword}&key={key}",
		String.class, vars);
		
		PlaceSearchModal placeSearchModal = mapper.readValue(resultPlaceSearch, PlaceSearchModal.class);
		
		vars.put("key", ELEVATION_API_KEY);
		String resultElevationSearch = restTemplate
		.getForObject("https://maps.googleapis.com/maps/api/elevation/json?locations={location}&key={key}",
		String.class, vars);
		
		ElevationSearchModal elevationSearchModal = mapper.readValue(resultElevationSearch, ElevationSearchModal.class);
		
		responseModalList = SBUtil.getResponseModalList(placeSearchModal, elevationSearchModal);

		logger.info("Total " + responseModalList.size() + " records mapped sucessfully!");
		} catch(JsonParseException ex) {
			logger.error("JSON was not parsed because of : " + ex.getMessage());
		} catch(JsonMappingException ex) {
			logger.error("Following excaption came while mapping the JSON : " + ex.getMessage());
		} catch(IOException ex) {
			logger.error(ex.getMessage());
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		} 
		return new ResponseEntity<>(responseModalList, HttpStatus.OK);
	}
	
}
