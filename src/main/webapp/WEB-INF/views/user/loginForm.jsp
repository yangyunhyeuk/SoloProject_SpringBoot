<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">


    <form action="/auth/loginProc" method="post">

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <button name="remember" class="btn btn-primary">Login</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=2900764235a5dea0937ee6160b389c50&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code "><img width="65px" height="38px" src="/image/kakao_login_button.png"></a>
    </form>


</div>
<%@include file="../layout/footer.jsp" %>

