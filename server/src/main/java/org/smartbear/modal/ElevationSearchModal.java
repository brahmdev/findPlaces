package org.smartbear.modal;

/**
 * 
 * @author KalpDev
 * @version 1.0
 * @since 02-09-2017
 *
 */
public class ElevationSearchModal {
	private ElevationSearchResult[] results;

	private String status;

	public ElevationSearchResult[] getResults() {
		return results;
	}

	public void setResults(ElevationSearchResult[] results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClassPojo [results = " + results + ", status = " + status + "]";
	}
}
