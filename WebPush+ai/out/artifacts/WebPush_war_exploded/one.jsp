<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ChatRoom (u r'n alone!)</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<script>
    var wsUri = "ws://" + document.location.host + "/chat/"
        + "<%=request.getSession().getAttribute("channel")%>"
        + "?"+"<%=request.getSession().getAttribute("username")%>";

    var websocket = new WebSocket(wsUri);

    websocket.onmessage = function (evt) {
        onMessage(evt)
    };
    websocket.onerror = function (evt) {
        onError(evt)
    };
    websocket.onopen = function (evt) {
        onOpen(evt)
    };

    function onMessage(evt) {
        // alert(evt.data);
        setMsg(evt.data);
        console.log("received over websockets: " + evt.data);
    }

    function onError(evt) {
        console.log("onError websockets: " + evt.data);
    }

    function onOpen() {
        // console.log("Connected to " + wsUri);
        console.log("Connect to " + "<%=request.getSession().getAttribute("channel")%>" + " channel")
    }

    function sendText() {
        // msg = prompt("enter your text")
        msg = document.getElementById("username").value;
        console.log("sending text: " + msg);
        websocket.send(msg);
        document.getElementById("username").value = "";
    }

    function setMsg(msg) {
        document.getElementById("messageBox").innerHTML += "<h5>" + msg + "</h5>";
    }
</script>

<div class="container" >
    <div class="panel-body" style="margin-top: 20px">
        <table class="table table-hover">
            <tr>
                <td style=" width: 40%;"><input class="form-control" type="text" id="username" placeholder="write your text" ></td>
                <td style="text-align: left;"><button class="btn btn-info" onclick="sendText()"><svg xmlns="http://www.w3.org/2000/svg" width="50" height="22" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
                    <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                    <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
                </svg></button></td>
                <td style="text-align: right;"><h3><a href="http://localhost:8080/" >BACK HOME</a></h3></td>
            </tr>
        </table>
        <div id="messageBox" style="text-align: -moz-left"></div>
    </div>
</div>
</body>
</html>
