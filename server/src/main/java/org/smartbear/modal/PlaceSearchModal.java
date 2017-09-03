package org.smartbear.modal;

/**
 * 
 * @author KalpDev
 * @version 1.0
 * @since 02-09-2017
 *
 */
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlaceSearchModal {

	private PlaceSearchResult[] results;

	private String[] html_attributions;
	
	@JsonIgnore
	private String next_page_token;

	private String status;

	public PlaceSearchResult[] getResults() {
		return results;
	}

	public void setResults(PlaceSearchResult[] results) {
		this.results = results;
	}

	public String[] getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(String[] html_attributions) {
		this.html_attributions = html_attributions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNext_page_token() {
		return next_page_token;
	}

	public void setNext_page_token(String next_page_token) {
		this.next_page_token = next_page_token;
	}

	@Override
	public String toString() {
		return "ClassPojo [results = " + results + ", html_attributions = " + html_attributions + ", status = " + status
				+ "]";
	}
}
