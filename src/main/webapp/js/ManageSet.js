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
            pagerDelta: 1,
        });
    }
});