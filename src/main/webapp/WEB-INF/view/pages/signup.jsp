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
              <fmt:message key="signup_page_title"/>
          </span>
        </div>
        <form method="POST" action="${pageContext.request.contextPath}${ACTION_URI}">
          <div class="row my_block">
            <div class="col-lg-4 text-left">
            </div>
            <div class="col-lg-2 text-left">
              <fmt:message key="input_nickname"/>
            </div>
            <div class="col-lg-3 text-center">
              <input class="form-control" type="text"
                     data-toggle="tooltip" data-placement="top" title="<fmt:message key="input_nickname_message"/>"
                     name="nickname" value="${NICKNAME}">
            </div>
            <div class="col-lg-3 text-left">
            </div>
          </div>
          <div class="row my_block">
            <div class="col-lg-4 text-left">
            </div>
            <div class="col-lg-2 text-left">
              <fmt:message key="input_password"/>
            </div>
            <div class="col-lg-3 text-center">
              <input class="form-control" type="text"
                     data-toggle="tooltip" data-placement="top" title="<fmt:message key="input_password_message"/>"
                     name="password"  value="${PASSWORD}">
            </div>
            <div class="col-lg-3 text-left">
            </div>
          </div>

          <div class="row my_block">
            <div class="col-lg-4"></div>
            <div class="col-lg-4 text-center">
              <button type="submit" class="btn btn-outline-dark"
                      data-toggle="tooltip" data-placement="top" title="<fmt:message key="signup_button_message"/>"
              ><fmt:message key="signup"/></button>
            </div>
            <div class="col-lg-4"></div>
          </div>
        </form>


      </div>
    </div>


<%@include file="/WEB-INF/view/includes/footer.jsp" %>
