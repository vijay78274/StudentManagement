console.log("This function is being called!");
document.addEventListener('DOMContentLoaded', () => {
    const loginButton = document.querySelector('.login')
    loginButton.addEventListener('click', () => {
        console.log('login button clicked!');
    });
});
