const request = require("request");
const cheerio = require("cheerio");
const fs= require("fs");
const PDFDocument = require('pdfkit');
const path = require("path");


function getissuehtml(fulllink, topic, reponame) {
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
            getissues(html);
        }
    }

    function getissues(html){
        let $= cheerio.load(html);
        let issuearr= $("a.d-block.d-md-none.position-absolute.top-0.bottom-0.left-0.right-0");
        let arr=[];
        for(let i=0;i<issuearr.length;i++)
        {
            let link= $(issuearr[i]).attr("href");
            arr.push(link);
        }
        let folderpath= path.join(__dirname, topic);
        dircreator(folderpath);
        let filepath = path.join(folderpath, reponame+".pdf");
        let pdfDoc = new PDFDocument;
       let text = JSON.stringify(arr);

       pdfDoc.pipe(fs.createWriteStream(filepath));
       pdfDoc.text(text);
       pdfDoc.end();

        
    }
}  

function dircreator(folderpath){
    if(fs.existsSync(folderpath)==false)
    {
        fs.mkdirSync(folderpath);
    }
}



module.exports= getissuehtml;