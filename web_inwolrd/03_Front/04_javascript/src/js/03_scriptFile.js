
    /* 콘솔에 Program Start 메시지 출력
       이 메시지는 브라우저 개발자 도구 콘솔에서 확인 */
    console.log("scriptFile loaeded");

    function over(arg) {                                /* 마우스를 이미지 위에 올렸을 때 실행되는 기능 */
        arg.src = "./images/color.jpeg";                /* src(이미지)의 주소 변경 */
        arg.width = arg.width * 1.1;                    /* 이미지의 너비를 10% 늘림 */
        console.log("이미지에 커서 올려놓음");            /* 콘솔에 메시지 출력, 
                                                        함수가 제대로 실행되었는지 확인하기 위한 디버깅 정보로 유용*/
    }

    function out(arg) {
        arg.src = "./images/grayscale.jpeg";
        arg.width = arg.width * 10 / 11;                /* 이미지의 너비를 10% 줄임 */
        console.log("현재 이미지 크기 : " + arg.width);
    }

    function clck(arg) {
        arg.width = "1000";
    }

    function dblclck(arg) {
        arg.width = "600";
    }
