function handleSelectType () {
    let selectType = document.getElementById('type');
    let type = selectType.options[selectType.selectedIndex].value;
    if (type === 'multiple') {
        document.getElementById('multiple-choice').style.display = 'block';
    } else {
        document.getElementById('multiple-choice').style.display = 'none';
    }
}

function handleAddOpt () {
    let choice = document.getElementById('choice').value;
    let choices = document.getElementById('choices');
    let option = document.createElement('option');
    option.text = choice;
    choices.add(option);
}

$(document).on("keydown", "form", function(event) {
    return event.key != "Enter";
});

document.addEventListener("DOMContentLoaded", function() {
    const hashtagInput = document.getElementById("hashtag-text");
    const hashtagContainer = document.getElementById("hashtag-container");

    hashtagInput.addEventListener("keyup", function(event) {
        if (event.key === "Enter") {
            const hashtagText = hashtagInput.value.trim();
            if (hashtagText !== "") {
                const hashtagElement = document.createElement("span");
                hashtagElement.classList.add("btn", "btn-outline-primary", "mx-1", "hashtag");
                hashtagElement.setAttribute("name", "hashtags");
                hashtagElement.textContent = `#${hashtagText}`;
                hashtagContainer.appendChild(hashtagElement);
                hashtagInput.value = "";
            }
        }
    });

    hashtagContainer.addEventListener("click", function(event) {
        if (event.target.classList.contains("hashtag")) {
            event.target.remove();
        }
    });
});

