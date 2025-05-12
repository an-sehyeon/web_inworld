var http = require('http');

// HttpRequest Option
var options = {
    host: 'localhost',
    port: '10002',
    path: '/index.html'
};

// Callback Function
var callback = function(res){
    // response 이벤트 data 발생 시 데이터를 수신
    var content = "";
    res.on('data', function(data){          // data 메시지가 들어오면 
        content += data;                    // content에 방금 들어온 데이터 저장
    });

    // respnse 이벤트 중 end 이벤트 발생 시 내용을 출력
    res.on('end', function(){               // end 메시지가 들어오면 실행
        // 데이터 디스플레이
        console.log("Data Received");       
        console.log(content);               // 콘솔에 content 출력
    });
}

var request = http.request(options, callback);
request.end();