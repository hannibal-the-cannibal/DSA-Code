const request = require('request');
const cheerio = require('cheerio');

const options = {
  url: 'https://www.espncricinfo.com/series/ipl-2020-21-1210595/chennai-super-kings-vs-kings-xi-punjab-53rd-match-1216506/full-scorecard',
  headers: {
    'User-Agent':
      'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
  },
  rejectUnauthorized: false,
  method: 'GET',
  hostname: 'www.espncricinfo.com',
  port: 443,
};

// console.log('Before');
request(options, cb);
// console.log('After');

async function cb(error, response, html) {
  if (error) {
    console.error('error:', error);
  } else {
    extracthtml(html);
  }
}

function extracthtml(html) {
  let $ = cheerio.load(html);
  let teamsarr = $("div.ds-flex.ds-items-center.ds-min-w-0.ds-mr-1");
  let winningteam = $(teamsarr[13]).text();
  let loosingteam = $(teamsarr[12]).text();

  console.log("winningteam  " + winningteam);
  console.log("loosingteam  " + loosingteam);

  let inningsarrcontent = $("div.ds-rounded-lg.ds-mt-2");
  let htmlstr = "";
  for (let i = 0; i < inningsarrcontent.length; i++) {
    let teamnamele = $(inningsarrcontent[i]).find("span.ds-text-title-xs.ds-font-bold.ds-capitalize");
    let teamname = teamnamele.text();

    if (winningteam == teamname) {
      // its bowling table we need to get
      let tablearr = $("table.ds-w-full.ds-table.ds-table-md.ds-table-auto.ci-scorecard-table");
      let winningbowlingteam = $(tablearr[i]).find("tbody tr"); // Select the rows inside the current table
      winningbowlingteam.each((index, element) => {
        let pname = $(element).find("td:nth-child(1)").text().trim(); // Extract player name
        let prun = $(element).find("td:nth-child(3)").text().trim(); // Extract runs scored
        if (pname && prun) {
          console.log("Player name: " + pname);
          console.log("Runs Scored : " + prun);
        }
      });
    }
  }
}
