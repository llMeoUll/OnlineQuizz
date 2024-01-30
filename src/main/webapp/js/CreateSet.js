function handleChooseType() {
    var selectedType = document.getElementById("questionType").value;

    // Hide all containers
    document.getElementById("multipleChoiceContainer").style.display = "none";
    document.getElementById("trueFalseContainer").style.display = "none";
    document.getElementById("essayContainer").style.display = "none";

    // Display the selected container
    document.getElementById(selectedType + "Container").style.display = "block";
}
function createQuestions() {
    // Lấy giá trị số lượng câu hỏi từ ô input
    var numberOfQuestions = document.getElementById("numberOfQuestions").value;

    // Kiểm tra xem số câu hỏi có hợp lệ không (ví dụ: lớn hơn 0)
    if (numberOfQuestions > 0) {
        // Xóa nội dung câu hỏi cũ (nếu có)
        document.getElementById("questionsContainer").innerHTML = "";

        // Tạo một danh sách câu hỏi
        var questions = [];

        // Lặp qua số lượng câu hỏi và tạo từng câu hỏi
        for (var i = 0; i < numberOfQuestions; i++) {
            var questionType = document.getElementById("questionType").value;
            var question;

            switch (questionType) {
                case "multipleChoice":
                    question = {
                        type: "Multiple Choice",
                        prompt: "Multiple Choice Question " + (i + 1),
                        choices: ["Option 1", "Option 2", "Option 3"],
                        correctChoice: 0
                    };
                    break;
                case "trueFalse":
                    question = {
                        type: "True/False",
                        prompt: "True/False Question " + (i + 1),
                        correctAnswer: true
                    };
                    break;
                case "essay":
                    question = {
                        type: "Essay",
                        prompt: "Essay Question " + (i + 1)
                    };
                    break;
                default:
                    alert("Invalid question type");
                    return;
            }

            // Thêm câu hỏi vào danh sách
            questions.push(question);

            // Hiển thị câu hỏi trên trang
            var questionContainer = document.getElementById("questionsContainer");
            questionContainer.innerHTML += "<h3>Question " + (i + 1) + ":</h3>" +
                "<p><strong>Type:</strong> " + question.type + "</p>" +
                "<p><strong>Prompt:</strong> " + question.prompt + "</p>";

            if (question.type === "Multiple Choice") {
                questionContainer.innerHTML += "<p><strong>Choices:</strong> " + question.choices.join(", ") + "</p>" +
                    "<p><strong>Correct Choice:</strong> " + question.choices[question.correctChoice] + "</p>";
            } else if (question.type === "True/False") {
                questionContainer.innerHTML += "<p><strong>Correct Answer:</strong> " + (question.correctAnswer ? "True" : "False") + "</p>";
            }
        }

        // Tại đây, bạn có thể thực hiện các thao tác khác với danh sách câu hỏi
    } else {
        // Hiển thị thông báo nếu số câu hỏi không hợp lệ
        alert("Please enter a number greater than 0 for the number of questions");
    }
}