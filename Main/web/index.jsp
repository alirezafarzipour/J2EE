<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 8/5/2021
  Time: 4:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>وارد شوید</title>
    <style>
      * {
        box-sizing: border-box;
      }

      *:focus {
        outline: none;
      }
      body {
        font-family: Arial;
        background-color: darkgray;
        padding: 50px;
      }
      .login {
        margin: 20px auto;
        width: 300px;
      }
      .login-screen {
        background-color: #FFF;
        padding: 20px;
        border-radius: 5px
      }

      .app-title {
        text-align: center;
        color: #777;
      }

      .login-form {
        text-align: center;
      }
      .control-group {
        margin-bottom: 10px;
      }

      input {
        text-align: center;
        background-color: #ECF0F1;
        border: 2px solid transparent;
        border-radius: 3px;
        font-size: 16px;
        font-weight: 200;
        padding: 10px 0;
        width: 250px;
        transition: border .5s;
      }

      input:focus {
        border: 2px solid #3498DB;
        box-shadow: none;
      }

      .btn {
        border: 2px solid transparent;
        background: #3498DB;
        color: #ffffff;
        font-size: 16px;
        line-height: 25px;
        padding: 10px 0;
        text-decoration: none;
        text-shadow: none;
        border-radius: 3px;
        box-shadow: none;
        transition: 0.25s;
        display: block;
        width: 250px;
        margin: 0 auto;
      }

      .btn:hover {
        background-color: #2980B9;
      }

      .login-link {
        font-size: 12px;
        color: #444;
        display: block;
        margin-top: 12px;
      }
    </style>
  </head>
  <body>
  <div class="login">
    <div class="login-screen">
      <div class="app-title">
        <h1>Login</h1>
      </div>

      <form action="/login.do">
        <div class="login-form">
          <div class="control-group">
            <input type="text" class="login-field" value="" placeholder="username" id="login-name" name="username">
            <label class="login-field-icon fui-user" for="login-name"></label>
          </div>

          <div class="control-group">
            <input type="password" class="login-field" value="" placeholder="password" id="login-pass" name="password">
            <label class="login-field-icon fui-lock" for="login-pass"></label>
          </div>

          <input class="btn btn-primary btn-large btn-block" type="submit" value="LOGIN">
        </div>
      </form>

    </div>
  </div>
  </body>
</html>
