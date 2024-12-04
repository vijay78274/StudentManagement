console.log("This function is being called!");
document.addEventListener('DOMContentLoaded', () => {
    const addButton = document.querySelector('.add');
    const modal = document.querySelector('.modal');
    const closeButton = document.querySelector('.close');
    addButton.addEventListener('click', () => {
        console.log('Add button clicked!');
        modal.style.display='flex';
    });

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    closeButton.addEventListener('click', () => {
        modal.style.display = 'none';
    });
});