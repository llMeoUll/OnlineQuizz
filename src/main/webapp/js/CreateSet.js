$(document).on("keydown", "form", function (event) {
    return event.key != "Enter";
});

// number of questions state
function setNumberOfQuestions() {
    let numberOfQuestions = document.getElementsByClassName("question").length;
    document.querySelector("#number-of-question").value = numberOfQuestions;
}

// number of options state
function setNumberOfOptions(qid) {
    let numberOfOptions = document.querySelectorAll(`#opt-container-${qid} .opt`).length;
    document.getElementById(`number-opt-mul-${qid}`).value = numberOfOptions;
}
// run the function state when the document is loaded
document.addEventListener("DOMContentLoaded", function () {
    setNumberOfQuestions();
});
document.addEventListener("DOMContentLoaded", function () {
    setNumberOfOptions(1);
});



// handle add hashtags
document.addEventListener("DOMContentLoaded", function () {
    const hashtagInput = document.getElementById("hashtag-text");
    const hashtagContainer = document.getElementById("hashtag-container");

    hashtagInput.addEventListener("keyup", function (event) {
        if (event.key === "Enter") {
            const hashtagText = hashtagInput.value.trim();
            if (hashtagText !== "") {
                const hashtagElement = document.createElement("input");
                hashtagElement.classList.add("btn", "btn-outline-primary", "m-1", "hashtag");
                hashtagElement.setAttribute("name", "hashtags");
                hashtagElement.setAttribute("type", "text");
                hashtagElement.value = `#${hashtagText}`;
                hashtagElement.setAttribute("style", `width: ${hashtagText.length + 4}ch;`);
                hashtagContainer.appendChild(hashtagElement);
                hashtagInput.value = "";
            }
        }
    });

    hashtagContainer.addEventListener("click", function (event) {
        if (event.target.classList.contains("hashtag")) {
            event.target.remove();
        }
    });
});

function handleSelectType(id) {
    let selectType = document.getElementById(`question-type-${id}`);
    let type = selectType.options[selectType.selectedIndex].value;
    switch (type) {
        case 'Multiple choice':
            document.getElementById(`multiple-choice-${id}`).style.display = 'block';
            document.getElementById(`true-false-${id}`).style.display = 'none';
            document.getElementById(`essay-${id}`).style.display = 'none';
            break;
        case 'True/False':
            document.getElementById(`multiple-choice-${id}`).style.display = 'none';
            document.getElementById(`true-false-${id}`).style.display = 'block';
            document.getElementById(`essay-${id}`).style.display = 'none';
            break;
        case 'Essay':
            document.getElementById(`multiple-choice-${id}`).style.display = 'none';
            document.getElementById(`true-false-${id}`).style.display = 'none';
            document.getElementById(`essay-${id}`).style.display = 'block';
            break;
    }
}


function handleRemoveOpt(questionId, optId) {
    let container = document.getElementById(`opt-container-${questionId}`);
    let optRemove = container.querySelector(`input[name="mul-question-${questionId}-opt-${optId}"]`).parentNode;
    if (optRemove !== null) {
        container.removeChild(optRemove);
        let list = container.getElementsByClassName("opt");
        Array.from(list).forEach((opt, index) => {
            opt.querySelector("input").name = `mul-question-${questionId}-opt-${index + 1}`;
            opt.querySelector("input").placeholder = "Option " + (index + 1);
            opt.querySelector("span").setAttribute("onclick", `handleRemoveOpt(${questionId}, ${index + 1})`);
        });
        setNumberOfOptions(questionId);
    }
}

function handleAddOpt(id) {
    // Get the container where options will be added
    let container = document.getElementById(`opt-container-${id}`);

    // Create a new div element for the input group
    let newInputGroup = document.createElement("div");
    newInputGroup.classList.add("input-group", "mb-3", "align-items-center", "opt");

    // Create the input element
    let newInput = document.createElement("input");
    newInput.type = "text";
    newInput.classList.add("form-control");
    newInput.name = `mul-question-${id}-opt-${container.childElementCount}`;
    newInput.placeholder = "Option " + (container.childElementCount); // Incremental placeholder

    // Create the span element for the remove button
    let removeButtonSpan = document.createElement("span");
    removeButtonSpan.classList.add("text-primary", "mx-1");
    removeButtonSpan.setAttribute("role", "button");
    removeButtonSpan.innerHTML = '<i class="fa-solid fa-xmark"></i>';
    removeButtonSpan.setAttribute('onClick', `handleRemoveOpt(${id}, ${container.childElementCount})`) // Incremental onclick (remove button

    // Append input and remove button to the new input group
    newInputGroup.appendChild(newInput);
    newInputGroup.appendChild(removeButtonSpan);

    // Append the new input group to the container
    container.appendChild(newInputGroup);
    newInput.focus();
    setNumberOfOptions(id);
}

function handleAddQuestion() {
    // Find the question list container
    let questionList = document.getElementById('question-list');
    let length = questionList.childElementCount;

    // Create a new question card
    let newQuestionCard = document.createElement('div');
    newQuestionCard.classList.add('card', 'mb-3', 'question');
    newQuestionCard.setAttribute('id', `question-container-${length}`);

    // Construct the card header
    let cardHeader = document.createElement('div');
    cardHeader.classList.add('card-header');
    cardHeader.innerHTML = `
        <div class="d-flex align-items-center justify-content-between mb-1 mx-2">
            <div>
                <span class="index">${length}</span>
            </div>
            <div>
                <span role="button" class="text-primary trash-icon" onclick="handleRemoveQuestion(${length})">
                    <i class="fa-solid fa-trash"></i>
                </span>
            </div>
        </div>
        <div class="form-floating">
            <select class="form-select question-type" id= "question-type-${length}" name="question-type-${length}" onchange="handleSelectType(${length});">
                <option value="Multiple choice">Multiple choice</option>
                <option value="True/False">True/False</option>
                <option value="Essay">Essay</option>
            </select>
            <label class="question-type-label" for="question-type-${length}">Type</label>
        </div>`;

    // Construct the card body
    let cardBodyMul = document.createElement('div');
    cardBodyMul.classList.add('card-body', 'multiple-choice');
    cardBodyMul.setAttribute('id', `multiple-choice-${length}`);
    cardBodyMul.innerHTML = `
        <div class="form-floating mb-3">
            <input type="text" class="form-control mul-question" id="mul-question-${length}" name="mul-question-${length}" placeholder="Enter a question"/>
            <label class="mul-question-label" for="mul-question-${length}">Question</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control mul-answer" id="mul-answer-${length}" name="mul-answer-${length}" placeholder="Enter an answer"/>
            <label class="mul-answer-label"  for="mul-answer-${length}">Answer</label>
        </div>
        <div class="opt-container" id="opt-container-${length}">
            <input type="hidden" name="number-opt-mul-${length}" id="number-opt-mul-${length}">
            <div class="input-group mb-3 align-items-center opt">
                <input type="text" name="mul-question-${length}-opt-1" class="form-control" placeholder="Option 1"/>
                <span class="text-primary mx-1 xmark-icon" role="button" onclick="handleRemoveOpt(${length}, 1);">
                    <i class="fa-solid fa-xmark"></i>
                </span>
            </div>
        </div>
        <div class="text-center">
            <span role="button" class="text-primary btn-add-opt" onclick="handleAddOpt(${length});">
                <i class="fa-solid fa-circle-plus"></i>
            </span>
        </div>`;
    let cardBodyTF = document.createElement('div');
    cardBodyTF.classList.add('card-body', 'true-false');
    cardBodyTF.setAttribute('id', `true-false-${length}`);
    cardBodyTF.setAttribute('style', 'display: none;');
    cardBodyTF.innerHTML = `
    <div class="form-floating mb-3">
        <input type="text" class="form-control tf-question" id="tf-question-${length}" name="tf-question-${length}" placeholder="Enter a question"/>
        <label class="tf-question-label" for="tf-question-${length}">Question</label>
    </div>
    <div class="form-floating mb-3">
        <select class="form-select tf-answer" id="tf-answer-${length}" name="tf-answer-${length}">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
        <label class="tf-answer-label" for="tf-answer-${length}">Answer</label>
    </div>
    `;

    let cardBodyEssay = document.createElement('div');
    cardBodyEssay.classList.add('card-body', 'essay');
    cardBodyEssay.setAttribute('id', `essay-${length}`);
    cardBodyEssay.setAttribute('style', 'display: none;');
    cardBodyEssay.innerHTML = `
    <div class="form-floating mb-3">
        <input type="text" class="form-control essay-question" id="essay-question-${length}" name="essay-question-${length}" placeholder="Enter a question"/>
        <label class="essay-question-label" for="essay-question-${length}">Question</label>
    </div>
    <textarea class="form-control essay-answer" id="essay-answer-${length}" name="essay-answer-${length}" placeholder="Enter a answer"
              style="height: 100px"></textarea>
    `;


    // Append card header and body to the question card
    newQuestionCard.appendChild(cardHeader);
    newQuestionCard.appendChild(cardBodyMul);
    newQuestionCard.appendChild(cardBodyTF);
    newQuestionCard.appendChild(cardBodyEssay);

    // Append the question card to the question list
    questionList.insertBefore(newQuestionCard, questionList.lastElementChild);
    setNumberOfQuestions();
    setNumberOfOptions(length);
}

function handleRemoveQuestion(id) {
    // Get the question list container
    let questionList = document.getElementById("question-list");

    // Get all the question cards within the question list
    let questionCard = document.getElementById(`question-container-${id}`);

    // Check if there are any question cards
    if (questionList.childElementCount > 0) {
        // Remove the last question card (you can modify this logic as per your requirement)
        questionCard.remove();
        let questions = document.getElementsByClassName("question");
        Array.from(questions).forEach((question, index) => {
            let id = index + 1;
            question.id = `question-container-${id}`;
            question.querySelector("span").innerHTML = id;
            question.querySelector(".trash-icon").setAttribute("onclick", `handleRemoveQuestion(${id})`);
            question.querySelector(".question-type").id = `question-type-${id}`;
            question.querySelector(".question-type").name = `question-type-${id}`;
            question.querySelector(".question-type").setAttribute("onchange", `handleSelectType(${id})`);
            question.querySelector(".question-type-label").setAttribute("for", `question-type-${id}`);
            question.querySelector(".multiple-choice").id = `multiple-choice-${id}`;
            question.querySelector(".mul-question").id = `mul-question-${id}`;
            question.querySelector(".mul-question").name = `mul-question-${id}`;
            question.querySelector(".mul-question-label").setAttribute("for", `mul-question-${id}`);
            question.querySelector(".mul-answer").id = `mul-answer-${id}`;
            question.querySelector(".mul-answer").name = `mul-answer-${id}`;
            question.querySelector(".mul-answer-label").setAttribute("for", `mul-answer-${id}`);
            question.querySelector(".opt-container").id = `opt-container-${id}`;
            question.querySelectorAll('.otp input[type="text"]').forEach((input, index) => {
                input.name = `mul-question-${id}-opt-${index + 1}`;
            });
            question.querySelectorAll(".opt .xmark-icon").forEach((xmark, index) => {
                xmark.setAttribute("onclick", `handleRemoveOpt(${id}, ${index + 1})`);
            });
            question.querySelector(".btn-add-opt").setAttribute("onclick", `handleAddOpt(${id})`);
            question.querySelector(".true-false").id = `true-false-${id}`;
            question.querySelector(".tf-question").id = `tf-question-${id}`;
            question.querySelector(".tf-question").name = `tf-question-${id}`;
            question.querySelector(".tf-question-label").setAttribute("for", `tf-question-${id}`);
            question.querySelector(".tf-answer").id = `tf-answer-${id}`;
            question.querySelector(".tf-answer").name = `tf-answer-${id}`;
            question.querySelector(".tf-answer-label").setAttribute("for", `tf-answer-${id}`);
            question.querySelector(".essay").id = `essay-${id}`;
            question.querySelector(".essay-question").id = `essay-question-${id}`;
            question.querySelector(".essay-question").name = `essay-question-${id}`;
            question.querySelector(".essay-question-label").setAttribute("for", `essay-question-${id}`);
            question.querySelector(".essay-answer").id = `essay-answer-${id}`;
            question.querySelector(".essay-answer").name = `essay-answer-${id}`;
            setNumberOfQuestions();
        });
    } else {
        console.error("No questions found to remove.");
    }
}


