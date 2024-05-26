document.addEventListener("DOMContentLoaded", function() {
    const postModalShowInput = document.getElementById('post-modal-show-input');

    if(postModalShowInput) {
        postModalShowInput.addEventListener("click", function (event) {
            event.preventDefault();
            const modal = document.querySelector('#post-modal');
            modal.style.display = 'block';
        });
    }

    const crossIcon = document.querySelector('.cross-icon');
    if (crossIcon) {
        crossIcon.addEventListener("click", function (event) {
            const modal = document.querySelector('#post-modal');
            modal.style.display = 'none';
        });
    }
});
