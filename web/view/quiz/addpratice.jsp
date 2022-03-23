<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../home/header_footer/header.jsp"></jsp:include>
    <div class="row" style="margin: 2%;">
        <div class="col-lg-2">
        <c:if test="${sessionScope.account != null}">
            <a class="btn btn-circle btn-success" href="list"><i class="bi bi-arrow-return-left"></i> Practice List</a>
        </c:if>
    </div>
        <form action="add" method="GET" class="col-lg-9 row" id="searchQues">
        <h2><strong>Please choose options to select some questions for new practice!</strong></h2>
        <div class="col-lg-3">
            Subject: 
            <select name="subject" class="form-select form-select-sm" aria-label=".form-select-sm example">
                <option value="all">Choose subject</option>    
                <c:forEach items="${subject}" var="s">
                    <option ${s.subject_id eq subjectID ? "selected=\"selected\"" : ""} value="${s.subject_id}">${s.subject_title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-3">
            Category:
            <select name="category" class="form-select form-select-sm" onchange="submitForm()" aria-label=".form-select-sm example">
                <option value="all">Choose category</option>   
                <c:forEach items="${categories}" var="c">
                    <option ${c.category_id eq cateID ? "selected=\"selected\"" : ""} value ="${c.category_id}">${c.category_value}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-3">
            Sub-category:
            <select name="subcate" class="form-select form-select-sm" aria-label=".form-select-sm example">
                <option value="all">Choose sub-category</option>   
                <c:forEach items="${subcate}" var="sb">
                    <option ${sb.id eq subcateId ? "selected=\"selected\"" : ""} value="${sb.id}">${sb.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-2">
            Number of questions:
            <input value="${noQues}" type="text" name="noQues" placeholder="Number of questions" style="border: solid 1px #d7cece;" class="form-select-sm" aria-label=".form-select-sm example">
        </div>
        <div class="col-lg-1">
            <button style="margin-top: 20px; height: 34px;" class="btn btn-primary" type="submit">Search</button>
        </div>

    </form>
</div>
<div class="container-fluid">
    <div class="row flex-nowrap" style="margin: 2%;">
        <div class="col-lg-2">
        </div>
        <div class="col-lg-9">
            <!-- Service Start -->
            <c:if test="${questions.size() == 0}">
                <h3>Not found questions!</h3>
            </c:if>
            <c:if test="${questions.size() > 0}">
                <div id="choose" style="color: blue; font-weight: bolder;"><input class="form-check-input" onclick="chooseQuestion()" type="checkbox">Select all question</div>
                <div hidden="" id="unchoose" style="color: blue; font-weight: bolder;"><input class="form-check-input" onclick="unchooseQuestion()" type="checkbox">Un select all question</div>
                <form action="add" method="POST">
                    <input type="hidden" value="${cateID}" name="cateid">
                    <input type="hidden" value="${subjectID}" name="subjectid">
                    <input type="hidden" value="${subcateId}" name="subcateid">
                    <table>
                        <c:forEach items="${questions}" var="q">
                            <tr>
                                <td><input type="checkbox" class="ques form-check-input" name="questionId" value="${q.id}"></td>
                                <td>
                                    <p>${q.content}</p>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button class="btn btn-danger" type="submit">Practice</button>
                </form>
            </c:if>
        </div>
    </div>
</div>
<script>
    function chooseQuestion() {
        var question = document.getElementsByClassName('ques');
        for (var i = 0; i < question.length; i++) {
            question[i].checked = true;
        }
        document.getElementById('unchoose').hidden = false;
        document.getElementById('choose').hidden = true;
    }
    function unchooseQuestion() {
        document.getElementById('unchoose').hidden = true;
        document.getElementById('choose').hidden = false;
        var question = document.getElementsByClassName('ques');
        for (var i = 0; i < question.length; i++) {
            question[i].checked = false;
        }
    }
    
    function submitForm(){
        document.getElementById('searchQues').submit();
    }
</script>
<style>
    .quiz{
        width: 66px;
        background-color: #999c9b;
        color: white;
        text-align: center;
        border-radius: 11px;
        box-shadow: 0px 0px 6px 2px #5a5757;
    }

    .page{
        /*margin-left: 94%;*/
        color: white;
        width: 100px;
        box-shadow: 0px 0px 4px 1px black;
    }
</style>
<jsp:include page="../home/header_footer/footer.jsp"></jsp:include>