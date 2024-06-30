const request = require('request');
const cheerio = require('cheerio');

const options = {
  url: 'https://www.espncricinfo.com/series/ipl-2020-21-1210595/chennai-super-kings-vs-kings-xi-punjab-53rd-match-1216506/ball-by-ball-commentary',
  headers: {
    'User-Agent':
      'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
  },
  rejectUnauthorized: false, 
  method: 'GET',
  hostname: 'www.espncricinfo.com',
  port: 443, 
};

console.log('Before');
request(options, cb);
console.log('After');

async function cb(error, response, html) {
  if (error) {
    console.error('error:', error); 
  } else {
    extracthtml(html);
  }
}

function extracthtml(html)
{
    let $= cheerio.load(html);
    let elearr = $("p.ci-html-content>b");
   let text = $(elearr[7]).text();
   let htmldata= $(elearr[7]).html();
   console.log(text);
   console.log(htmldata);


}
