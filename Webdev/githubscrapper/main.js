const request = require("request");
const cheerio= require("cheerio");
const PDFDocument = require('pdfkit');
const getrepospagehtml = require("./repopage");



const options = {
    url: 'https://github.com/topics',
    headers: {
      'User-Agent':
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
    },
    rejectUnauthorized: false,
    secureProtocol: 'TLSv1_2_method',
    ciphers: 'DEFAULT:!SSLv3:!RC4:!DES:!MD5:!aNULL:!eNULL:!NULL:!DH:!EDH:!EXP:+HIGH:+MEDIUM'
  };

request(options, cb);

function cb(error, response, html) {
  if (error) {
    console.error('error:', error);
  } else {
    gettopiclinks(html);
  }
}

function gettopiclinks(html){
    let $= cheerio.load(html);
    let linksarr= $("a.no-underline.d-flex.flex-column.flex-justify-center");
    for(let i=0;i<linksarr.length;i++)
    {
        let href=$(linksarr[i]).attr("href");
        let topic= href.split("/").pop();
        let fulllink= `https://github.com${href}`;
        getrepospagehtml(fulllink, topic);
    }
}