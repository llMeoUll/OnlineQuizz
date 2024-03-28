window.addEventListener('DOMContentLoaded', event => {
    const datatablesSimple = document.getElementById('leader-table');
    if (datatablesSimple) {
        let table = new simpleDatatables.DataTable(datatablesSimple, {
            labels: {
                placeholder: "Search for leaderboard...",
                perPage: "record per page",
                noRows: "No one has completed any test yet.",
            },
            sortable: false,
            pagerDelta: 1,
        });

        let columns = table.columns;
        columns.hide([6, 7])

        table.on("datatable.selectrow", (rowIndex, event) => {
            let row = table.data.data[rowIndex];
            goToReviewTest(row[6].text, row[4].text, row[7].text);
        });

    }
});

function goToReviewTest(testId, attempt, userId) {
    window.location.href = `./reviewtest?testId=${testId}&&attempt=${attempt}&&userId=${userId}`;
}
