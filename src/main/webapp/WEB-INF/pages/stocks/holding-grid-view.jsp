<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped" style="width: 60%;">
	<c:if test="${!empty stocksList}">
		<thead>
			<tr>
				<th>Ticker</th>
				<th>Price Paid</th>
				<th>Quantity</th>
				<th>Total Amount Paid</th>
				<th>Purchase Date</th>
				<th>Gain/Loss</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${stocksList}" var="stock" varStatus="stat">
				<c:choose>
				<c:when test="${stock.profitIndicator  == '+'}">
					<c:set var="rowStyle" scope="page" value="style='color: Green'" />
				</c:when>
				<c:otherwise>
					<c:set var="rowStyle" scope="page" value="style='color: Red'" />
				</c:otherwise>
				</c:choose>
				<tr class="success">
					<td>${stock.ticker}</td>
					<td>${stock.pricePaid}</td>
					<td>${stock.quantity}</td>
					<td>${stock.amount}</td>
					<td>${stock.purchaseDateDisply}</td>
					<td><div ${rowStyle}>${stock.gainLoss}</div></td>
				</tr>
			</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty stocksList}">
		<tr>
			<td colspan="6">No holdings found</td>
		</tr>
	</c:if>
	</tbody>
</table>
<jsp:include page="/WEB-INF/pages/paging/commonPagination.jsp"
	flush="true" />
