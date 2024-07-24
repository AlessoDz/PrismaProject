document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('.toggle-btn');

    buttons.forEach(button => {
        button.addEventListener('click', function () {
            const targetId = this.getAttribute('data-target');
            const targetElement = document.querySelector(`#${targetId} .text-content`);

            if (targetElement.style.display === 'none') {
                targetElement.style.display = 'block';
                this.textContent = 'Ver menos';
            } else {
                targetElement.style.display = 'none';
                this.textContent = 'Ver más';
            }
        });
    });
});
