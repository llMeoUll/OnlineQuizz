document.addEventListener('DOMContentLoaded', function () {
    var hourlyData = numberOfActiveUser;
    var ctx = document.getElementById('activeUsersChart').getContext('2d');
    var activeUsersChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: Array.from({ length: 24 }, (_, i) => i),
            datasets: [{
                label: 'Active Users',
                data: hourlyData,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                x: {
                    type: 'linear',
                    position: 'bottom'
                },
                y: {
                    min: 0,
                    max: 50
                }
            }
        }
    });
});