const puppeteer = require('puppeteer');
const loginlink= 'https://www.hackerrank.com/auth/login';
const email= 'shreeshbhardwaj9@gmail.com';
const password= 'Ishan@1122';

const codeobj = require('./answer');
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
    let elewait= page.waitForSelector("input[id='input-1']", { visible: true });
    return elewait;
})
.then(function(){
    let emailtype = page.type("input[id='input-1']", email, {delay: 50});
    return emailtype;
}).then(function(){
    let passwordtype= page.type("input[id='input-2']", password, {delay: 50});
    return passwordtype;
}).then(function (){
    let loginclicked= page.click("button[data-analytics='LoginPassword']", {delay: 50});
    return loginclicked;
}).then(function(){
    let elewait= page.waitForSelector("a[data-attr1='algorithms']", { visible: true });
    return elewait;
}).then(function (){
    let loginclicked= page.click("a[data-attr1='algorithms']", {delay: 500});
    return loginclicked;
}).then (function (){
    let gettowarmup= page.waitForSelector("input[value='warmup']", { visible: true });
    return gettowarmup;
}).then(function (){
    let warmupclick= page.click("input[value='warmup']", {delay: 500});
    return warmupclick;
}).then (function (){
    let questarr= page.$$("div.challenge-submit-btn", {delay: 50})
    return questarr;
}).then(function(arrofquest){
    let questionsolvedprmosie= questionsolver(page,arrofquest[0], codeobj.answers[0]);
    return questionsolvedprmosie;
})









function questionsolver(page,question, answer){
    return new Promise(function(resolve, reject){
        let questwillbeclicked= question.click();
        questwillbeclicked.then(function(){
            let editortextpromise= page.waitForSelector(".monaco-editor.no-user-select.showUnused.showDeprecated.vs", { visible: true });
            return editortextpromise;
        }).then(function(){
            let editorclicked= page.click(".monaco-editor.no-user-select.showUnused.showDeprecated.vs", {delay: 500});
            return editorclicked;
        }).then(function(){
            let customcheckbox= page.waitForSelector("input[class='checkbox-input']", { visible: true });
            return customcheckbox;
        }).then(function(){
            let customcheckboxclicked= page.click("input[class='checkbox-input']", {delay: 500});
            return customcheckboxclicked;
        }).then(function()
        {
            let newtextbox= page.waitForSelector(".input.text-area.custominput.auto-width", { visible: true });
            return newtextbox;
        }).then(function(){
            let newtextboxclicked= page.click(".input.text-area.custominput.auto-width", {delay: 500});
            return newtextboxclicked; 
        }).then(function(){
            return page.type(".input.text-area.custominput.auto-width", answer);
        }).then(function (){
            let ctrlispressed= page.keyboard.down('Control');
            return ctrlispressed;
        }).then(function(){
            let aispressed= page.keyboard.press('A', {delay:145});
            return aispressed;
        }).then(function ()
        {
            let xispressed= page.keyboard.press('X', {delay:175});
            return xispressed;
        }).then(function(){
            let ctrlisunpressed= page.keyboard.up('Control');
            return ctrlisunpressed;
        }).then(function(){
            let maineditorpromise= page.waitForSelector(".monaco-editor.no-user-select.showUnused.showDeprecated.vs", { visible: true });
            return maineditorpromise;
        }).then(function(){
            let maineditorclicked= page.click(".monaco-editor.no-user-select.showUnused.showDeprecated.vs", {delay: 500});
            return maineditorclicked;
        }).then(function (){
            let ctrlispressed= page.keyboard.down('Control');
            return ctrlispressed;
        }).then(function(){
            let aispressed= page.keyboard.press('A', {delay:145});
            return aispressed;
        }).then(function(){
            let vispressed= page.keyboard.press('V', {delay:175});
            return vispressed;
        }).then(function(){
            let ctrlisunpressed= page.keyboard.up('Control');
            return ctrlisunpressed;
        }).then(function (){
            let submitpromise= page.click(".ui-btn.ui-btn-normal.ui-btn-primary.pull-right.hr-monaco-submit.ui-btn-styled", {delay: 50});
            return submitpromise;
        }).then(function(){
            resolve();
        }).catch(function(err){
            reject();
        })
    })
}