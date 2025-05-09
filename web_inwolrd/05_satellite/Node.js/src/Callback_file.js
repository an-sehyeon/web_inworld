var fs = require("fs");

var data = fs.readFileSync("main.js");
console.log(data.toString());

console.log("File Read Program Ended OK");

console.error("Error는 이렇게");
fs.readFile("main.js", function(err, data){
    if(err) return console.error(err);
    console.log(data.toString());
});

console.log("\033[95m" + "File Read Program Ended OK2" + "\033[0m");