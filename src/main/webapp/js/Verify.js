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