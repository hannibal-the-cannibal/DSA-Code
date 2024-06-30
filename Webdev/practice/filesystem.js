let fs= require("fs");
// let output= fs.readFileSync("abc.js");
// console.log(""+output);

for(let i=1;i<=10;i++)
{
    let dirpathname= `Lecture ${i}`;
    fs.mkdirSync(dirpathname);
    fs.writeFileSync(dirpathname+"\\"+"readme.txt",`Text here is ${dirpathname}`);
}
