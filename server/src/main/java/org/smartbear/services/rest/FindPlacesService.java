package org.smartbear.services.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * <h2>Find the Places</h2>
 * 
 * @author KalpDev
 * @version 1.0
 * @since 02-09-2017
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
	
	@Value("${SEARCH.API_URL}")
	private String SEARCH_API_URL;
	
	@Value("${ELEVATION.API.URL}")
	private String ELEVATION_API_URL;
	
	/**
	 * 
	 * <p>This method exposes a REST(REpresentational State Transfer) service with URL {@code}/service/findPlaces</p>
	 * <p>It required following parameters</p>
	 * <ul>
	 * 	<li>1) location (Required Parameter - This parameter is passed as Path Variable)</li>
	 * 	<li>2) radius (Required Parameter - This parameter is passed as Path Variable)</li>
	 * 	<li>3) type (Optional Parameter - This parameter is passed as one of the attribute of the POST Data)</li>
	 * 	<li>4) keyword (Optional Parameter - This parameter is passed as one of the attribute of the POST Data)</li>
	 * </ul>
	 * <p>This method access 2 google API's</p>
	 * <ul>
	 * 	<li>Find the NearBy Places - for more information please refer : {@link} https://developers.google.com/places/web-service/search</li>
	 *  <li>Find the Elevation - for more information please refer : {@link} https://developers.google.com/maps/documentation/elevation/start</li>
	 * </ul>
	 * <p>Errors are logged into the ${java.io.tmpdir}/application.log location</p>
	 * 
	 * @param location First Mandatory Parameter as a Path Variable which is used for specifying the location for Google Places Search API
	 * @param radius Second Mandatory Parameter as a Path Variable which is used for specifying the Range/Radius for Google Places Search API
	 * @param type Third Optional Parameter as a POST Data Load which is used for specifying the location for Google Places Search API
	 * @param keyword Fourth Optional Parameter as a POST Data Load which is used for specifying the location for Google Places Search API
	 * @return Spring ResponseEntity which consists ArrayList of ResponseModal Object
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
		.getForObject(SEARCH_API_URL + "location={location}&radius={radius}&type={type}&keyword={keyword}&key={key}",
		String.class, vars);
		
		PlaceSearchModal placeSearchModal = mapper.readValue(resultPlaceSearch, PlaceSearchModal.class);
		
		vars.put("key", ELEVATION_API_KEY);
		String resultElevationSearch = restTemplate
		.getForObject(ELEVATION_API_URL + "locations={location}&key={key}",
		String.class, vars);
		
		ElevationSearchModal elevationSearchModal = mapper.readValue(resultElevationSearch, ElevationSearchModal.class);
		
		responseModalList = SBUtil.getResponseModalList(placeSearchModal, elevationSearchModal);

		logger.info("Total " + responseModalList.size() + " records mapped sucessfully!");
		} catch(JsonParseException ex) {
			logger.error("JSON was not parsed because of : " + ex.getMessage());
		} catch(JsonMappingException ex) {
			logger.error("Following excaption came while mapping the JSON : " + ex.getMessage());
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		} 
		return new ResponseEntity<>(responseModalList, HttpStatus.OK);
	}
	
}
