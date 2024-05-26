$(document).ready(function() {
    appendPost();
    appendFriends()
    // Update timestamps every minute
	setInterval(updateTimestamps, 60000);
	
	// Initial update
	updateTimestamps();
    // What is On your mind Modal
    const textarea = $('#post-desc'); 
    const postBtn = $('.post-btn'); 
    const postAudienceBtn = $('.post-audience'); 
    const backBtn = $('.arrow-left-icon'); 
    const createPostSection = $('.create-post'); 
    const postAudienceSection = $('.post-audience-section'); 
    const emojiBtn = $('.emoji'); 
    const emojiPicker = $('emoji-picker'); 
    const audienceOptions = $(".audience-option"); 
    const radioBtns = $(".audience-option-radio");
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    $('body').css('overflow-x', 'none');

    textarea.on("input", function() {
        if (textarea.val() !== '') {
            postBtn.prop('disabled', false);
        } else {
            postBtn.prop('disabled', true);
        }
    });

    emojiBtn.on("click", function() {
        if (emojiPicker.css('display') === 'none') {
            emojiPicker.css('display', 'block');
        } else {
            emojiPicker.css('display', 'none');
        }
    });

    emojiPicker.on('emoji-click', function(e) {
        textarea.val(textarea.val() + e.detail.unicode);
    });

    postAudienceBtn.on('click', function() {
        $('.wrapper').addClass('wrapper-active');
        postAudienceSection.css('display', 'block');
        createPostSection.css('display', 'none');
    });

    audienceOptions.on('click', function() {
        if (!$(this).hasClass('active')) {
            $(this).addClass('active');
            $(this).find('.audience-option-radio').prop('checked', true);
        }
        audienceOptions.not(this).removeClass('active');
        // radioBtns.prop('checked', false);
    });

    backBtn.on('click', function() {
        $('.wrapper').removeClass('wrapper-active');
        postAudienceSection.css('display', 'none');
        createPostSection.css('display', 'block');
    });

    postBtn.on('click', async function() {
        var userId = currentUser.id;
        var postText = textarea.val();
        var requestData = {
            userId: userId,
            postText: postText
        };
        await createPost(requestData);
    });    
});