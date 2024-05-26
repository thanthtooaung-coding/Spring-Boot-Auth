async function generatePost() {
    try {
        const postData = await fetchPosts();
        useFetchedPosts();
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        const currentUserProfile = currentUser.profileUrl;
        const posts = [];
        
        postData.forEach(postDataItem => {
            const user = postDataItem.user;
            if (user) {
                const postContainer = $('<div class="friends_post"></div>');

                const friendPostTop = $('<div class="friend_post_top"></div>');
                const imgAndName = $('<div class="img_and_name"></div>');
                const img = $('<img>').attr('src', user.profileUrl);
                const friendsNameDiv = $('<div class="friends_name"></div>');
                const friendsName = $('<p class="friends_name"></p>').text(user.name);
                // const time = $('<p class="time"></p>').html(`${postDataItem.time} <i class="fa-solid fa-user-group"></i>`);
                const time = $('<p class="time"></p>')
                	.attr('data-timestamp', postDataItem.createdAt)
                	.text('Just now');
                const userGroupIcon = $('<i class="fa-solid fa-user-group"></i>');
                const menu = $('<div class="menu"></div>');
                const ellipsisIcon = $('<i class="fa-solid fa-ellipsis"></i>');                
                const postText = $('<p></p>').text(postDataItem.postText);
                const postImage = $('<img>').attr('src', '/assets/images/aye_myint_san.jpg');
                const info = $('<div class="info"></div>');
                const emojiImg = $('<div class="emoji_img"></div>');
                const likeIcon = $('<img>').attr('src', '/assets/images/like.png');
                const hahaIcon = $('<img>').attr('src', '/assets/images/haha.png');
                const heartIcon = $('<img>').attr('src', '/assets/images/heart.png');
                const likeCount = $('<p></p>').text(`You, Hein Arkar and 250 others `);
                const comment = $('<div class="comment"></div>');
                // const commentCount = $('<p></p>').text(`${postDataItem.commentCount} Comments`);
                const commentCount = $('<p></p>').text(`421 Comments`);
                // const shareCount = $('<p></p>').text(`${postDataItem.shareCount} Shares`);
                const shareCount = $('<p></p>').text(`1.3K Shares`);
                const hr1 = $('<hr>');
                const likeSection = $('<div class="like"></div>');
                const likeIcons = $('<div class="like_icon"></div>');                
                let likeIconThumbUp = $('<i class="fa-regular fa-heart"></i>');
		        if (postDataItem.likes.some(postLikeObject => postLikeObject.user.name === currentUser.name)) {
		            likeIconThumbUp = $('<i class="fa-solid fa-heart activi"></i>');
		        }                           
                const likeTextThumbUp = $(`<p>${postDataItem.likeCount}</p>`);
                const commentIcons = $('<div class="like_icon"></div>');
                const commentIconMessage = $('<i class="fa-regular fa-message"></i>');
                const commentText = $(`<p>${postDataItem.commentCount}</p>`);
                const retweetIcons = $('<div class="like_icon"></div>');
                const retweetIconMessage = $('<i class="fa-solid fa-retweet"></i>');
                const retweetIconText = $(`<p>${postDataItem.commentCount}</p>`);
                const viewIcons = $('<div class="like_icon"></div>');
                const viewIconMessage = $('<i class="fa-regular fa-eye"></i>');
                const viewIconText = $(`<p>${postDataItem.commentCount}</p>`);
                const shareIcons = $('<div class="like_icon"></div>');
                const shareIconMessage = $('<i class="fa-solid fa-share"></i>');
                //const shareText = $(`<p>${postDataItem.commentCount}</p>`);
                const hr2 = $('<hr>');
                const commentWrapper = $('<div class="comment_warpper"></div>');
                const commenterImage = $('<img>').attr('src', currentUserProfile);
                const commenterCircle = $('<div class="circle"></div>');
                const commentSearch = $('<div class="comment_search"></div>');
                const commentInput = $('<input>').attr({'type': 'text', 'placeholder': 'Write a comment'});
                const emojiSmile = $('<i class="fa-regular fa-face-smile"></i>');
                const cameraIcon = $('<i class="fa-solid fa-camera"></i>');
                const stickyNote = $('<i class="fa-regular fa-note-sticky"></i>');

                imgAndName.append(img, friendsNameDiv);
                friendsNameDiv.append(friendsName, time)
                time.append(userGroupIcon);
                friendPostTop.append(imgAndName, menu);
                menu.append(ellipsisIcon);
                postContainer.append(friendPostTop, postText, info, hr1, likeSection, hr2, commentWrapper);        
                emojiImg.append(likeIcon, hahaIcon, heartIcon, likeCount);
                //info.append(emojiImg, comment, );
                //info.append(comment);
                comment.append(commentCount, shareCount);
                likeIcons.append(likeIconThumbUp, likeTextThumbUp);
                commentIcons.append(commentIconMessage, commentText);
                retweetIcons.append(retweetIconMessage, retweetIconText);
                viewIcons.append(viewIconMessage, viewIconText);
                shareIcons.append(shareIconMessage);                
                likeSection.append(likeIcons, retweetIcons, commentIcons, viewIcons, shareIcons);
                commentWrapper.append(commenterImage, commenterCircle, commentSearch);
                commentSearch.append(commentInput, emojiSmile, cameraIcon, stickyNote);

                posts.push(postContainer);
                likeIconThumbUp.on('click', function() {
				    const isLiked = $(this).hasClass('activi');
				    const userId = currentUser.id;
				    const postId = postDataItem.id;
				    const $this = $(this);
				
				    const requestData = {
				        userId: userId,
				        postId: postId,
				        react: !isLiked
				    };
				
				    reactPost(requestData)
				        .then(response => {
				            if (response.ok) {
				                if (isLiked) {
				                    $this.removeClass('fa-solid fa-heart activi').addClass('fa-regular fa-heart');
				                    likeTextThumbUp.text(parseInt(likeTextThumbUp.text()) - 1);
				                } else {
				                    $this.removeClass('fa-regular fa-heart').addClass('fa-solid fa-heart activi');
				                    likeTextThumbUp.text(parseInt(likeTextThumbUp.text()) + 1);
				                }
				            } else {
				                console.error('Failed to update the reaction:', response.statusText);
				            }
				        })
				        .catch(error => {
				            console.error('Error updating the reaction:', error);
				        });
				});
            }
        });

        return posts;
    } catch (error) {
        console.error('Error generating post:', error);
    }
}

function timeAgo(timestamp) {
    const now = new Date();
    const postTime = new Date(parseInt(timestamp));
    const seconds = Math.floor((now - postTime) / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
	const months = Math.floor(days / 30);
	const years = Math.floor(days / 365);

    if (seconds < 60) {
        return seconds + ' sec';
    } else if (minutes < 60) {
        return minutes + ' min';
    } else if (hours < 24) {
        return hours + ' h';
    } else if (days < 30) {
        return days + ' d';
    } else if (months < 12) {
        return months + ' m';
    } else {
        return years + ' y';
    }
}

function updateTimestamps() {
    $('.time').each(function() {
        const timestamp = $(this).data('timestamp');
        const timeAgoText = timeAgo(timestamp);
        $(this).html(`${timeAgoText} <i class="fa-solid fa-user-group"></i>`);
    });
}



async function appendPost() {
    const postWrapper = $('#postWrapper');
    const posts = await generatePost();
    posts.forEach(post => {
        postWrapper.append(post);
    });
    updateTimestamps();
}

async function appendFriends() {
    try {
        const allFriends = await getAllFriends();
        console.log(allFriends);
        
        const allFriendsContainer = $('<div class="right-contact"></div>');
        if(allFriends.length > 0 && allFriends !== 'No friends found.') {
            allFriends.forEach(friendData => {
                const rightFriendContainerContact = $('<div class="contact"></div>');
                
                const rightFriendContainerImage = $('<img>').attr('src', friendData.profileUrl);
                const rightFriendContainerName = $('<p></p>').text(friendData.name);
                
                // Append image and name to contact container
                rightFriendContainerContact.append(rightFriendContainerImage, rightFriendContainerName);
                console.log("hi");
                // Append contact container to parent container
                allFriendsContainer.append(rightFriendContainerContact);
            });
            // Append parent container to the document
            
        } else {
            const noFriendFound = $('<p></p>').text(allFriends);
            allFriendsContainer.append(noFriendFound);
        }

        $('.third_warpper').append(allFriendsContainer);
        
        
        

    } catch (error) {
        console.error('Error generating contact:', error);
    }
}
