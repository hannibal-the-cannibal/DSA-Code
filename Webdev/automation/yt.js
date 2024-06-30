const puppeteer = require('puppeteer');

const pdf= require('pdfkit');
const fs= require('fs');

let link ='https://www.youtube.com/playlist?list=PLRBp0Fe2Gpgm0WF6DEGmb7ab4qHAGlPzg';

let ctab;

(async function(){
    try {
     const browserinstance= await puppeteer.launch({ headless: false,slowMo:true,defaultViewport:null, args:['--start-maximized'],
     executablePath: 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' });

     let alltab= await browserinstance.pages();
     ctab=alltab[0];
     await ctab.goto(link);
     await ctab.waitForSelector(".dynamic-text-container.style-scope.yt-dynamic-sizing-formatted-string");
     let name = await ctab.evaluate(function(select){
        return document.querySelector(select).innerText}, ".dynamic-text-container.style-scope.yt-dynamic-sizing-formatted-string"
     );

     let alldata= ctab.evaluate(getdata, ".byline-item.style-scope.ytd-playlist-byline-renderer");
     console.log(name, (await alldata).noofvideos, (await alldata).noofviews);

     let totalvideos= ((await alldata).noofvideos).split(" ")[0];
     console.log(totalvideos);

     let currpagevideos= await getcurrvideos();
     console.log(currpagevideos);

     while(totalvideos-currpagevideos>=20)
     {
        await scrollbottom();
        currpagevideos= await getcurrvideos();
     }


     let finallist = await getstats();
     let pdfdoc= new pdf;
     pdfdoc.pipe(fs.createWriteStream('play.pdf'));
     pdfdoc.text(JSON.stringify(finallist));
     

     pdfdoc.end();



java.show.server.task.status
    } catch (error) {
        
    }

})()

function getdata(selector){
    let allele= document.querySelectorAll(selector);
    let noofvideos= allele[0].innerText;
    let noofviews= allele[1].innerText;

    return {
        noofvideos,
         noofviews
    }
}

async function getcurrvideos(){
    let length= await ctab.evaluate(getlen, "span.style-scope.ytd-thumbnail-overlay-time-status-renderer");
    return length;
}

function getlen(selector){
    let duraionele= document.querySelectorAll(selector);
    return duraionele.length;

}


async function scrollbottom(){
    await ctab.evaluate(gotobottom);
    function gotobottom(){
        window.scrollBy(0, window.innerHeight);
    }

}

async function getstats(){
    await ctab.waitForSelector('ytd-playlist-video-renderer');
    await ctab.waitForSelector('ytd-thumbnail-overlay-time-status-renderer'); 
    let list=await ctab.evaluate(getnameandduration, ".yt-simple-endpoint.style-scope.ytd-playlist-video-renderer", "span.style-scope.ytd-thumbnail-overlay-time-status-renderer");
    return list;
}


function getnameandduration (videoselector, durationselector){
    let videoele= document.querySelectorAll(videoselector);
    let durationele= document.querySelectorAll(durationselector);

    let currlist=[];

    for(let i=0;i<durationele.length;i++)
    {
        let videotitle=videoele[i].innerText;
        let durationtime= durationele[i].innerText;
        currlist.push({videotitle, durationtime});

    }
    return currlist;
}


