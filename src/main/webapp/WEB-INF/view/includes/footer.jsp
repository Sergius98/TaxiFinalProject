<%--
  Created by IntelliJ IDEA.
  User: BIGse
  Date: 01-Mar-19
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="my_bottom bg-dark text-white">
    <div class="row">
        <div class="col-lg-5">
            <a href="${pageContext.request.contextPath}${PATH}/discounts">
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