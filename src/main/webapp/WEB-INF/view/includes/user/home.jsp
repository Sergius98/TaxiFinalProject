<%--
  Created by IntelliJ IDEA.
  User: sergius
  Date: 4/17/19
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>

<div class="row my_block">
    <div class="col-lg-2"></div>
    <div class="col-lg-8 text-left">
            <span>
                <fmt:message key="hello"/>${USER.nickname}<br>
                <fmt:message key="you_spent"/>${USER.spendMoney} <fmt:message key="${CURRENCY}_s"/><br>
                <fmt:message key="next_threshold"/>${NEXT_THRESHOLD}  0 <fmt:message key="${CURRENCY}_s"/><br>
                <fmt:message key="current_discount"/>${CURRENT_DISCOUNT}  0%<br>
                <fmt:message key="next_discount"/>${NEXT_DISCOUNT}  0%<br>
            </span>
    </div>
    <div class="col-lg-2"></div>
</div>
