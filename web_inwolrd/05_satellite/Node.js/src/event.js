// event 모듈 require
var events = require('events');

console.log("Event Example Started OK");                // 정상동작했다면 코드 출력

// 이벤트 발생기인 EventEmitter 생성
var EventEmitter = new events.EventEmitter();

// 이벤트 처리 함수 예제
var goHome = function goHomeFunc(){
    console.log("집에 가려고 합니다.");

    // 집에 가는 이벤트 발생시키기
    EventEmitter.emit("Sleep");
}

// goHomeNow 메시지 발생 시 goHome 실행
EventEmitter.on("goHomeNow", goHome);               // goHomeNow 메시지가 들어오면 goHome을 실행
EventEmitter.on("Sleep", function(){                // Sleep 메시지가 들어오면 콘솔에 "아무 생각없이 자기" 출력
    console.log("아무 생각없이 자기");
});

for(let i=0; i<10; i++){
    EventEmitter.emit("goHomeNow","Sleep");
}