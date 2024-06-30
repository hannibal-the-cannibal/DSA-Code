const request = require("request");
const cheerio = require("cheerio");
const getissuehtml = require("./issue");

function getReposPageHtml(fulllink, topic) {
    const options = {
        url: fulllink,
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
            getreposlink(html);
        }
    }

    function getreposlink(html){
        let $= cheerio.load(html);
        let headingarr=$("h3.f3.color-fg-muted.text-normal.lh-condensed");
    
        console.log(topic);
        for(let i=0;i<8;i++)
        {
            let twoacnchor= $(headingarr[i]).find("a");
            let link=$(twoacnchor[1]).attr("href");
            let fulllink= `https://github.com${link}/issues`;

            let reponame= link.split("/").pop();


            getissuehtml(fulllink, topic, reponame);
        }
        console.log("---------------------");
    }

    
}





module.exports = getReposPageHtml;
