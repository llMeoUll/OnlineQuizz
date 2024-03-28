window.addEventListener('DOMContentLoaded', event => {
    const datatablesSimple = document.getElementById('sets-table');
    if (datatablesSimple) {
         new simpleDatatables.DataTable(datatablesSimple, {
            labels: {
                placeholder: "Search for sets...",
                perPage: "sets per page",
                noRows: "No sets found",
            },
            pagerDelta: 1,
        });
    }
});
