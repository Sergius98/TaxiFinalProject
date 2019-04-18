<%--
  Created by IntelliJ IDEA.
  User: BIGse
  Date: 25-Feb-19
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>

<div class="lab2_main">
  <div class="container my_main_block">
    <div class="row my_block my_head_block">
      <span class="col text-center">${MESSAGE.getString("taxes_table")}</span>
    </div>

    <div class="row my_white_block table-responsive-lg">
      <c:choose>
        <c:when test="${TAX_LIST.size() > 0}">
          <table class="col text-center table table-striped" style="margin-top: 10px;">
            <thead class="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">${MESSAGE.getString("table_name")}</th>
              <th scope="col">${MESSAGE.getString("table_value")}</th>
              <th scope="col">${MESSAGE.getString("table_tax_per_cent")}</th>
              <th scope="col">${MESSAGE.getString("table_tax")}</th>
            </tr>
            </thead>
            <tbody>
              <c:forEach var = "i" begin = "0" end = "${TAX_LIST.size() - 1}">
                <tr>
                  <th scope="row">${i + 1}</th>
                  <td>${MESSAGE.getString(TAX_LIST.get(i).getName())}</td>
                  <td>${TAX_LIST.get(i).getValue()}</td>
                  <td>${TAX_LIST.get(i).getTax_per_cent()}</td>
                  <td>${TAX_LIST.get(i).getTax()}</td>
                </tr>
              </c:forEach>
              <tr>
                <th scope="row"></th>
                <td></td>
                <td></td>
                <td>${MESSAGE.getString("table_sum")}:</td>
                <td>${TAX_SUM}</td>
              </tr>
            </tbody>
          </table>
        </c:when>
        <c:otherwise>
            <div class="col-12">
              <span class="text-center">${MESSAGE.getString("empty_table_message")}</span>
            </div>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="row my_block">
      <div class="col-lg-4"></div>
      <div class="col-lg-4 text-center">

        <a href="${pageContext.request.contextPath}${PATHES.get("input")}" class="btn btn-outline-dark" role="button"
           data-toggle="tooltip" data-placement="bottom" title="${MESSAGE.getString("back_to_calculator_message")}"
        >${MESSAGE.getString("back_to_calculator")}</a>
      </div>
      <div class="col-lg-4"></div>
    </div>
  </div>
</div>


<%@include file="../includes/footer.jsp" %>
