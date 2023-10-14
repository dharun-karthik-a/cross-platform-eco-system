function createBasicNotification(notificationId, extraTitle, title, message, priority = 2) {

    chrome.notifications.createNotification(notificationId, {
        type: 'basic',
        iconUrl: 'android-logo.png',
        title: extraTitle,
        contextMessage: title,
        message: message,
        priority: priority
    })

}

export {createBasicNotification}
