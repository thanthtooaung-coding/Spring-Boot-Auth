$(document).ready(function() {
var registerBtn = $("#register-btn");
if (registerBtn.length) {
	console.log("hi");
	registerBtn.on("click", async function(event) {
		console.log("hi");
		event.preventDefault();
		
		await createUser();		
	});
}
});
async function createUser() {
    var formData = new FormData();
    formData.append('name', $('#username').val());
    formData.append('email', $('#email').val());
    formData.append('password', $('#password').val());
    formData.append('profileImageInput', $('#profile')[0].files[0]);

    try {
        const response = await fetch('/api/user/create', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error('Failed to create user');
        }

        const responseData = await response.text();
        console.log("Successful:", responseData);
        handleResponse(response, '/vinnet/login');
    } catch (error) {
        console.error('Error:', error);
    }
}

async function search(value) {
    try {
		try {
        var value = value;
        const responseData = await sendRequestWithOneParam('/api/search', 'POST', 'value', value);
        const contentType = responseData.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            const searchResults = await responseData.json();
            console.log(searchResults)
            return searchResults;
        } else {
            const textData = await responseData.text();
            return textData;
        }
        // console.log(allFriends.length);
    } catch (error) {
        console.error('Error:', error);
    }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function createPost(requestData) {    
    try {
        const responseData = await sendRequest(`/api/post/create`, 'POST', requestData);
        handleResponse(responseData, null);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function fetchPosts() {
    try {
        const responseData = await sendRequest(`/api/post/view`, 'POST', {});
		const jsonData = await responseData.json();
		return jsonData;
    } catch (error) {
        console.error('Error:', error);
    }
}

async function reactPost(requestData) {
    try {
        const {userId, postId, react} = requestData;
        const responseData = await sendRequestWithThreeParams('/api/post/react', 'POST','userId', userId, 'postId', postId, 'react', react);
        handleResponse(responseData, null);
        return responseData;
    } catch (error) {
        console.error('Error:', error);
    }
}

async function useFetchedPosts() {
    const postsData = await fetchPosts();
    postsData.forEach(post => {
        const user = post.user;
        console.log(user);
    });
}

async function addFriend(requestData) {
    try {
        const {userId, friendId} = requestData;
        const responseData = await sendRequestWithTwoParams('/api/friendship/addFriend', 'POST', 'userId', userId, 'friendId', friendId);
        handleResponse(responseData, null);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function getAllPendingFriends() {
    var currentUser = getData()
    id = currentUser.id
    try {
        var userId = id;
        const responseData = await sendRequestWithOneParam('/api/friendship/getAllPendingFriends', 'POST', 'friendId', userId);
        const contentType = responseData.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            const allFriends = await responseData.json();
            return allFriends;
        } else {
            const textData = await responseData.text();
            return textData;
        }
        // console.log(allFriends.length);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function acceptOrRemoveFriend(requestData) {
    try {
        const {friendId, userId, status} = requestData;
        const responseData = await sendRequestWithThreeParams('/api/friendship/acceptOrRemoveFriend', 'POST','friendId', friendId, 'userId', userId, 'status', status);
        handleResponse(responseData, null);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function getMutualFriends() {
    try {
        var userId1 = 4;
        var userId2 = 10;
        var responseData = await sendRequestWithTwoParams('/api/friendship/mutualFriends', 'POST', 'userId1', userId1, 'userId2', userId2);
		const mutualFriends = await responseData.json();
        console.log(mutualFriends);
		return mutualFriends;
        
    } catch (error) {
        console.error('Error:', error);
    }
}
async function getAllFriends() {
    var currentUser = getData()
    id = currentUser.id
    try {
        var userId = id;
        const responseData = await sendRequestWithOneParam('/api/friendship/getAllFriends', 'POST', 'userId', userId);
        const contentType = responseData.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            const allFriends = await responseData.json();
            return allFriends;
        } else {
            const textData = await responseData.text();
            return textData;
        }
        // console.log(allFriends.length);
    } catch (error) {
        console.error('Error:', error);
    }
}