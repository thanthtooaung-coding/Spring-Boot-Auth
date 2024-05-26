$(document).ready(function() {
	getMutualFriends();
	appendAllPendingFriends();
});

async function appendAllPendingFriends() {
	const allPendingFriends = await getAllPendingFriends();
	console.table(allPendingFriends)
	const currentUser = JSON.parse(localStorage.getItem('currentUser'));
	const pendingFriends = $('#pending-friends')
	const pendingFriendsContainer = $('<div class="pending-friends-container"></div>');
	if (Array.isArray(allPendingFriends) && allPendingFriends.length > 0) {
		allPendingFriends.forEach(pendingFriend => {
			const pendingFriendsCard = $('<div class="card"></div>');
			const banner = $('<div class="banner"></div>');
			const pendingFriendImage = $('<img class="currentUserImage">').attr('src', pendingFriend.profileUrl);
			banner.append(pendingFriendImage);
			const menu = $('<div class="menu"></div>');
			const pendingFriendName = $('<h2 class="name"></h2>').text(pendingFriend.name);
			const actions = $('<div class="actions"></div>');
			const followInfo = $('<div class="follow-info"></div>');
			const friendsH2 = $('<h2></h2>')
			const friendsH2a = $('<a></a>')
			const friendsCount = $('<span></span>').text(pendingFriend.allFriendsCount);
			const friendsH2Small = $('<small></small>').text("Friends");
			friendsH2a.append(friendsCount, friendsH2Small);
			friendsH2.append(friendsH2a);
	
			const mutualFriendsH2 = $('<h2></h2>')
			const mutualFriendsH2a = $('<a></a>')
			const mutualFriendsCount = $('<span></span>').text(pendingFriend.mutualFriendsCount);
			const mutualFriendsH2Small = $('<small></small>').text("Mutual Friends");
			mutualFriendsH2a.append(mutualFriendsCount, mutualFriendsH2Small);
			mutualFriendsH2.append(mutualFriendsH2a);
	
			followInfo.append(friendsH2, mutualFriendsH2);
	
			const followBtn = $('<div class="follow-btn"></div>');
			const confirmBtn = $('<button>Confirm</button>')
			confirmBtn.click(function() {
				const cardContainer = $(this).closest('.card');
				const searchedUserId = $(this).closest('.follow-btn').find('.friendId').val();
				console.log('Clicked Confirm Friend Id:', searchedUserId);
				var status = "ACCEPT";
				var requestData = {
					friendId: currentUser.id,
					userId: searchedUserId,
					status: status,
				};
				acceptOrRemoveFriend(requestData)
				.then(() => {
		            cardContainer.remove();
		        })
		        .catch((error) => {
		            console.error('Error:', error);
		        });
			});
			const friendIdInput = $('<input type="hidden" class="friendId">').val(pendingFriend.id);
			followBtn.append(friendIdInput);
			const deleteBtn = $('<button style="margin-top: 20px;">Delete</button>')
			deleteBtn.click(function() {
				const cardContainer = $(this).closest('.card');
				const searchedUserId = $(this).closest('.follow-btn').find('.friendId').val();
				console.log('Clicked Delete Friend Id:', searchedUserId);
				var status = "REJECT";
				var requestData = {
					friendId: currentUser.id,
					userId: searchedUserId,
					status: status,
				};
				acceptOrRemoveFriend(requestData)
				.then(() => {
		            cardContainer.remove();
		        })
		        .catch((error) => {
		            console.error('Error:', error);
		        });
			});
			followBtn.append(confirmBtn, deleteBtn)
			actions.append(followInfo, followBtn)
			pendingFriendsCard.append(banner, menu, pendingFriendName, actions);
			pendingFriendsContainer.append(pendingFriendsCard);
		});		
	} else {
		const noResultFound = $('<p>No Pending Friends found.</p>');
        pendingFriendsContainer.append(noResultFound);
	}
	pendingFriends.append(pendingFriendsContainer)
}