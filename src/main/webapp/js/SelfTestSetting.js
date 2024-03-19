document.getElementById('setting-form').addEventListener('submit', function(event) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    var isChecked = false;
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            isChecked = true;
        }
    });
    if (!isChecked) {
        alert('Please check at least one checkbox.');
        event.preventDefault(); // Prevent form submission
    }
});