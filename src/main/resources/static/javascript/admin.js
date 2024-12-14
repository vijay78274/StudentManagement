console.log("This function is being called!");
document.addEventListener('DOMContentLoaded', () => {
    const addButton = document.querySelector('.add');
    const modal = document.querySelector('.modal');
    const update = document.querySelector('.update-container');
    const closeButton = document.querySelector('.close');
    const updateclose = document.querySelector('.update-close');
    const updateButtons = document.querySelectorAll('.update');
    // const updateButton = document.querySelector('.update');
    addButton.addEventListener('click', () => {
        console.log('Add button clicked!');
        modal.style.display='flex';
    });
    // updateButton.addEventListener('click', () => {
    //     console.log('update button clicked!');
    //     update.style.display='flex';
    // })
    updateButtons.forEach((button) => {
        button.addEventListener('click', (event) => {
            const studentId = event.target.getAttribute('data-id');
            console.log('Update button clicked for student ID:', studentId);
            update.style.display='flex';
            // window.location.href = `/update-student?id=${studentId}`;
        });
    });
    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
        if(event.target==update){
            update.style.display='none';
        }
    });

    closeButton.addEventListener('click', () => {
        modal.style.display = 'none';
    });
    updateclose.addEventListener('click', () => {
        update.style.display = 'none';
    });
});