<%-- 
    Document   : list_user
    Created on : Feb 8, 2022, 3:39:20 PM
    Author     : Yankee
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header_footer/header.jsp"></jsp:include>
    <style>
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 0 auto;
            box-shadow: 1px 0px 10px 11px  #61616159;
        }
        /*        tr, td {
                    border: 1px solid black;
                }*/
        .body_inf{
            padding-top: 50px;
        }
    </style>
    <div class="body_inf">
        <table>
            <tr>
                <td>ID</td>
                <td>Phone</td>
                <td>Username</td>
                <!--<td></td>-->
                <td>Email</td>
                <td>Fullname </td>
            </tr>
        <c:forEach items="${requestScope.information}" var="inf">
            <tr>
                <td>${inf.id} </td>
                <td>${inf.fullname} </td>
                <td>${inf.username}</td>
                <!--<td>${inf.email}</td>-->
                <td>${inf.phone}</td>
                <td>${inf.address}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="header_footer/footer.jsp"></jsp:include>
