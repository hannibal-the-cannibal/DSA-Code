const { dir } = require("console");
let fs= require("fs");
let path = require("path");

let inputarr= process.argv.slice(2);
let command= inputarr[0];
let types={
    media:["mp4","mkv"],
    pdf: ["pdf"],
    excel: ["xls","xlsx"],
    others: ["exe","zip"]
}
switch(command)
{
    case "tree":
        treefn(inputarr[1])
        break;
    case "organise":
        organizefn(inputarr[1])
        break;
    case "help":
        helpfn(inputarr[1])
        break;
    deafult: 
        console.log("Please enter valid values");
        break;
}
function treefn(dirpath)
{
    console.log("Tree function excecuted for ", dirpath);
}

function organizefn(dirPath) {
    let destPath;
    if (dirPath == undefined) {
        destPath = process.cwd();
        return;
    } else {
        let doesExist = fs.existsSync(dirPath);
        if (doesExist) {

            destPath = path.join(dirPath, "organized_files");
            if (fs.existsSync(destPath) == false) {
                fs.mkdirSync(destPath);
            }

        } else {

            console.log("Kindly enter the correct path");
            return;
        }
    }
    organizeHelper(dirPath, destPath);
}
function helpfn()
{
    console.log(`
    this is helper function 
    first line
    second line
    `);
}

function organizeHelper(src, dest) {
    let childNames = fs.readdirSync(src);
    for (let i = 0; i < childNames.length; i++) {
        let childAddress = path.join(src, childNames[i]);
        let isFile = fs.lstatSync(childAddress).isFile();
        if (isFile) {
            let category = getCategory(childNames[i]);
            // console.log(childNames[i], "belongs to --> ", category);
            sendFiles(childAddress, dest, category);
        }
    }
}

function  getCategory(name)
{
    let ext= path.extname(name);
    ext = ext.slice(1).toLowerCase();

    for(let type in types)
    {
        let currtype= types[type];
        for(let i=0;i<currtype.length;i++)
        {
            if(ext==currtype[i])
            {
                return type;
            }
        }
    }
    return "others";
    
}
function sendFiles(srcFilePath, dest, category) {
    let categoryPath = path.join(dest, category);
    if (fs.existsSync(categoryPath) == false) {
        fs.mkdirSync(categoryPath);
    }
    let fileName = path.basename(srcFilePath);
    let destFilePath = path.join(categoryPath, fileName);
    fs.copyFileSync(srcFilePath, destFilePath);
}