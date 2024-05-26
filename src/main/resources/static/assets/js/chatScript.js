document.addEventListener('DOMContentLoaded', function() {
    const recentListUl = document.querySelector('.recent_list');
    
    // Example data - you can replace this with your actual data
    const recentItems = [
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        },
        {
            imgSrc: "/assets/images/profile.jpg",
            userName: "Vinn",
            message: "You : Hi",
            time: "2d"
        }
    ];
    console.table(recentItems)

    // Loop through the items and create elements
    recentItems.forEach(item => {
        const liElement = document.createElement('li');

        const imgElement = document.createElement('img');
        imgElement.src = item.imgSrc;
        liElement.appendChild(imgElement);

        const messageInfoDiv = document.createElement('div');
        messageInfoDiv.classList.add('message-info');

        const userNameDiv = document.createElement('div');
        userNameDiv.classList.add('user-name');
        userNameDiv.textContent = item.userName;
        messageInfoDiv.appendChild(userNameDiv);

        const userInfoDiv = document.createElement('div');
        userInfoDiv.classList.add('user-info');

        const messageSpan = document.createElement('span');
        messageSpan.classList.add('message');
        messageSpan.textContent = item.message;
        userInfoDiv.appendChild(messageSpan);

        const timeSpan = document.createElement('span');
        timeSpan.classList.add('time');
        timeSpan.textContent = item.time;
        userInfoDiv.appendChild(timeSpan);

        messageInfoDiv.appendChild(userInfoDiv);
        liElement.appendChild(messageInfoDiv);

        recentListUl.appendChild(liElement);
    });
});
