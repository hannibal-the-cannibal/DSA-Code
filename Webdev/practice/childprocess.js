let cp= require("child_process");
cp.execSync("calc");
let output= cp.execSync("node abc.js");
console.log("output will be", ""+output);