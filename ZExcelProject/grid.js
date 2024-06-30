let rows=100;
let cols=26;

let addresscolcont= document.querySelector(".address-col-cont");
for(let  i=0;i<rows;i++)
{
    let addresscol= document.createElement("div");
    addresscol.setAttribute("class","address-col");
    addresscol.innerText=i+1;
    addresscolcont.appendChild(addresscol);
}

let addressrowcont= document.querySelector(".address-row-cont");
for (let i=0;i<cols;i++)
{
    let addressrow= document.createElement("div");
    addressrow.setAttribute("class","address-row");
    addressrow.innerText=String.fromCharCode(65+i);
    addressrowcont.appendChild(addressrow);
}

let cellscont= document.querySelector(".cells-cont");
for(let i=0;i<rows;i++)
{
    let rowcont= document.createElement("div");
    rowcont.setAttribute("class","rowcont");
    for(let j=0;j<cols;j++)
    {
        let cell= document.createElement("div");
        cell.setAttribute("class","cell");
        cell.setAttribute("contenteditable","true");
        cell.setAttribute("spellcheck",false);
        cell.setAttribute("rid",i);
        cell.setAttribute("cid",j);
        rowcont.appendChild(cell);
        addlistnerforaddressbar(cell,i,j);
    }
    cellscont.appendChild(rowcont);
}

let addressbarcont= document.querySelector(".address-bar");

function addlistnerforaddressbar(cell,i,j){
    cell.addEventListener("click",(e)=>{
        let rowid= i+1;
        let colid= String.fromCharCode(65+j);
        addressbarcont.value= `${colid}${rowid}`;
    })
}





