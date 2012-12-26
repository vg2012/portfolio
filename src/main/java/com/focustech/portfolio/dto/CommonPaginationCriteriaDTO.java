package com.focustech.portfolio.dto;

public class CommonPaginationCriteriaDTO {
	
	private String trackingToken;
	private String currrentIdx;
	private String previousIdx;
	private String nextIdx;
	private String numOfPageChunks;
	private String source = "N/A";
	private Boolean showPagination = true;
	
	
	
	public CommonPaginationCriteriaDTO() {
		super();
	}
	
	public Boolean getShowPagination() {
		return showPagination;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setShowPagination(Boolean showPagination) {
		this.showPagination = showPagination;
	}

	public String getTrackingToken() {
		return trackingToken;
	}
	public void setTrackingToken(String trackingToken) {
		this.trackingToken = trackingToken;
	}
	public String getCurrrentIdx() {
		return currrentIdx;
	}
	public void setCurrrentIdx(String currrentIdx) {
		this.currrentIdx = currrentIdx;
	}
	public String getPreviousIdx() {
		return previousIdx;
	}
	public void setPreviousIdx(String previousIdx) {
		this.previousIdx = previousIdx;
	}
	public String getNextIdx() {
		return nextIdx;
	}
	public void setNextIdx(String nextIdx) {
		this.nextIdx = nextIdx;
	}
	public String getNumOfPageChunks() {
		return numOfPageChunks;
	}
	public void setNumOfPageChunks(String numOfPageChunks) {
		this.numOfPageChunks = numOfPageChunks;
	}
	
	

}
