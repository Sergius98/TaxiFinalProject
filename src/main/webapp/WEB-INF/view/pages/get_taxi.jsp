<%--
  Created by IntelliJ IDEA.
  User: sergius
  Date: 4/20/19
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="/WEB-INF/view/includes/header.jsp" %>

<div class="lab2_main">
    <div class="container my_main_block">
        <div class="row my_block my_head_block">
          <span class="col text-center">
              <fmt:message key="home_page_title"/>
          </span>
        </div>

        <c:if test="${SUCCESSFUL_CONFIRMATION == 1}">
            <div class="row my_block">
                <div class="col-lg-3"></div>
                <div class="col-lg-6 text-center">
                    <fmt:message key="taxi_taken_successfully"/><br>
                    <fmt:message key="taxi_take_you_in"/>
                        ${DELAY}
                    <fmt:message key="minutes"/>
                </div>
                <div class="col-lg-3"></div>
            </div>
        </c:if>

        <c:if test="${(SUCCESSFUL_CONFIRMATION != 1)&&(SUCCESSFUL_SEARCH != 1)}">
            <div class="row my_block">
                <div class="col-lg-3"></div>
                <div class="col-lg-6 text-center">
                    <form method="POST" action="${pageContext.request.contextPath}${PATH}/get_taxi/search">

                        <div class="form-group">
                            <label for="source_street"><fmt:message key="source_street"/></label>
                            <select class="form-control" id="source_street" name="source_street">
                                <c:forEach items="${STREETS_LIST}" var="item">
                                    <option value="${item.id}">${item.getNameString(sessionScope.lang)}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="destination_street"><fmt:message key="destination_street"/></label>
                            <select class="form-control" id="destination_street" name="destination_street">
                                <c:forEach items="${STREETS_LIST}" var="item">
                                    <option value="${item.id}">${item.getNameString(sessionScope.lang)}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="car_class"><fmt:message key="car_class"/></label>
                            <select class="form-control" id="car_class" name="car_class">
                                <c:forEach items="${CARS_LIST}" var="item">
                                    <option value="${item.id}">${item.getNameString(sessionScope.lang)}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-outline-dark" data-toggle="tooltip"
                                data-placement="top" title="<fmt:message key="search_taxi_message"/>">
                            <fmt:message key="search_taxi"/>
                        </button>
                    </form>
                </div>
                <div class="col-lg-3"></div>
            </div>
        </c:if>


        <c:if test="${(SUCCESSFUL_CONFIRMATION != 1)&&(SUCCESSFUL_SEARCH == 1)}">
            <div class="row my_block">
                <div class="col-lg-3"></div>
                <div class="col-lg-6 text-center">

                    <form method="POST" action="${pageContext.request.contextPath}${PATH}/get_taxi/comfirm">
                        <input type="hidden" name="taxi_id" value="${TAXI_ID}">
                        <input type="hidden" name="car_class_id" value="${CAR_CLASS.id}">
                        <input type="hidden" name="source_street_id" value="${SOURCE_STREET.id}">
                        <input type="hidden" name="destination_street_id" value="${DESTINATION_STREET.id}">

                        car_class: ${CAR_CLASS.getNameString(sessionScope.lang)}<br>
                        source_street: ${SOURCE_STREET.getNameString(sessionScope.lang)}<br>
                        destination street: ${DESTINATION_STREET.getNameString(sessionScope.lang)}<br>
                        discount: ${DISCOUNT}<br>
                        price: ${PRICE}<br>
                            <%--<input class="form-control" type="text" placeholder="Readonly input here…" readonly>--%>


                        <button type="submit" class="btn btn-outline-dark" data-toggle="tooltip"
                                data-placement="top" title="<fmt:message key="comfirm_taxi_message"/>">
                            <fmt:message key="comfirm_taxi"/>
                        </button>
                    </form>

                </div>
                <div class="col-lg-3"></div>
            </div>
        </c:if>

    </div>
</div>


<%@include file="/WEB-INF/view/includes/footer.jsp" %>