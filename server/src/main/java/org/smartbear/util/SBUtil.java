package org.smartbear.util;

import java.util.ArrayList;
import java.util.List;

import org.smartbear.modal.ElevationSearchModal;
import org.smartbear.modal.PlaceSearchModal;
import org.smartbear.modal.PlaceSearchResult;
import org.smartbear.modal.ResponseModal;

public class SBUtil {

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
