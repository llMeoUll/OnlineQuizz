$(document).on("keydown", "form", function (event) {
    return event.key != "Enter";
});
function calculateScore(qid) {
    let total = parseInt(document.getElementById('total-score').value);
    let checkedQuestions = document.querySelectorAll('input[name="question-ids"]:checked');
    let uncheckedQuestions = document.querySelectorAll('input[name="question-ids"]:not(:checked)');
    let numberOfQuestions = checkedQuestions.length;
    let scorePerQuestion = (total / numberOfQuestions).toFixed(2);
    let scoreInput = document.getElementsByClassName('score');
    if(qid === undefined) {
        Array.from(scoreInput).forEach((input) => {
            input.value = scorePerQuestion;
        });
    } else {
        Array.from(scoreInput).forEach((input) => {
                input.value = scorePerQuestion;
        });
        Array.from(uncheckedQuestions).forEach((input) => {
            document.getElementById(`score-question-${input.value}`).value = 0;
        });
    }
}

function calculateTotalScore() {
    let scoreInput = document.getElementsByClassName('score');
    let total = 0;
    Array.from(scoreInput).forEach(input => {
        total += parseFloat(input.value);
    });
    document.getElementById('total-score').value = total;
}


function deleteQuestionButton (qid) {
    let question = document.getElementById(`card-${qid}`);
    question.remove();
    calculateScore(qid);
    let indexSpan = document.querySelectorAll('.index');
    Array.from(indexSpan).forEach((span, index) => {
        span.textContent = index + 1;
    });
}



function handleSubmit() {
    let form = document.getElementById('review-form');
    let checkboxes = document.querySelectorAll('input[name="question-ids"]:checked');
    if (checkboxes.length < 1) {
        let submitToast = new bootstrap.Toast(document.getElementById('submit-toast'));
        submitToast.show();
        return; // Prevent form submission if conditions are not met
    }
    form.submit();
}
