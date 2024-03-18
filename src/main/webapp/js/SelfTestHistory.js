function onRowClick() {
    alert('hello')
}
window.addEventListener('DOMContentLoaded', event => {
    const datatablesSimple = document.getElementById('self-test-history');
    if (datatablesSimple) {
        let table = new simpleDatatables.DataTable(datatablesSimple, {
            labels: {
                perPage: "results per page",
                noRows: "No sets found",
            },
            searchable: false,
            pagerDelta: 1,
            nextPrev: false,

        });

        let columns = table.columns;
        columns.hide([3])


        table.on("datatable.selectrow", (rowIndex, event) => {
            let row = table.data.data[rowIndex];
            let cell = row[3];
            let id = cell.text;
            goToHistoryDetail(id);
        });

    }

});

function goToHistoryDetail(id) {
    window.location.href = "./history/view-detail?stid=" + id;
}