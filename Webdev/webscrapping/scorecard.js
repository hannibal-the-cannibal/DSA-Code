const request = require('request');
const cheerio = require('cheerio');
const path = require("path");
const fs= require("fs");
const xlsx= require("xlsx");


function processcard(link123){
    const options = {
        url: link123,
        headers: {
          'User-Agent':
            'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
        },
        rejectUnauthorized: false,
        secureProtocol: 'TLSv1_2_method',
        ciphers: 'DEFAULT:!SSLv3:!RC4:!DES:!MD5:!aNULL:!eNULL:!NULL:!DH:!EDH:!EXP:+HIGH:+MEDIUM'
      };
      
      request(options, cb);
}



async function cb(error, response, html) {
  if (error) {
    console.error('error:', error);
  } else {
    extractmatchdata(html);
  }
}

function extractmatchdata(html) {
    // we need venue, data, opponent , runs, runs, balls, fours, sixes, sr
    // venue date and result will go for both the teams 
    let $= cheerio.load(html);
    let result= $("p.ds-text-tight-m.ds-font-regular.ds-truncate.ds-text-typo");
    let event= $("div.ds-text-tight-m.ds-font-regular.ds-text-typo-mid3");
    let eventarr= event.text().split(",");
    let venue= eventarr[1].trim();
    let date = eventarr[2].trim();
    let result1= result.text();

    let innings = $("div.ds-rounded-lg.ds-mt-2");
    let htmlstr="";
    for(let i=0;i<innings.length;i++)
    {
        // htmlstr+= $(innings[i]).html();
        // first need to find team and opponent 
        let teamsarr= $("span.ds-text-title-xs.ds-font-bold.ds-capitalize");
        let team1= $(teamsarr[0]).text();
        let team2= $(teamsarr[1]).text();
        let myteam;
        let oppteam;
        if(i==0)
        {
            myteam=team1;
            oppteam=team2;
        }
        else
        {
            myteam=team2;
            oppteam=team1;
        }
        // now find each player runs balls etc
        let currinning = $(innings[i]);
        let allbatmenrow= currinning.find("table.ds-w-full.ds-table.ds-table-md.ds-table-auto.ci-scorecard-table tbody tr");
        for(let j=0;j<allbatmenrow.length;j++)
        {
            let allcols = $(allbatmenrow[j]).find("td");
            let isworthy= $(allcols[0]).hasClass("ds-w-0 ds-whitespace-nowrap ds-min-w-max ds-flex ds-items-center");
            if(isworthy==true)
            {
                let playername= $(allcols[0]).text().trim();
                let runs= $(allcols[2]).text().trim();
                let balls= $(allcols[3]).text().trim();
                let four= $(allcols[5]).text().trim();
                let six= $(allcols[6]).text().trim();
                let strikerate= $(allcols[7]).text().trim();
                console.log(`${playername}  ${runs}  ${balls}  ${four}  ${six}  ${strikerate}`);
                processplayer(myteam,playername, runs, balls,four,six,strikerate, oppteam, date, venue, result1);



            }

        }

    }

}

function processplayer(myteam,playername, runs, balls,four,six,strikerate, oppteam, date, venue, result1){
  let teampath = path.join(__dirname,"ipl",myteam);
  dircreator(teampath);
  let filepathofplayer= path.join(teampath,playername+".xlsx");
  let content =excelreader(filepathofplayer, playername);
  let playerobj={
    myteam,
    playername,
    runs,
    balls,
    four,
    six,
    strikerate,
    oppteam,
    date,
    venue,
    result1
  }
  content.push(playerobj);
  excelwriter(filepathofplayer, content, playername);


}

function dircreator(filepath){
  if(fs.existsSync(filepath)==false)
  {
    fs.mkdirSync(filepath);
  }
}

function excelwriter(filepath, json, sheetname){
  let newwb= xlsx.utils.book_new(); // creates new worksheet

  let newws= xlsx.utils.json_to_sheet(json); // json data-> excel format converted 

  xlsx.utils.book_append_sheet(newwb, newws, sheetname);

  xlsx.writeFile(newwb,filepath); // wb-> fileaptah, ws-> name, json data
}

function excelreader(filepath, sheetname){
  // read
  if(fs.existsSync(filepath)==false)
  {
      return [];
  }
  let wb = xlsx.readFile(filepath);
  let exceldata= wb.Sheets[sheetname];
  let ans = xlsx.utils.sheet_to_json(exceldata);
  return ans;
}

module.exports={
    ps: processcard
}