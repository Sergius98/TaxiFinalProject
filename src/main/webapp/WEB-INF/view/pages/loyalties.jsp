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
              <fmt:message key="loyalty_program_page_title"/>
          </span>
        </div>

        <c:choose>
            <c:when test="${LAST_ELEMENT>=0}">
                <c:forEach var = "i" begin = "${FIRST_ELEMENT}" end = "${LAST_ELEMENT}">

                    <div class="row my_block">
                        <div class="col-lg-3 text-left"></div>
                        <div class="col-lg-6 text-left">

                            <fmt:message key="minimal_threshold"/>
                                ${CURRENCY_FORMATTER.format(LOYALTIES_LIST.get(i).getThreshold())}
                            <fmt:message key="${CURRENCY}_s"/><br>
                            <fmt:message key="discount"/>
                                ${CURRENCY_FORMATTER.formatDouble(LOYALTIES_LIST.get(i).getDiscount())}
                                %

                        </div>
                        <div class="col-lg-3 text-left">
                            <c:if test="${ROLE == 2}">
                                <form method="POST" action="${pageContext.request.contextPath}${PATH}/loyalties/delete">
                                    <input type="hidden" name="id" value="${LOYALTIES_LIST.get(i).getId()}">
                                    <button type="submit" class="btn btn-outline-dark" data-toggle="tooltip"
                                            data-placement="top" title="<fmt:message key="delete_message"/>">
                                        <fmt:message key="delete"/>
                                    </button>
                                </form>
                            </c:if>
                        </div>
                    </div>
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

        <c:if test="${(ROLE == 2) && (LAST_ELEMENT==LOYALTIES_LIST.size() - 1)}">
            <div class="row my_block">
                <div class="col-lg-3 text-center">
                </div>
                <div class="col-lg-6 text-center">
                    <c:if test="${LAST_ELEMENT==LOYALTIES_LIST.size() - 1}">
                        <form method="POST" action="${pageContext.request.contextPath}${PATH}/loyalties/add">

                            <fmt:message key="minimal_threshold"/>
                            <input class="form-control" type="text" name="minimal_threshold" placeholder="" value="1" data-toggle="tooltip"
                                   data-placement="right" title="<fmt:message key="input_minimal_threshold_message"/>">
                            <fmt:message key="discount"/>
                            <input class="form-control" type="text" name="discount" placeholder="" value="1" data-toggle="tooltip"
                                   data-placement="right" title="<fmt:message key="input_loyalties_message"/>">

                            <button type="submit" class="btn btn-outline-dark" data-toggle="tooltip"
                                    data-placement="top" title="<fmt:message key="new_loyalties_message"/>">
                                <fmt:message key="new_loyalties"/>
                            </button>
                        </form>
                    </c:if>
                </div>
                <div class="col-lg-3 text-center">
                </div>
                <div class="col-lg-2 text-center">
                </div>
            </div>
        </c:if>

        <div class="row my_block">
            <div class="col-lg-2 text-center">
                <c:if test="${page>1}">
                    <a href="${pageContext.request.contextPath}${PATH}/loyalties?page=1" class="btn btn-outline-dark" role="button"
                       data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="first_page_message"/>">
                        <fmt:message key="first_page"/>
                    </a>
                </c:if>
            </div>
            <div class="col-lg-2 text-center">
                <c:if test="${page>1}">
                    <a href="${pageContext.request.contextPath}${PATH}/loyalties?page=${page - 1}" class="btn btn-outline-dark" role="button"
                       data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="prev_page_message"/>">
                        <fmt:message key="prev_page"/>
                    </a>
                </c:if>
            </div>
            <div class="col-lg-4 text-center">
                <<${page}>>
            </div>
            <div class="col-lg-2 text-center">
                <c:if test="${LAST_ELEMENT<LOYALTIES_LIST.size() - 1}">
                    <a href="${pageContext.request.contextPath}${PATH}/loyalties?page=${page + 1}" class="btn btn-outline-dark" role="button"
                       data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="next_page_message"/>">
                        <fmt:message key="next_page"/>
                    </a>
                </c:if>
            </div>
            <div class="col-lg-2 text-center">
                <c:if test="${LAST_ELEMENT<LOYALTIES_LIST.size() - 1}">
                    <a href="${pageContext.request.contextPath}${PATH}/loyalties?page=${Integer.MAX_VALUE}" class="btn btn-outline-dark" role="button"
                       data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="last_page_message"/>">
                        <fmt:message key="last_page"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/view/includes/footer.jsp" %>
