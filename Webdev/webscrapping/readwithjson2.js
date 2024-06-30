let data= require("./example.json");
let fs= require("fs");
let xlsx= require("xlsx");

function excelwriter(filepath, json, sheetname){
    let newwb= xlsx.utils.book_new(); // creates new worksheet

    let newws= xlsx.utils.json_to_sheet(json); // json data-> excel format converted 

    xlsx.utils.book_append_sheet(newwb, newws, sheetname);

    xlsx.writeFile(newwb,filepath); // wb-> fileaptah, ws-> name, json data
}

function excelreader(filepath, sheetname){
    // read
    if(fs.existsSync(filepath)==false)
    {
        return [];
    }
    let wb = xlsx.readFile(filepath);
    let exceldata= wb.Sheets[sheetname];
    let ans = xlsx.utils.sheet_to_json(exceldata);
    return ans;
}











