<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:setBundle basename="messages" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stocks Dashboard</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/jVal.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	data-main="${pageContext.request.contextPath}/js/stocks/config.js"
	src="${pageContext.request.contextPath}/js/pfm/core/krn/sys/kernel.js"></script>
</head>
<body>
	<div align="right" style="width: 85%">
		<span class="label label-important">${username}</span> <a
			href="<c:url value="/logout" />"> <label class="control-label"
			for="name">Logout</label></a>
	</div>
	<div id="inline_stock_grid"
		style="padding: 0px; background: none; border: 1px">
		<form:form id="stockDetailSaveForm" class="form-horizontal"
			commandName="stocksDTO" method="POST">
			<fieldset>
				<fieldset>
					<legend>Stock Details</legend>
				</fieldset>
				<table>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">Ticker</label>
							</div>
						</td>
						<td>
							<div class="control-group">
								<input type="text" class="input-small" name="ticker" id="ticker" style="font-size: 12px"
									jVal="{valid:function (val) { if (val.length < 1) return 'Ticker is required'; else return ''; }}"
									jValKey="{valid:/[a-zA-Z]/, message:'&quot;%c&quot; Invalid Character - Only Alphabets Allowed'}" />
								<form:errors path="ticker" cssClass="errorBox" />
								<span id="valid"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label" >Price</label>
							</div>
						</td>
						<td>
							<div class="control-group">
								<input type="text" class="input-small" name="pricePaid" style="font-size: 12px"
									id="pricePaid"
									jVal="{valid:function (val) { if (val.length < 1) return 'Price is required'; else return ''; }}"
									jValKey="{valid:/[0-9.]/, message:'&quot;%c&quot; Invalid Character - Only Digits Allowed'}" />
								<form:errors path="pricePaid" cssClass="errorBox" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">Quantity</label>
							</div>
						</td>
						<td>
							<div class="control-group">
								<input type="text" class="input-small" name="quantity" style="font-size: 12px"
									id="quantity"
									jVal="{valid:function (val) { if (val.length < 1) return 'Quantity is required'; else return ''; }}"
									jValKey="{valid:/[0-9]/, message:'&quot;%c&quot; Invalid Character - Only Digits Allowed'}" />
								<form:errors path="quantity" cssClass="errorBox" />
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					<tr>
						<td colspan="2">
							<div class="control-group"  align="right">
								<div id="stockDetailSaveAction">
									<a href="#" class="btn btn-primary btn-small">Save Details</a>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
		</form:form>
		<fieldset>
			<legend>Holdings</legend>
		</fieldset>
		<div id="wait"></div>
		<div id="holdingGrid"></div>
		<div class="pagination">
			<div id="Pagination"></div>
		</div>
	</div>
</body>
</html>