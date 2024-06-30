let path = require("path");
for(let i=1;i<=10;i++)
{
    let dirpathname= `Lecture ${i}`;
    fs.mkdirSync(dirpathname);
    fs.writeFileSync(path.join(dirpathname,"readme.txt"),`Text here is ${dirpathname}`);
}
