// Select all tabs
const tabs = document.querySelectorAll('.tab');

// Add event listener for click on each tab
tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        // Get the data-target attribute value of clicked tab
        const target = tab.dataset.target;
        // Select all tab panels
        const panels = document.querySelectorAll('.tab-panel');
        // Hide all tab panels except the one with the matching data-target value
        panels.forEach(panel => {
            if (panel.id === target) {
                panel.classList.add('active');
            } else {
                panel.classList.remove('active');
            }
        });
        // Remove the active class from all tabs except the clicked one
        tabs.forEach(t => {
            if (t === tab) {
                t.classList.add('active');
            } else {
                t.classList.remove('active');
            }
        });
    });
});