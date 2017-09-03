package org.smartbear.util;

import java.util.ArrayList;
import java.util.List;

import org.smartbear.modal.ElevationSearchModal;
import org.smartbear.modal.PlaceSearchModal;
import org.smartbear.modal.PlaceSearchResult;
import org.smartbear.modal.ResponseModal;

/**
 * 
 * @author KalpDev
 * @version 1.0
 * @since 02-09-2017
 *
 */
public class SBUtil {

	/**
	 * <p>This method is responsible for list of ResponseModal Object which will be used in the front end to show the data</p>
	 * <p><b>Note: </b> Only few parameters are copied from PlaceSearchModal so if we want more data then we need to change/add properties in the ResponseModal Object</p>
	 * @param placeSearchModal Object of PlaceSearchModal which consist of parameters/property returned by Google Place Search API
	 * @param elevationSearchModal Object of ElevationSearchModal which consist of parameters/property returned by Google Elevation Search API
	 * @return List of ResponseModal Object
	 */
	public static List<ResponseModal> getResponseModalList(PlaceSearchModal placeSearchModal,
			ElevationSearchModal elevationSearchModal) {

		List<ResponseModal> responseModalList = new ArrayList<ResponseModal>();
		for (PlaceSearchResult placeSearchResult : placeSearchModal.getResults()) {
			ResponseModal responseModal = new ResponseModal();
			responseModal.setIcon(placeSearchResult.getIcon());
			responseModal.setLat(placeSearchResult.getGeometry().getLocation().getLat());
			responseModal.setLng(placeSearchResult.getGeometry().getLocation().getLng());
			responseModal.setName(placeSearchResult.getName());
			responseModal.setVicinity(placeSearchResult.getVicinity());
			responseModal.setElevation(elevationSearchModal.getResults()[0].getElevation());
			responseModalList.add(responseModal);
		}
		return responseModalList;
	}
}
