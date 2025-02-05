'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var chatHeader = $('.chat-header');

var stompClient = null;
var username = null;
var topicName = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

usernameForm.addEventListener('submit', connect, true) //true는 캡처링. false는 버블링
messageForm.addEventListener('submit', sendMessage, true)

function connect(event) {
    username = $('#name').val().trim();
    topicName = $('#chatRoomNumber').val();
    //String(num); 이거 null도 처리
    
    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('http://localhost:1000/Shoots/livechat/livechat/ws'); // 포트에 맞게 수정
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);//1헤더 2성공 3실패
    }
    event.preventDefault();
}


function loadChat() {
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/' + topicName, onMessageReceived);
    chatHeader.text("채팅방 이름 : " + topicName);
    loadChat();
    // Tell your username to the server

    // DB설계
    //
    // 채팅방 idx
    // 채팅방 이름
    // 채팅방 내용들
    // 채팅방 가입자?
    // 채팅방

    // 01/23 여기 만지세요
    stompClient.send("/app/"+topicName,
                    {},
                    JSON.stringify({sender: username, type: 'JOIN'})
                    )

    connectingElement.classList.add('hidden');

}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat"+topicName, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    makeMessageElement(message);
    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    }
    else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    }
    else {
        var avatarElement = document.createElement('i');
        avatarElement.appendChild(document.createTextNode(message.sender[0]));
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        var usernameElement = document.createElement('span');
        usernameElement.appendChild(document.createTextNode(message.sender));

        messageElement.classList.add('chat-message');
        messageElement.classList.add(message.sender === username ? 'my-message' : 'other-message');
        messageElement.appendChild(avatarElement);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    textElement.appendChild(document.createTextNode(message.content));

    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
function makeMessageElement(message){
    var avatarElement = document.createElement('i');
    avatarElement.appendChild(document.createTextNode(message.sender[0]));
    avatarElement.style['background-color'] = getAvatarColor("아바타임");

    var usernameElement = document.createElement('span');
    usernameElement.appendChild(document.createTextNode("나는 보낸이"));

    var textElement = document.createElement('p');
    textElement.appendChild(document.createTextNode("나는 텍스트엘레먼트"));

    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');
    messageElement.classList.add(message.sender === username ? 'my-message' : 'other-message');
    messageElement.appendChild(avatarElement);
    messageElement.appendChild(usernameElement);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}


