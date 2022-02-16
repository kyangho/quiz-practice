<%-- 
    Document   : homedirector
    Created on : Jan 15, 2022, 3:07:56 PM
    Author     : ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Director</title>
        <jsp:include page="header/linkcss.jsp"></jsp:include>
        </head>

        <body class="skin-black">
        <jsp:include page="header/headerforadmin.jsp"></jsp:include>

            <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:include page="header/leftmenuforadmin.jsp"></jsp:include>
                <aside class="right-side">
                    <section class="content">
                    <c:if test="${requestScope.contentPageIncluded != null}">
                        <jsp:include page="${requestScope.contentPageIncluded}"></jsp:include>
                    </c:if>
                </section>
                aaaaahahahahahahahahahahahhahahahahahahhahahahahaahhahahahahahah
                <div class="col-md-8 my-5">

                    <fieldset class="sort-border">
                        <legend class="sort-border">Sort</legend>
                        <div class="control-group">
                            <select class="select select-initialized" id="filter-sort-select">
                                <option value="" disabled="" selected="">Choose category</option>
                                <option value="1">Product name ascending</option>
                                <option value="2">Product name descending</option>
                                <option value="3">Highest price</option>
                                <option value="4">Lowest price</option>
                            </select>
                        </div>
                    </fieldset>
            </aside>
        </div>


    </div>
    <jsp:include page="header/linkjavascript.jsp"></jsp:include>
</body>
</html>
