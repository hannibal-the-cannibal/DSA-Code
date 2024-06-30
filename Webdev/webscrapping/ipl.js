const request = require('request');
const cheerio = require('cheerio');
const fs = require("fs");
const path = require("path");
const allmatchobj = require("./allmatch");

const iplpath = path.join(__dirname, "ipl");
dircreator(iplpath);

const options = {
  url: 'https://www.espncricinfo.com/series/ipl-2020-21-1210595',
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
    extractlink(html);
  }
}

function extractlink(html) {
  let $ = cheerio.load(html);
  let achorele = $("a[title='View All Results']"); // getting link of the summary page
  let link = achorele.attr('href');
  let fulllink = 'https://www.espncricinfo.com' + link;

  const optionsnew = {
    url: fulllink,
    headers: {
      'User-Agent':
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
    },
    rejectUnauthorized: false,
    secureProtocol: 'TLSv1_2_method',
    ciphers: 'DEFAULT:!SSLv3:!RC4:!DES:!MD5:!aNULL:!eNULL:!NULL:!DH:!EDH:!EXP:+HIGH:+MEDIUM'
  };

  optionsnewFunction(fulllink, optionsnew);
}

function optionsnewFunction(fulllink, optionsnew) {
  request(optionsnew, cb);
  function cb(error, response, html) {
    if (error) {
      console.error('error:', error);
    } else {
      allmatchobj.gallmatc(html);
    }
  }
}

function dircreator(filepath) {
  if (fs.existsSync(filepath) === false) {
    fs.mkdirSync(filepath);
  }
}
