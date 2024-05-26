$(document).ready(function() {
    $('.search-box').keypress(async function(event) {		
        if (event.which === 13) {
            event.preventDefault(); // Prevent default form submission

            const value = $(this).val();
            console.log('Search value:', value);

            // Redirect to search page with search value as a query parameter
            window.location.href = "/vinnet/search?value=" + encodeURIComponent(value);
        }
    });
});

// Function to fetch search results
async function appendSearchResult() {
    try {
        const params = new URLSearchParams(window.location.search);
        const value = params.get('value');
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));

        if (!value) {
            console.log('No search value provided.');
            return;
        }
        
        $('.search-box').val(value);

        const searchResults = await search(value);
        console.log('Search results:', searchResults);
        console.table(searchResults)

        const searchResultsContainer = $('.search-right-container').empty();

        if (Array.isArray(searchResults) && searchResults.length > 0) {
            searchResults.forEach(searchResult => {
                 const searchUserContainer = $('<div class="search-user-container"></div>');

                const userNameImageContainer = $('<div class="search-user-name-image-container"></div>');
                const userImage = $('<img class="currentUserImage">').attr('src', searchResult.profileUrl);
                const userName = $('<p></p>').text(searchResult.name);

                userNameImageContainer.append(userImage, userName);

                searchUserContainer.append(userNameImageContainer);

                const buttonContainer = $('<div class="button-container"></div>');
                if(searchResult.friendShipStatus == null) {
					const addFriendButton = $('<button class="post-btn">Add Friend</button>');
	                addFriendButton.click(function() {
	                    const searchedUserId = $(this).closest('.search-user-container').find('.userId').val();
	                    console.log('Clicked Add Friend for userId:', searchedUserId);
	                    var requestData = {
	            			userId: currentUser.id,
	            			friendId: searchedUserId
	        			};
	        			addFriend(requestData);
	                });
	                buttonContainer.append(addFriendButton);
				}
				
				if(searchResult.friendShipStatus == "PENDING") {
					const cancelRequestButton = $('<button class="post-btn">Cancel Request</button>');
	                cancelRequestButton.click(function() {
	                    const searchedUserId = $(this).closest('.search-user-container').find('.userId').val();
	                    console.log('Clicked Add Friend for userId:', searchedUserId);
	                    var requestData = {
	            			userId: currentUser.id,
	            			friendId: searchedUserId
	        			};
	        			addFriend(requestData);
	                });
	                buttonContainer.append(cancelRequestButton);
				}  
				
				if(searchResult.friendShipStatus == "ACCEPT") {
					const cancelRequestButton = $('<button class="post-btn">Message</button>');
	                cancelRequestButton.click(function() {
	                    const searchedUserId = $(this).closest('.search-user-container').find('.userId').val();
	                    console.log('Clicked Add Friend for userId:', searchedUserId);
	                    var requestData = {
	            			userId: currentUser.id,
	            			friendId: searchedUserId
	        			};
	        			addFriend(requestData);
	                });
	                buttonContainer.append(cancelRequestButton);
				}                                                         
				
                searchUserContainer.append(buttonContainer);
                
                const userIdInput = $('<input type="hidden" class="userId">').val(searchResult.id);
                searchUserContainer.append(userIdInput);

                searchResultsContainer.append(searchUserContainer);
            });
        } else {
            const noResultFound = $('<p>No result found.</p>');
            searchResultsContainer.append(noResultFound);
        }

    } catch (error) {
        console.error('Error fetching search results:', error);
    }
}


// Call appendSearchResult on page load
$(document).ready(function() {
    appendSearchResult();
});
