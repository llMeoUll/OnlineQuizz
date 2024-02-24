let availablekeywords = [
    "Biology",
    "Chemistry",
    "Physics",
    "Mathematics",
    "History",
    "Geography",
    "Literature",
    "Vocabulary",
    "Grammar",
    "Spanish",
    "French",
    "German",
    "Mandarin",
    "Japanese",
    "Economics",
    "Psychology",
    "Sociology",
    "Philosophy",
    "Art",
    "Music",
    "Computer Science",
    "Programming",
    "Statistics",
    "Medicine",
    "Nursing",
    "Law",
    "Political Science",
    "Astronomy",
    "Environmental Science",
    "Nutrition",
    "Physical Education",
    "Business",
    "Marketing",
    "Management",
    "Finance",
    "Accounting",
    "Entrepreneurship",
    "Communication",
    "Public Speaking",
    "Debate",
    "Ethics",
    "Cultural Studies",
    "Anthropology",
    "Archaeology",
    "Paleontology",
    "Mythology",
    "Religion",
    "World Religions",
    "Ethics",
    "Leadership",
    "Teamwork",
    "Time Management",
    "Goal Setting",
    "Stress Management",
    "Mindfulness",
    "Motivation",
    "Self-discipline",
    "Creativity",
    "Critical Thinking",
    "Problem Solving",
    "Decision Making",
    "Emotional Intelligence",
    "Cybersecurity",
    "Artificial Intelligence",
    "Machine Learning",
    "Robotics",
    "Cryptocurrency",
    "Blockchain",
    "Internet of Things (IoT)",
    "Virtual Reality (VR)",
    "Augmented Reality (AR)",
    "Renewable Energy",
    "Climate Change",
    "Sustainable Development",
    "Green Living",
    "Yoga",
    "Meditation",
    "Fitness",
    "Nutrition",
    "Healthy Living",
    "Cooking",
    "Gardening",
    "DIY Projects",
    "Photography",
    "Film",
    "Animation",
    "Game Design",
    "Graphic Design",
    "Web Development",
    "Mobile App Development",
    "Social Media",
    "Influencer Marketing",
    "E-commerce",
    "Digital Marketing",
    "Stock Market",
    "Real Estate",
    "Travel",
    "Food",
    "Fashion",
    "Parenting"
];
const resultBox = document.querySelector(".result-box");
const inputBox = document.querySelector("#input-box");

inputBox.onkeyup = function () {
    let result = [];
    let input = inputBox.value;
    if (input.length) {
        result = availablekeywords.filter((data) => {
            return data.toLocaleLowerCase().includes(input.toLocaleLowerCase());
        });
        console.log(result)
    }
    if (result.length === 0) {
        resultBox.innerHTML = ""; // Clear the result box if no matches found
    } else {
        display(result);
    }
    if (!input.length) {
        resultBox.innerHTML = "";
    }
}
function display(result) {
    const slicedResult = result.slice(0, 6);
    const content = slicedResult.map((list) => {
        return `<li onclick=selectInput(this)><i class="fa-solid fa-magnifying-glass"></i> ${list}</li>`;
    });
    resultBox.innerHTML = "<ul>" + content.join('') + "</ul>";
}
function selectInput(list) {
    inputBox.value = element.textContent;
    resultBox.innerHTML = "";
}

