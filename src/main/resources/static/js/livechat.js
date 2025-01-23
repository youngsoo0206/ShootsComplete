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
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var imageElement = document.createElement('img');
        imageElement.src = '../img/card1.jpg';
        imageElement.width = 100;
        imageElement.height = 100;
        imageElement.style.border = '2px solid #000';
        imageElement.style.borderRadius = '10px';
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);//message 본문

//messageElement에다가 추가할거 추가하면됩니다.

    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);
    messageElement.appendChild(imageElement);

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


