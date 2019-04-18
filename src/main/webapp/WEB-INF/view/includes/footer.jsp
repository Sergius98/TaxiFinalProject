<%--
  Created by IntelliJ IDEA.
  User: BIGse
  Date: 01-Mar-19
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%----%
<div class="col-lg-2 dropdown open"
     data-toggle="tooltip" data-placement="right" title="<fmt:message key="change_language_message"/>">
    <button class="btn btn-outline-dark btn-sm dropdown-toggle" type="button" id="dropdownMenuButton"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <fmt:message key="language"/>
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <c:forEach items="${LANGUAGES_LIST}" var="language">
            <a class="dropdown-item" href="${pageContext.request.contextPath}${ACTION_URI}?lang=${language}"><fmt:message key="${language}"/></a>
        </c:forEach>
    </div>
</div>

<div class="col-lg-2 dropdown open"
     data-toggle="tooltip" data-placement="right" title="<fmt:message key="change_currency_message"/>" style="margin-top: 5px">
    <button class="btn btn-outline-dark btn-sm dropdown-toggle" type="button" id="dropdownMenuButton2"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <fmt:message key="currency"/>
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <c:forEach items="${CURRENCY_LIST}" var="currency">
            <a class="dropdown-item" href="${pageContext.request.contextPath}${ACTION_URI}?curr=${currency}"><fmt:message key="${currency}"/></a>
        </c:forEach>
    </div>
</div>
<%----%>

<div class="my_bottom bg-dark text-white">
    <div class="row">
        <div class="col-lg-5">
            <a href="#">
                <img src="${pageContext.request.contextPath}/web_resources/pict/ad.jpg" class="my_small_left rounded float-left" alt="ad" title="ad: Fast Food">
            </a>
        </div>
        <div class="col-lg-7 text-right">
            <p class="my_papers">
                Designed by: <br>
                National Technical University of Ukraine<br>
                "Igor Sikorsky Kyiv Polytechnic Institute"<br>
                Faculty of Applied Mathematics <br>
                Special-Purpose Computer Systems Department<br>
                Group KV-51<br>
                <span class="font-italic bg-light text-dark" title="Микитенко С.С."> Mykytenko S.S. </span><br>Tuesday, April 10, 2018
            </p>
        </div>
    </div>
</div>

</div>
</body>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
</html>