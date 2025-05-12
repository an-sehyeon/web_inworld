var http = require("http");
var fs = require("fs");
var url = require("url");

// Create Server
http.createServer(function(request,response){
    console.log("Web Server Created");
    // console.log(request);
    console.log(request.url);

    // URL 뒤에 있는 디렉토리 / 파일명 해석
    var pathName = url.parse(request.url).pathname;
    console.log("pathname : " + pathName);

    // Root 접속 시 index.html로 세팅
    if(pathName == "/"){                                // pathName이 아무것도 없을때
        pathName = "/index.html";                       // pathName을 /index.html로 변경
    }

    //Read File
    fs.readFile(pathName.substr(1), function(err, data){
        if(err){                                                    // 에러가 있다면? 파일을 못읽었다면?
            console.log(err);                                       // 콘솔에 err 출력
            // Page Not Found(404) return
            response.writeHead(404, {'Content-Type': 'text/html'});  
        }
        else{
            // 페이지를 찾았음
            response.writeHead(200,{'Content-Type': 'text/html'});

            // 파일을 읽어 Body에 기술
            response.write(data.toString());
        }

        response.end();
    });
}).listen(10002);

console.log("나 노는거 아니야 일하고 있어");