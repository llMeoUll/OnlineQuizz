const codes = document.querySelectorAll(".code");

codes[0].focus();
codes.forEach((code, idx) => {
  code.addEventListener("keydown", (e) => {
    if (e.key >= 0 && e.key <= 9) {
      codes[idx].value = "";
      setTimeout(() => codes[idx + 1].focus(), 10);
    } else if (e.key === "Backspace") {
      setTimeout(() => codes[idx - 1].focus(), 10);
    }
  });
});

function submitCode(){

  let digitCode = "";
    codes.forEach((code) => {
        digitCode += code.value;
    });
    if(digitCode.length === 6) {
      document.getElementById("codeInput").value = digitCode;
      document.querySelector("form").submit();
    }
}

// Set the timer for 3 minutes (in seconds)
const timerDuration = 2 * 60;

// Get the countdown element
const countdownElement = document.getElementById('countdown');

// Get the resend button
const resendButton = document.getElementById('btn-resend');

// Update the countdown timer
function updateCountdown(seconds) {
  const minutes = Math.floor(seconds / 60);
  const remainingSeconds = seconds % 60;
  countdownElement.textContent = `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
}

// Start the countdown
let timeLeft = timerDuration;
let countdownInterval = setInterval(() => {
  updateCountdown(timeLeft);
  timeLeft--;

  // If time is up, stop the countdown and enable the resend button
  if (timeLeft < 0) {
    clearInterval(countdownInterval);
    countdownElement.textContent = '0:00';
    resendButton.classList.remove('disabled');
  }
}, 1000);

// Resend button functionality
resendButton.addEventListener('click', () => {
  // Reset the timer
  timeLeft = timerDuration;
  // Restart the countdown
  clearInterval(countdownInterval);
  countdownInterval = setInterval(() => {
    updateCountdown(timeLeft);
    timeLeft--;
    if (timeLeft < 0) {
      clearInterval(countdownInterval);
      countdownElement.textContent = '0:00';
      resendButton.classList.remove('disabled');
    }
  }, 1000);
  // Disable the resend button again
  resendButton.classList.add('disabled');
});