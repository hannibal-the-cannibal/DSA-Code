let fs= require("fs");
let inputarr= process.argv.slice(2);
let optionsarr= [];
let filesarr =[];
for(let i=0;i<inputarr.length;i++)
{
    if(inputarr[i].charAt(0)=='-')
    {
        optionsarr.push(inputarr[i]);
    }
    else
    {
        filesarr.push(inputarr[i]);
    }
}
let content= "";
for(let i=0;i<filesarr.length;i++)
{
    let buffer= fs.readFileSync(filesarr[i]);
    content+=buffer+"\r\n";
}
let contentarr= content.split("\r\n");

//-s

if(optionsarr.includes("-s"))
{
    for(let i=1;i<contentarr.length;i++)
    {
        if(contentarr[i]=="" && contentarr[i-1]=="")
        {
            contentarr[i]=null;
        }
        else if(contentarr[i]=="" && contentarr[i-1]==null)
        {
            contentarr[i]=null;
        }
    }
}
let temparr=[];
for(let i=0;i<contentarr.length;i++)
{
    if(contentarr[i]!=null)
    {
        temparr.push(contentarr[i]);
    }
}
contentarr=temparr;

//-n

if(optionsarr.includes("-n"))
{
    for(let i=0;i<contentarr.length;i++)
    {
        contentarr[i]=`${ i+1 } ${contentarr[i]}`;
    }
}
// console.log(contentarr.join("\n"));

//-b

if(optionsarr.includes("-b"))
{
    let counter=1;
    for(let i=0;i<contentarr.length;i++)
    {
        if(contentarr[i]!="")
        {
            contentarr[i]=`${counter} ${contentarr[i]}`;
            counter++;
        }
    }
}
console.log(contentarr.join("\n"));
