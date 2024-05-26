$(document).ready(function() {

    getSessionUser();
    const currentUser = getData();

    const currentUserName = $('.currentUserName');
    const currentUserImage = $('.currentUserImage');

    const profileImage = currentUser.profileUrl;	    

    setData(currentUser);
    
    function setData(currentUser) {    
        currentUserName.text(currentUser.name);
        currentUserImage.attr('src', profileImage).attr('alt', currentUser.name);
        $('#post-modal-show-input').attr('placeholder', `What's on your mind, ${currentUser.name}?`);
        $('#post-desc').attr('placeholder', `What's on your mind, ${currentUser.name}?`);        
    }
    $('#sign-out-btn').on('click', function(event) {
        signOut();
        window.location.href = '/signOut'        
    });
});

async function getSessionUser() {
    try {
        const response = await sendRequest(`/api/session/create`, 'POST', {});
        if (response.ok) {
            const currentUser = await response.json();
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
            getData();
            
        } else {
            console.error('Error:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function getData() {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser;
}

function signOut() {
    localStorage.removeItem('currentUser');
}