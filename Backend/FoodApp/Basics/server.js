const http = require('http');
const fs = require('fs');
const _ = require('lodash');
const server= http.createServer((req,res)=>{
    console.log('request done');
    // console.log(req.method);
    // console.log(req.url);

    let num= _.random(0,20);
    console.log(num);

    res.setHeader('Content-Type','text/html');
    // res.write('<h1> hello-Shreesh </h1>');
    // res.write('<h1> how are you?  </h1>');
    // res.end();

    let path =''
    switch(req.url)
    {
        case '/':
            path+='index.html'
            res.statusCode=200;
            break;
        case '/about':
            path+='about.html'
            res.statusCode=200;
            break;
        case '/about-me':
            res.setHeader('Location','/about');
            res.statusCode=301;
            res.end();
            break;
        default:
            path+='404.html'
            res.statusCode=404;
            break;
    };

    fs.readFile(path,(err,filedata)=>{
        if(err)
        {
            console.log(err);
        }
        else{
            
            res.write(filedata);
            res.end();
        }
    })
});

server.listen(3000,'localhost',()=>{
    console.log('server is listening')
})

