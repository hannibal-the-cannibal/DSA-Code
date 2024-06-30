const puppeteer = require('puppeteer');
const loginlink= 'https://rc-appdev03.crowe-chizek.com/MRM/feature_33109_multiple_risk/index.html#/mrm/signIn';
const username= 'admin';
const password= 'Crowe@1234';

let page;

const browserPromise = puppeteer.launch({ headless: false,slowMo:true,defaultViewport:null, args:['--start-maximized'],
     executablePath: 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' });


browserPromise.then(function(browserobj){
    let browserpage= browserobj.newPage();
    return browserpage;
}).then(function (newtab){
    page=newtab;
    let hkpromise= newtab.goto(loginlink);
    return hkpromise;
}).then(function (){
    let elewait= page.waitForSelector("input[id='userName']", { visible: true });
    return elewait;
})
.then(function(){
    let emailtype = page.type("input[id='userName']", username, {delay: 50});
    return emailtype;
}).then(function(){
    let passwordtype= page.type("input[id='password']", password, {delay: 50});
    return passwordtype;
}).then(function (){
    let loginclicked= page.click(".btn.btn-primary", {delay: 50});
    return loginclicked;
})