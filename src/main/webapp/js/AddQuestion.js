window.addEventListener('DOMContentLoaded', event => {
    const datatablesSimple = document.getElementById('sets-table');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple, {
            labels: {
                placeholder: "Search for sets...",
                perPage: "sets per page",
                noRows: "No sets found",
            },
            classes: {
                active: "active",
                disable: "disabled",
                paginationList: "pagination",
                paginationListItem: "page-item",
                paginationListItemLink: "page-link",
                dropdown: "mb-3",
                wrapper: "col-md-12 d-flex flex-column align-items-center",
                info: "text-center",
            },
            header: false,
            pagerDelta: 1,
            nextPrev: false,
        });
    }
});

function switchTab(tabId, contentId) {
    // Loại bỏ lớp active và show từ tất cả các tab
    let tabContents = document.querySelectorAll('.set-content');
    tabContents.forEach(function(tabContent) {
        tabContent.classList.remove('active', 'show');
    });

    // Loại bỏ lớp active từ tất cả các tab
    let tabs = document.querySelectorAll('.set');
    tabs.forEach(function(tab) {
        tab.classList.remove('active');
    });

    // Thêm lớp active cho tab được chọn
    let selectedTab = document.getElementById(tabId);
    selectedTab.classList.add('active');
    // Thêm lớp active và show cho tab được chọn
    let content = document.getElementById(contentId);
    content.classList.add('active', 'show');
}

function selectAll(setIndex) {
    let checkboxes = document.querySelectorAll('.question-checkbox-' + setIndex);
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = true;
    });
}

function deselectAll(setIndex) {
    let checkboxes = document.querySelectorAll('.question-checkbox-' + setIndex);
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = false;
    });
}

function getCheckedQuestions() {
    let checkedQuestions = [];
    let checkboxes = document.querySelectorAll('input[name="question-ids"]:checked');
    checkboxes.forEach(function(checkbox) {
        checkedQuestions.push(checkbox.value);
    });
    return checkedQuestions.join(',');
}

function submitFormAddQuestion() {
    let form = document.getElementById('add-question');
    let checkboxes = document.querySelectorAll('input[name="question-ids"]:checked');

    if (checkboxes.length < 1) {
        let submitToast = new bootstrap.Toast(document.getElementById('submit-toast'));
        submitToast.show();
        return; // Prevent form submission if conditions are not met
    }
    let questionsInput = form.querySelector('input[name="questions"]');
    questionsInput.value = getCheckedQuestions();
    form.submit();
}