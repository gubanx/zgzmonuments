"use strict";

var wsUri = 'ws://' + document.location.host
    + document.location.pathname.substr(0, document.location.pathname.indexOf("/chat.xhtml"))
    + '/chat';

var username;

var websocket = new WebSocket(wsUri);

websocket.onopen = function (evt) {
    onOpen(evt);
};

websocket.onmessage = function (evt) {
    onMessage(evt);
};

websocket.onerror = function (evt) {
    onError(evt);
};

websocket.onclose = function (evt) {
    onClose(evt);
};

var output = document.getElementById("output");
var msgField = document.getElementById("message");
var users = document.getElementById("users");
var chatlog = document.getElementById("chatlog");

function join() {
    username = msgField.value;
    websocket.send(username + " joined");
}

function sendMessage() {
    websocket.send(username + ": " + msgField.value);
    msgField.value = "";
}

function onOpen() {
    writeToScreen("CONNECTED");
}

function onClose() {
    writeToScreen("DISCONNECTED");
}

function onMessage(evt) {
    writeToScreen("RECEIVED: " + evt.data);
    if (evt.data.indexOf("joined") !== -1) {
        users.innerHTML += evt.data.substring(0, evt.data.indexOf(" joined")) + "\n";
    } else {
        chatlog.innerHTML += evt.data + "\n";
    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function disconnect() {
    websocket.close();
}

function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
}

function sendMsgOnEnter(e) {
    if (e.which == 13 || e.keyCode == 13) {
        e.preventDefault();
        sendMessage();
    }

}

