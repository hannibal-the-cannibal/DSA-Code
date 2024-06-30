const request = require('request');
const cheerio = require('cheerio');

// Define request options
const options = {
  url: 'https://www.worldometers.info/coronavirus',
  headers: {
    'User-Agent':
      'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
  },
  rejectUnauthorized: false, // Allow self-signed certificates
  method: 'GET',
  hostname: 'www.worldometers.info',
  port: 443, // Standard HTTPS port
};

console.log('Before');
request(options, cb);
console.log('After');

async function cb(error, response, html) {
  if (error) {
    console.error('error:', error); // Print the error if one occurred
  } else {
    try {
      const chalk = await import('chalk');
      handlehtml(html, chalk.default);
    } catch (error) {
      console.error('Error loading chalk:', error);
    }
  }
}

function handlehtml(html, chalk) {
  const selTool = cheerio.load(html);
  const contentArr = selTool('#maincounter-wrap span');
  const total = selTool(contentArr[0]).text();
  const deaths = selTool(contentArr[1]).text();
  const recovered = selTool(contentArr[2]).text();

  console.log(chalk.gray('Total Cases: ' + total));
  console.log(chalk.red('Deaths: ' + deaths));
  console.log(chalk.green('Recovery : ' + recovered));
}
