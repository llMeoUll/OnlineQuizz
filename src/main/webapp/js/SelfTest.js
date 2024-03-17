function  handleChooseMultiplechoiceQuestion (questionId, optionId) {
    let question = document.getElementById(`option-container-${questionId}`);
    let options = question.getElementsByTagName("input");
    for (let i = 0; i < options.length; i++) {
        if (options[i].id === `question-${questionId}-option-${optionId}`) {
            if(options[i].classList.contains("active") || options[i].name === `answer-${questionId}`){
                options[i].classList.remove("active");
                options[i].removeAttribute("name");
            } else {
                options[i].classList.add("active");
                options[i].setAttribute("name", `answer-${questionId}`);
            }
        } else if(options[i].classList.contains("active") || options[i].name === `answer-${questionId}`){
            options[i].classList.remove("active");
            options[i].removeAttribute("name");
        }
    }
}