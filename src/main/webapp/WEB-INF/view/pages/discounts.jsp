<%--
  Created by IntelliJ IDEA.
  User: sergius
  Date: 4/19/19
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/includes/header.jsp" %>

<div class="lab2_main">
    <div class="container my_main_block">
        <div class="row my_block my_head_block">
          <span class="col text-center">
              <fmt:message key="discounts_page_title"/>
          </span>
        </div>



        <%-- todo: show only if end >= 0 --%>
<c:forEach var = "i" begin = "${FIRST_ELEMENT}" end = "${LAST_ELEMENT}">
    <%-- todo: take begin and end for pagenation --%>

    <div class="row my_block">
        <div class="col-lg-3 text-left"></div>
        <div class="col-lg-6 text-left">
            <input type="hidden" name="id" value="${DISCOUNTS_LIST.get(i).getId()}">

            <c:if test="${DISCOUNTS_LIST.get(i).getCarClass().isPresent()}">
                <fmt:message key="car_Class"/>
                <fmt:message key="${CARS_LIST.get(DISCOUNTS_LIST.get(i).getCarClass().get()).name}"/><br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getSourceStreetId().isPresent()}">
                <fmt:message key="source_street"/>
                ${STREETS_LIST.get(DISCOUNTS_LIST.get(i).getSourceStreetId().get()-1).getNameString(sessionScope.lang)}<br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getDestinationStreetId().isPresent()}">
                <fmt:message key="destination_street"/>
                ${STREETS_LIST.get(DISCOUNTS_LIST.get(i).getDestinationStreetId().get()-1).getNameString(sessionScope.lang)}<br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getMinimalBill().isPresent()}">
                <fmt:message key="minimal_bill"/>
                ${CURRENCY_FORMATTER.format(DISCOUNTS_LIST.get(i).getMinimalBill())}
                <fmt:message key="${CURRENCY}_s"/><br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getMinimalThreshold().isPresent()}">
                <fmt:message key="minimal_threshold"/>
                ${CURRENCY_FORMATTER.format(DISCOUNTS_LIST.get(i).getMinimalThreshold())}
                <fmt:message key="${CURRENCY}_s"/><br>
            </c:if>
            <fmt:message key="discount"/>
                ${CURRENCY_FORMATTER.format(DISCOUNTS_LIST.get(i).getDiscount())}
            <fmt:message key="${CURRENCY}_s"/><br>


        </div>
        <div class="col-lg-3 text-left"></div>
    </div>
</c:forEach>
        <%-- todo: include admin parts --%>
    </div>
</div>


<%@include file="/WEB-INF/view/includes/footer.jsp" %>
