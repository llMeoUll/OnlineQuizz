function scrollToFooter() {
    // Lấy đối tượng footer
    var footer = document.getElementById("footer");

    // Scroll đến vị trí của footer
    footer.scrollIntoView({behavior: "smooth"});
}

let suggestions = [
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

// getting all required elements
const searchInput = document.querySelector(".searchInput");
const input = searchInput.querySelector("input");
const resultBox = searchInput.querySelector(".resultBox");
const icon = searchInput.querySelector(".icon");
let linkTag = searchInput.querySelector("a");
let webLink;

// if user press any key and release
input.onkeyup = (e) => {
    let userData = e.target.value; //user enetered data
    let emptyArray = [];
    if (userData) {
        emptyArray = suggestions.filter((data) => {
            //filtering array value and user characters to lowercase and return only those words which are start with user enetered chars
            return data.toLocaleLowerCase().startsWith(userData.toLocaleLowerCase());
        });
        emptyArray = emptyArray.map((data) => {
            // passing return data inside li tag
            return data = '<li>' + data + '</li>';
        });
        searchInput.classList.add("active"); //show autocomplete box
        showSuggestions(emptyArray);
        let allList = resultBox.querySelectorAll("li");
        for (let i = 0; i < allList.length; i++) {
            //adding onclick attribute in all li tag
            allList[i].setAttribute("onclick", "select(this)");
        }
    } else {
        searchInput.classList.remove("active"); //hide autocomplete box
    }
}

function showSuggestions(list) {
    let listData;
    if (!list.length) {
        userValue = inputBox.value;
        listData = '<li>' + userValue + '</li>';
    } else {
        listData = list.join('');
    }
    resultBox.innerHTML = listData;
}