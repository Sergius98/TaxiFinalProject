<%--
  Created by IntelliJ IDEA.
  User: BIGse
  Date: 25-Feb-19
  Time: 13:51
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
        <c:choose>
          <c:when test="${ROLE == 0}">
            <%@include file="/WEB-INF/view/includes/guest/home.jsp" %>
          </c:when>
          <c:when test="${ROLE == 1}">
            <%@include file="/WEB-INF/view/includes/user/home.jsp" %>
          </c:when>
          <c:when test="${ROLE == 2}">
            <%@include file="/WEB-INF/view/includes/admin/home.jsp" %>
            <%@include file="/WEB-INF/view/includes/user/home.jsp" %>
          </c:when>
          <c:otherwise>
            <%@include file="/WEB-INF/view/includes/errors/dont_have_legitime_role.jsp" %>
          </c:otherwise>
        </c:choose>
      </div>
    </div>


<%@include file="/WEB-INF/view/includes/footer.jsp" %>
