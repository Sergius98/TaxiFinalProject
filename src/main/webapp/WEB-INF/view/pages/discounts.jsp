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


<c:forEach var = "i" begin = "0" end = "${DISCOUNTS_LIST.size() - 1}">
    <%-- todo: take begin and end for pagenation or do it through sql --%>

    <div class="row my_block">
        <div class="col-lg-3 text-left"></div>
        <div class="col-lg-6 text-left">
        <%-- todo: print only if not null--%>
            <c:if test="${DISCOUNTS_LIST.get(i).getCarClass().isPresent()}">
                <fmt:message key="car_Class"/>${DISCOUNTS_LIST.get(i).getCarClass().get()}<br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getSourceStreetId().isPresent()}">
                <fmt:message key="source_street"/>${DISCOUNTS_LIST.get(i).getSourceStreetId().get()}<br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getDestinationStreetId().isPresent()}">
                <fmt:message key="destination_street"/>${DISCOUNTS_LIST.get(i).getDestinationStreetId().get()}<br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getMinimalBill().isPresent()}">
                <fmt:message key="minimal_bill"/>${DISCOUNTS_LIST.get(i).getMinimalBill().get()}<br>
            </c:if>
            <c:if test="${DISCOUNTS_LIST.get(i).getMinimalThreshold().isPresent()}">
                <fmt:message key="minimal_threshold"/>${DISCOUNTS_LIST.get(i).getMinimalThreshold().get()}<br>
            </c:if>
            <fmt:message key="discount"/>${DISCOUNTS_LIST.get(i).getDiscount()}<br>

        </div>
        <div class="col-lg-3 text-left"></div>
    </div>
</c:forEach>
        <%-- todo: include admin parts --%>
    </div>
</div>


<%@include file="/WEB-INF/view/includes/footer.jsp" %>
