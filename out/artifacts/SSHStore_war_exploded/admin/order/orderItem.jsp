<%--
  Created by IntelliJ IDEA.
  User: gunner
  Date: 2019/4/5
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table border="0" width="100%">
    <s:iterator var="orderItem" value="odList">
        <tr>
            <td><img width="40" src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image" />"></td>
            <td><s:property value="#orderItem.count" /></td>
            <td><s:property value="#orderItem.subtotal" /></td>
        </tr>
    </s:iterator>
</table>
