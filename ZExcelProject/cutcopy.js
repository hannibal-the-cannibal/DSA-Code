let ctrlkey;
let copybtn = document.querySelector(".copy");
let pastebtn= document.querySelector(".paste");
let cutbtn= document.querySelector(".cut");



document.addEventListener("keydown", (e)=>{
    ctrlkey=e.ctrlKey;
})

document.addEventListener("keyup", (e)=>{
    ctrlkey=e.ctrlKey;
})

for(let i=0;i<rows;i++)
{
    for(let j=0;j<cols; j++)
    {
        let cell= document.querySelector(`.cell[rid="${i}"][cid="${j}"]`);
        handleselectedcells(cell);
    }
}

let rangestorage=[];
function handleselectedcells(cell){
    cell.addEventListener("click", (e)=>{
        //select cells range 
        if(!ctrlkey)
        {
            return ;
        }
        if(rangestorage.length>=2)
        {
            handleselectedcellsUI();
            rangestorage=[];
        }

        cell.style.border=" 1.5px solid #34495e";

        let rid= Number(cell.getAttribute("rid"));
        let cid= Number(cell.getAttribute("cid"));
        rangestorage.push([rid,cid]);
    })
}

function handleselectedcellsUI(){
    for(let i=0;i<rangestorage.length;i++)
    {
        let cell= document.querySelector(`.cell[rid="${rangestorage[i][0]}"][cid="${rangestorage[i][1]}"]`);
        cell.style.border= " 1px solid #d1d8e0";
    }
}

let copydata=[];
copybtn.addEventListener("click", (e)=>{

    copydata=[];
    for(let i=rangestorage[0][0];i<=rangestorage[1][0];i++)
    {
        if(rangestorage.length<2)
        {
            return ; // as we do not have valid range 
        }
        let copyrow=[];
        for(let j=rangestorage[0][1]; j<=rangestorage[1][1];j++)
        {
            let cellProp= sheetDB[i][j];
            copyrow.push(cellProp);
        }
        copydata.push(copyrow);
    }
    handleselectedcellsUI();
})

pastebtn.addEventListener("click", (e)=>{
    if(rangestorage.length<2)
    {
        return ; // as we do not have valid range 
    }

    let rowdiff= Math.abs(rangestorage[0][0]-rangestorage[1][0]);
    let coldiff= Math.abs(rangestorage[0][1]-rangestorage[1][1]);

    //Target
    let address= addressbar.value;
    let[strow, stcol]=  decodeidsfromaddress(address);

    // rand c denote copydata row and col
    for(let i=strow, r=0;i<=strow+rowdiff;i++,r++)
    {
        for(let j=stcol,c=0;j<=stcol+coldiff;j++,c++)
        {
            let cell= document.querySelector(`.cell[rid="${i}"][cid="${j}"]`);
            if(!cell){
                //if range is not valid we cannot copy outside grid
                continue ;
            }

            // DB change
            let cellprop= sheetDB[i][j];
            let data=copydata[r][c];
            cellprop.value=data.value;
            cellprop.bold=data.bold;
            cellprop.italic=data.italic;
            cellprop.underline=data.underline;
            cellprop.fontsize=data.fontsize;
            cellprop.fontcolor=data.fontcolor;
            cellprop.bgcolor=data.bgcolor;
            cellprop.aligment= data.aligment;

            //UI
            cell.click();
        }
    }

})

cutbtn.addEventListener("click", (e)=>{
    if(rangestorage.length<2)
    {
        return ; // as we do not have valid range 
    }

    for(let i=rangestorage[0][0];i<=rangestorage[1][0];i++)
    {
        for(let j=rangestorage[0][1]; j<=rangestorage[1][1];j++)
        {
            let cell= document.querySelector(`.cell[rid="${i}"][cid="${j}"]`);

            let cellprop= sheetDB[i][j];
            //DB work 
            cellprop.value="";
            cellprop.bold=false;
            cellprop.italic=false;
            cellprop.underline=false;
            cellprop.fontsize=14;
            cellprop.fontcolor="#000000";
            cellprop.bgcolor="#ecf0f1";
            cellprop.aligment= "left";

            //UI
            cell.click();
            handleselectedcellsUI();
        }
    }
})