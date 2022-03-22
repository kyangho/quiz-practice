<%-- 
    Document   : add
    Created on : Jan 15, 2022, 7:51:32 PM
    Author     : ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/ajax/setting/setting.js?v=3" type="text/javascript"></script>
<div class="col-md-12">
    <section class="panel">
        <header class="panel-heading">
            Add new setting
        </header>
        <div class="panel-body">
            <form class="form-horizontal tasi-form add-form">
                <div class="form-group">
                    <label class="col-sm-2 col-sm-2 control-label">Setting name<p class="text-danger" style="display: inline"> *</p>:</label>
                    <div class="col-sm-10">
                        <input type="text" name="settingName" class="form-control" 
                               value="${requestScope.setting.name}" required placeholder="Setting name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-sm-2 control-label">Description<p class="text-danger" style="display: inline"> *</p>:</label>
                    <div class="col-sm-10">
                        <textarea class="form-control rounded-2" name="settingDescription" 
                                  id="settingDescription" placeholder="Write description here" 
                                  rows="10" required></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-sm-2 control-label">Type<p class="text-danger" style="display: inline"> *</p></label>
                    <div class="col-sm-10">
                        <select name="settingType" class="form-control m-b-10">
                            <c:forEach items="${requestScope.types}" var="type">
                                <option value="${type.name}">${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-sm-2 control-label">Value<p class="text-danger" style="display: inline"> *</p>: </label>
                    <div class="col-sm-10">
                        <input type="text" name="settingValue" class="form-control" 
                               value="${requestScope.setting.value}"
                               required placeholder="Setting value">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-sm-2 control-label">Status</label>
                    <div class="col-sm-10">
                        <div class="radio">
                            <label>
                                <input type="radio" required name="settingStatus" id="setting-status" value="ACTIVE" 
                                       checked>
                                       Active
                            </label>
                        </div>
                        <label class="col-sm-2 col-sm-2 control-label"></label>
                        <div class="radio">
                            <label>
                                <input type="radio" required name="settingStatus" id="setting-status" value="DEACTIVE" 
                                       ${(requestScope.setting.status == "DEACTIVE") ? "checked" : ""}>
                                Deactive
                            </label>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="notification-add col-sm-6 center-block">
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="col-sm-4">
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-danger center-block col-sm-10"
                                onclick="location.href = 'settinglist'">
                            Discard
                        </button>
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-info center-block col-sm-10">Add</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>