// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
// import {createBasicNotification} from './notification/notification'
// const {} = require("../notification.js")
const firebaseConfig = {
    apiKey: "######################",
    authDomain: "######################",
    databaseURL: "######################",
    projectId: "######################",
    storageBucket: "######################",
    messagingSenderId: "######################",
    appId: "######################",
    measurementId: "######################"
  };

firebase.initializeApp(firebaseConfig);

console.log(firebase);

var notificationReference = firebase.database().ref("notification")

notificationReference.on('value', function (snapshot) {
    var value = snapshot.val()
    console.log(value);
    var sortedTs = Object.keys(value).sort()
    var lastReceivedTs = sortedTs[sortedTs.length - 1]
    console.log(lastReceivedTs);
    chrome.notifications.create(lastReceivedTs, {
        type: 'basic',
        iconUrl: 'android-logo.png',
        title: value[lastReceivedTs].packageName,
        contextMessage: value[lastReceivedTs].title,
        message: value[lastReceivedTs].content,
        priority: 2
    })
    // createBasicNotification(value[lastReceivedTs].packageName, value[lastReceivedTs].title, value[lastReceivedTs].content)
});

