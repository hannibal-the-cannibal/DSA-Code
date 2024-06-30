const request = require('request');
const cheerio = require('cheerio');
const scorecardobj= require("./scorecard");


function fullmatchlink(html){
    let $ = cheerio.load(html);
    let matchlistarr= $("div.ds-grow.ds-px-4.ds-border-r.ds-border-line-default-translucent a");
    let uniqueLinks = [];
    for(let i=0;i<matchlistarr.length;i++) {
        let link =$(matchlistarr[i]).attr("href");
        let fulllink = 'https://www.espncricinfo.com' + link;
        if(!uniqueLinks.includes(fulllink)){
            uniqueLinks.push(fulllink);
        }
    }
    if(uniqueLinks.length >= 2){
        uniqueLinks.splice(1, 1); // remove the second element that unwanted 
    }
    for(let k=0 ;k<uniqueLinks.length;k++)
    {
        console.log(uniqueLinks[k]);
        scorecardobj.ps(uniqueLinks[k]);
    }
}

module.exports={
    gallmatc: fullmatchlink
}