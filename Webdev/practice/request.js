const https = require('https');

console.log("before");

const options = {
  hostname: 'www.cricbuzz.com',
  port: 443,
  path: '/live-cricket-scores/75437/ind-vs-aus-5th-match-icc-cricket-world-cup-2023',
  method: 'GET',
  rejectUnauthorized: false, // Allow self-signed certificates
  headers: {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',
  },
};


https.request(options, (response) => {
  let data = '';

  response.on('data', (chunk) => {
    data += chunk;
  });

  response.on('end', () => {
    console.log('body:', data); // Print the HTML
  });

}).on('error', (error) => {
  console.error('error:', error);
}).end();

console.log("after");
