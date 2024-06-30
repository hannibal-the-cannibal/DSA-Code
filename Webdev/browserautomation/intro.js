const puppeteer= require("puppeteer");
console.log("before");
let browseropenpromise=puppeteer.launch();
browseropenpromise.then(function (browser){
    console.log("Browser opened");
});

console.log("after");
