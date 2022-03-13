/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let questions;
$.ajax({
    type: 'post',
    url: 'game/getquestion',
    success: function (response) {
        questions = JSON.parse(response);
        console.log(questions);
    }
})



//selecting all required elements
const start_btn = document.querySelector(".start_btn button");
const info_box = document.querySelector(".info_box");
const exit_btn = info_box.querySelector(".buttons .quit");
const continue_btn = info_box.querySelector(".buttons .restart");
const quiz_box = document.querySelector(".quiz_box");
const result_box = document.querySelector(".result_box");
const option_list = document.querySelector(".option_list");
const time_line = document.querySelector("header .time_line");
const timeText = document.querySelector(".timer .time_left_txt");
const timeCount = document.querySelector(".timer .timer_sec");

//Attribute
let timeValue = 15;
let que_count = 0;
let que_numb = 0;
let userScore = 0;
let counter;
let counterLine;
let widthValue = 0;
let user_answer = [];

//Logic
start_btn.onclick = () => {
    quiz_box.classList.add("activeQuiz");
    showQuetions(0);
    queCounter(0);
    startTimer(timeValue);
    startTimerLine(0);
    for (var i = 0; i < questions.length; i++) {
        user_answer[i] = -1;
    }
}
exit_btn.onclick = () => {
    info_box.classList.remove("activeInfo");
}
continue_btn.onclick = () => {
    info_box.classList.remove("activeInfo");
    quiz_box.classList.add("activeQuiz");
    showQuetions(0);
    queCounter(0);
    startTimer(timeValue);
    startTimerLine(0);
}

const restart_quiz = result_box.querySelector(".buttons .restart");
const quit_quiz = result_box.querySelector(".buttons .quit");
restart_quiz.onclick = () => {
    quiz_box.classList.add("activeQuiz");
    result_box.classList.remove("activeResult");
    timeValue = 15;
    que_count = 0;
    que_numb = 0;
    userScore = 0;
    widthValue = 0;
    showQuetions(que_count);
    queCounter(que_numb);
    clearInterval(counter);
    clearInterval(counterLine);
    startTimer(timeValue);
    startTimerLine(widthValue);
    timeText.textContent = "Time Left";
    next_btn.classList.remove("show");
}
quit_quiz.onclick = () => {
    window.location.reload();
}
const next_btn = document.querySelector("footer .next_btn");
const back_btn = document.querySelector("footer .back_btn");
const submit_btn = document.querySelector("footer .submit_btn");
const bottom_ques_counter = document.querySelector("footer .total_que");

//if Back Que button clicked
back_btn.onclick = () => {
    if (que_count > 0) {
        que_count--;
        showQuetions(que_count);
        next_btn.classList.add("show");
    } else {
        back_btn.classList.remove("show");
    }
    if (que_count == 0) {
        back_btn.classList.remove("show");
    }
}
// if Next Que button clicked
next_btn.onclick = () => {
    if (que_count == questions.length - 1) {
        next_btn.classList.remove("show");
    }
    if (que_count < questions.length - 1) {
        que_count++;
        showQuetions(que_count);
        back_btn.classList.add("show");
    } else {
        next_btn.classList.remove("show");
        submit_btn.classList.add("show");
    }
}
//Submit question
submit_btn.onclick = () => {
    if (confirm("Do you want to submit?")) {
        var tmpQuestions = questions;
        for (var i = 0; i < tmpQuestions.length; i++) {
            delete tmpQuestions[i].media;
        }
        $.ajax({
            type: 'post',
            url: 'submit',
            data: {questions: JSON.stringify(tmpQuestions), userAnswer: JSON.stringify(user_answer)},
            dataType: 'json',
            success: function (response) {
                console.log("Submit success");
            }
        })
    } else {

    }
}


function showQuetions(index) {
    const que_text = document.querySelector(".que_text");
    let que_tag = '<span>' + (index + 1) + ". " + questions[index].content + '</span>';
    let option_tag = "";
    for (var i = 0; i < questions[index].answers.length; i++) {
        option_tag += '<div class="option"><span>' + questions[index].answers[i].content + '</span></div>';
    }
    que_text.innerHTML = que_tag;
    option_list.innerHTML = option_tag;

    const option = option_list.querySelectorAll(".option");
    for (i = 0; i < option.length; i++) {
        option[i].setAttribute("onclick", "optionSelected(this)");
    }
    var blob = new Blob([new Uint8Array(questions[index].media.binaryData)]);

    var reader = new FileReader();
    reader.onload = function (e) {
        imageExists(e.target.result, function (exists) {
            if (exists) {
                var tmpBlob = new Blob([new Uint8Array(questions[index].media.binaryData)]);
                var urlCreator = window.URL || window.webkitURL;
                var imageUrl = urlCreator.createObjectURL(tmpBlob);
                document.querySelector(".que-image").innerHTML = "<img src='" + imageUrl + "'width='440' height='240'>"
            } else {
                audioExists(e.target.result, function (exists) {
                    if (exists) {
                        var tmpBlob = new Blob([new Uint8Array(questions[0].media.binaryData)], {type: "audio/mp3"});
                        var urlCreator = window.URL || window.webkitURL;
                        var audioUrl = urlCreator.createObjectURL(tmpBlob);
                        document.querySelector(".que-image").innerHTML =
                                `<div class="embed-responsive embed-responsive-4by3">
                            <audio src=" ` + audioUrl + `" controls width='440'></audio>
                        </div>`
                    } else {
                        var tmpBlob = new Blob([new Uint8Array(questions[0].media.binaryData)], {type: "video/mp4"});
                        var urlCreator = window.URL || window.webkitURL;
                        var videoUrl = urlCreator.createObjectURL(tmpBlob);
                        document.querySelector(".que-image").innerHTML =
                                `<div class="embed-responsive embed-responsive-4by3">
                            <video src=" ` + videoUrl + `" controls width='440' height='240'   ></video>
                        </div>`
                    }
                })

            }
        });
    };

    reader.readAsDataURL(blob);
    function imageExists(url, callback) {
        var img = new Image();
        img.onload = function () {
            callback(true);
        };
        img.onerror = function () {
            callback(false);
        };
        img.src = url;
    }
    function audioExists(url, callback) {
        var audio = new Audio();
        audio.onload = function () {
            callback(true);
        };
        audio.onerror = function () {
            callback(false);
        };
        audio.src = url;
    }

}
let tickIconTag = '<div class="icon tick"><i class="fas fa-check"></i></div>';
let crossIconTag = '<div class="icon cross"><i class="fas fa-times"></i></div>';
function optionSelected(answer) {
    let userAns = answer.textContent;
    let correcAns = questions[que_count].answer;
    const allOptions = option_list.children.length;

    let isSelected = false;
    for (i = 0; i < allOptions; i++) {
        if (option_list.children[i] == answer) {
            user_answer[que_count] = i;
        }
        if (option_list.children[i].getAttribute("class").includes("selected") == true)
            isSelected = true;
    }
    if (!isSelected) {
        que_numb++;
    }

    if (answer.getAttribute("class").includes("selected")) {
        answer.classList.remove("selected");
        que_numb--;
    } else {
        answer.classList.add("selected");
    }
    for (i = 0; i < allOptions; i++) {
        if (option_list.children[i] != answer)
            option_list.children[i].classList.remove("selected");
    }
    queCounter(que_numb);
//    if (userAns == correcAns) { //if user selected option is equal to array's correct answer
//        userScore += 1; //upgrading score value with 1
//        answer.classList.add("correct"); //adding green color to correct selected option
//        answer.insertAdjacentHTML("beforeend", tickIconTag); //adding tick icon to correct selected option
//        console.log("Correct Answer");
//        console.log("Your correct answers = " + userScore);
//    } else {
//        answer.classList.add("incorrect"); //adding red color to correct selected option
//        answer.insertAdjacentHTML("beforeend", crossIconTag); //adding cross icon to correct selected option
//        console.log("Wrong Answer");
//        for (i = 0; i < allOptions; i++) {
//            if (option_list.children[i].textContent == correcAns) { //if there is an option which is matched to an array answer 
//                option_list.children[i].setAttribute("class", "option correct"); //adding green color to matched option
//                option_list.children[i].insertAdjacentHTML("beforeend", tickIconTag); //adding tick icon to matched option
//                console.log("Auto selected correct answer.");
//            }
//        }
//    }
//    for (i = 0; i < allOptions; i++) {
//        option_list.children[i].classList.add("disabled"); //once user select an option then disabled all options
//    }
    next_btn.classList.add("show"); //show the next button if user selected any option
}
function showResult() {
    info_box.classList.remove("activeInfo");
    quiz_box.classList.remove("activeQuiz");
    result_box.classList.add("activeResult");
    const scoreText = result_box.querySelector(".score_text");
    if (userScore > 3) {
        let scoreTag = '<span>and congrats! , You got <p>' + userScore + '</p> out of <p>' + questions.length + '</p></span>';
        scoreText.innerHTML = scoreTag;
    } else if (userScore > 1) {
        let scoreTag = '<span>and nice , You got <p>' + userScore + '</p> out of <p>' + questions.length + '</p></span>';
        scoreText.innerHTML = scoreTag;
    } else {
        let scoreTag = '<span>and sorry , You got only <p>' + userScore + '</p> out of <p>' + questions.length + '</p></span>';
        scoreText.innerHTML = scoreTag;
    }
}
function startTimer(time) {
    counter = setInterval(timer, 1000);
    timeValue = time;
    function timer() {
        timeCount.textContent = time;
        time--;
        if (time < 9) {
            let addZero = timeCount.textContent;
            timeCount.textContent = "0" + addZero;
        }
        if (time < 0) {
            clearInterval(counter);
            timeText.textContent = "Time Off";
            const allOptions = option_list.children.length;
            let correcAns = questions[que_count].answer;
        }
    }
}
function startTimerLine(time) {
    var delay = 29;
    var step = window.innerWidth / timeValue / 1000 * 29;
    counterLine = setInterval(timer, delay);

    function timer() {
        time += step;
        time_line.style.width = time + "px";
        if (time > window.innerWidth) {
            clearInterval(counterLine);
        }
    }
}
function queCounter(index) {
    let totalQueCounTag = '<span><p>' + index + '</p> of <p>' + questions.length + '</p> Questions</span>';
    bottom_ques_counter.innerHTML = totalQueCounTag;
}

function addQuestion() {
    console.log('a')
}
