let addsheetbtn = document.querySelector(".sheet-add-icon");
let sheetsfoldercont= document.querySelector(".sheets-folder-cont");
addsheetbtn.addEventListener("click", (e)=>{
    let sheet= document.createElement("div");
    sheet.setAttribute("class","sheet-folder");
    
    let allsheetsfolders= document.querySelectorAll(".sheet-folder");
    sheet.setAttribute("id", allsheetsfolders.length);

    sheet.innerHTML=`
    <div class="sheet-content">Sheet ${allsheetsfolders.length+1}</div>
    `;

    sheetsfoldercont.appendChild(sheet);
    sheet.scrollIntoView();

    //DB
    createsheetDB();
    createGraphcomponenetMatrix();
    handlesheetActivness(sheet);
    handlesheetremoval(sheet);
    sheet.click();

})
function handlesheetDB(sheetidx){
    sheetDB= collectedsheetDB[sheetidx];
    graphcomponentmatrix= collectedgraphcomponent[sheetidx];
}

function handlesheetremoval(sheet){
    sheet.addEventListener("mousedown", (e)=>{
        //right click
        if(e.button!==2){
            return;
        }
        let allsheetsfolders= document.querySelectorAll(".sheet-folder");
        if(allsheetsfolders.length==1)
        {
            alert("You need to have atleast one sheet");
            return ;
        }
        let response= confirm("Your sheet will be removed permanentlt. Are you sure?")
        if(response===false)
        {
            return ;
        }
        
        let sheetidx= Number(sheet.getAttribute("id"));
        collectedsheetDB.splice(sheetidx,1);
        collectedgraphcomponent.splice(sheetidx,1);
        handlesheetUIremoval(sheet);
        

        //by default breing sheet 1 to active after deletion 
        sheetDB=collectedsheetDB[0];
        graphcomponentmatrix=collectedgraphcomponent[0];
        handlesheetProperties();
    })
}

function handlesheetUIremoval(sheet){
    sheet.remove();
    let allsheetsfolders= document.querySelectorAll(".sheet-folder");
    for(let i=0;i<allsheetsfolders.length;i++)
    {
        allsheetsfolders[i].setAttribute("id", i);
        let sheetcontent= allsheetsfolders[i].querySelector(".sheet-content");
        sheetcontent.innerText= `Sheet ${i+1}`;
        allsheetsfolders[i].style.backgroundColor="transparent";
    }
    allsheetsfolders[0].style.backgroundColor="#d2dae2";
}

function handlesheetProperties(){
    for(let i=0;i<rows;i++)
    {
        for(let j=0;j<cols;j++)
        {
            let cell= document.querySelector(`.cell[rid="${i}"][cid="${j}"]`);
            cell.click();
        }
    }
    // by default click/select on first cell
    let firstcell= document.querySelector(".cell");
    firstcell.click();
}

function handlesheetActivness(sheet){
    sheet.addEventListener("click", (e)=>{
        let sheetidx= Number(sheet.getAttribute("id"));
        handlesheetDB(sheetidx);
        handlesheetProperties();
        handlesheetUI(sheet);
    })
}

function handlesheetUI(sheet){
    let allsheetsfolders= document.querySelectorAll(".sheet-folder");
    for(let i=0;i<allsheetsfolders.length;i++)
    {
        allsheetsfolders[i].style.backgroundColor="transparent";
    }
    sheet.style.backgroundColor= "#d2dae2";
}

function createsheetDB(){
    let sheetDB=[];
    let activeColorProp="#95a5a6";
    let inactiveColorProp="#d2dae2";

    for(let i=0;i<rows;i++)
    {
        let sheetRow=[];
        for(let j=0;j<cols;j++)
        {
            let cellProp={
                bold: false,
                italic: false,
                underline: false,
                aligment: "left",
                fontfamily:"monospace",
                fontsize:"14",
                fontcolor:"#000000",
                bgcolor: "#ecf0f1",
                value:"",
                formula:"",
                children:[],  // parent-child relationship for formula calc

            }
            sheetRow.push(cellProp);
        }
        sheetDB.push(sheetRow);
    }
    collectedsheetDB.push(sheetDB);
}

function createGraphcomponenetMatrix(){
    let graphcomponentmatrix= [];
    for(let i=0;i<rows;i++)
    {
        let row=[];
        for(let j=0;j<cols;j++)
        {
            // each cell can have more than parent-child relation hence using array for each cell

            row.push([]);

        }
        graphcomponentmatrix.push(row);
    }
    collectedgraphcomponent.push(graphcomponentmatrix);
}