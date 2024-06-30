let fs= require("fs");
let buffer= fs.readFileSync("./example.json");
let data= JSON.parse(buffer); // we receivve data in array format 
data.push({
        
    "name":"Anil",
    "last Name" : "Bhardwaj",
    "is hero": true,
    "friends":["Manik", "hem"],
    "age": 27,
    "address":{
        "city": "Bikaner",
        "state": "rajasthan"
    }

});
let strdata= JSON.stringify(data); // cannot push the array data need to convert to string 
fs.writeFileSync("./example.json", strdata);
// console.log(data);