<%-- 
    Document   : postthumbnail
    Created on : Feb 13, 2022, 7:56:08 PM
    Author     : ducky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form class="upload-thumbnail-form" action="thumbnail" method="POST" enctype="multipart/form-data">
            Select file to upload: 
            <input class="form-control" type="file" name="thumbnail" />
            <button class="btn btn-primary" type="submit" value="Upload" />Upload </button>
    </form>
</body>
</html>
