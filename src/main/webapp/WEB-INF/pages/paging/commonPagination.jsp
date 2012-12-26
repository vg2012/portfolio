<form id="paginationHandler">
	<input type="hidden" name="trackingToken" id="trackingToken"
		value="${pagingCriteria.trackingToken}" />
	<input type="hidden" name="currrentIdx" id="currrentIdx"
		value="${pagingCriteria.currrentIdx}" />
	<input type="hidden" name="previousIdx" id="previousIdx"
		value="${pagingCriteria.previousIdx}" />
	<input type="hidden" name="nextIdx" id="nextIdx"
		value="${pagingCriteria.nextIdx}" />
	<input type="hidden" name="numOfPageChunks" id="numOfPageChunks"
		value="${pagingCriteria.numOfPageChunks}" />
		<input type="hidden" name="source" id="source"
		value="${pagingCriteria.source}" />
</form>