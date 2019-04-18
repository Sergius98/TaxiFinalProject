<%--
  Created by IntelliJ IDEA.
  User: sergius
  Date: 4/18/19
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/includes/header.jsp" %>

<div class="lab2_main">
    <div class="container my_main_block">
        <div class="row my_block my_head_block">
          <span class="col text-center">
              404 - <fmt:message key="error_title"/>
          </span>
        </div>

    </div>

    <div class="row my_block">
        <div class="col-lg-2"></div>
        <div class="col-lg-8 text-center">
            <span>
                <fmt:message key="error_message"/>
            </span>
        </div>
        <div class="col-lg-2"></div>
    </div>
</div>



<%@include file="/WEB-INF/view/includes/footer.jsp" %>