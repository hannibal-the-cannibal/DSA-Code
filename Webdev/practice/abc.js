console.log("varname", varname);
var varname;
console.log("varname", varname);
varname="Shreesh";
console.log("varname", varname);
fn();
function fn()
{
    console.log("Hello from fn");
}
fn();
fncontainer();
let fncontainer= function newfn()
{
    console.log("Hello from new fn");
}
fncontainer();
