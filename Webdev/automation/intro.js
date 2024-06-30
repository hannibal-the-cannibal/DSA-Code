const puppeteer = require('puppeteer');
let page;

// console.log("before");

const browserPromise = puppeteer.launch({ headless: false,slowMo:true,defaultViewport:null,
     executablePath: 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' });

browserPromise.then(function (browser) {
  const pagesPromise = browser.pages();  // Currently opened tab
  return pagesPromise;
}).then(function (pages) {
    page = pages[0];
    const gotoPromise = page.goto("https://www.google.com/");
    return gotoPromise;
}).then(function () {
    //waiating for element to appear on page
    const waitForSelectorPromise = page.waitForSelector("textarea[title='Search']", { visible: true });
    return waitForSelectorPromise;
}).then(function () {
    // console.log("Reached");
    //type any element on that page 
    let typePromise = page.type("textarea[title='Search']", "pepcoding");
    return typePromise;
}).then(function () {
    //keyboard in used 
    const pressEnterPromise = page.keyboard.press("Enter");
    return pressEnterPromise;
}).then(function (){
    let elewait =page.waitForSelector("h3.LC20lb.MBeuO.DKV0Md", { visible: true });
    return elewait;
}).then(function (){
    //mouse
    let typePromise = page.click("h3.LC20lb.MBeuO.DKV0Md");
})
.catch(function (err) {
    console.error(err);
});

// console.log("after");