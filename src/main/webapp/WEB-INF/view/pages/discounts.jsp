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

<c:choose>
        <c:when test="${LAST_ELEMENT>=0}">
            <c:forEach var = "i" begin = "${FIRST_ELEMENT}" end = "${LAST_ELEMENT}">

                <div class="row my_block">
                    <div class="col-lg-3 text-left"></div>
                    <div class="col-lg-6 text-left">

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

                        <c:if test="${ROLE == 2}">
                            <form method="POST" action="${pageContext.request.contextPath}${PATH}/discounts/delete">
                                <input type="hidden" name="id" value="${DISCOUNTS_LIST.get(i).getId()}">
                                <button type="submit" class="btn btn-outline-dark" data-toggle="tooltip"
                                        data-placement="top" title="<fmt:message key="delete_message"/>">
                                    <fmt:message key="delete"/>
                                </button>
                            </form>
                        </c:if>

                    </div>
                    <div class="col-lg-3 text-left"></div>
                </div>
                <%-- todo: include admin parts (form to delete a node)--%>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="row my_block">
                <div class="col-lg-3 text-left"></div>
                <div class="col-lg-6 text-left">

                    <fmt:message key="no_discounts"/>
                </div>
                <div class="col-lg-3 text-left"></div>
            </div>
        </c:otherwise>
</c:choose>
        <%-- todo: include admin parts (for for creation)--%>

        <div class="row my_block">
            <div class="col-lg-4 text-center">
                <c:if test="${page>1}">
                    <a href="${pageContext.request.contextPath}${PATH}/discounts?page=${page - 1}" class="btn btn-outline-dark" role="button"
                       data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="prev_page_message"/>">
                        <fmt:message key="prev_page"/>
                    </a>
                </c:if>
            </div>
            <div class="col-lg-4 text-center">
                <<${page}>>
            </div>
            <div class="col-lg-4 text-center">
                <c:if test="${LAST_ELEMENT<DISCOUNTS_LIST.size() - 1}">
                    <a href="${pageContext.request.contextPath}${PATH}/discounts?page=${page + 1}" class="btn btn-outline-dark" role="button"
                       data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="next_page_message"/>">
                        <fmt:message key="next_page"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/view/includes/footer.jsp" %>
