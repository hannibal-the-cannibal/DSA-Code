const puppeteer = require('puppeteer');
const loginlink= 'https://www.hackerrank.com/auth/login';
const email= 'shreeshbhardwaj9@gmail.com';
const password= 'Ishan@1122';

const codeobj = require('./answer');



(async function(){
    try {
    const browserinstance= await puppeteer.launch({ headless: false,slowMo:true,defaultViewport:null, args:['--start-maximized'],
     executablePath: 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' });

     let newtab= await browserinstance.newPage();
     await newtab.goto(loginlink);
     await newtab.type("input[id='input-1']", email, {delay: 50});
     await newtab.type("input[id='input-2']", password, {delay: 50});
     await newtab.click("button[data-analytics='LoginPassword']", {delay: 50});

     await waitandclick("a[data-attr1='algorithms']", newtab);

     await waitandclick("input[value='warmup']", newtab);

     let questarr= await newtab.$$("div.challenge-submit-btn", {delay: 50});
     
     await questionsolve(newtab, questarr[1], codeobj.answers[0]);


    } 
    catch (error) {
        console.log(error);
    }
})()


async function waitandclick (selector, cpage){
    await cpage.waitForSelector(selector);
    let selectorclicked= cpage.click(selector);
    return selectorclicked;
}


async function questionsolve(page,question,answer){
    await question.click();
    await waitandclick(".monaco-editor.no-user-select.showUnused.showDeprecated.vs",page);
    await waitandclick("input[class='checkbox-input']", page);
    await waitandclick(".input.text-area.custominput.auto-width", page);
    await page.type(".input.text-area.custominput.auto-width", answer);

    await page.keyboard.down('Control');
    await page.keyboard.press('A', {delay:145});
    await  page.keyboard.press('X', {delay:175});
    await page.keyboard.up('Control');

    await page.click(".monaco-editor.no-user-select.showUnused.showDeprecated.vs", {delay: 500});
    await  page.keyboard.down('Control');
    await page.keyboard.press('A', {delay:145});
    await page.keyboard.press('V', {delay:175});
    await page.keyboard.up('Control');
    await page.click(".ui-btn.ui-btn-normal.ui-btn-primary.pull-right.hr-monaco-submit.ui-btn-styled", {delay: 50});


}