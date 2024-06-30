//storage define and structure of cell
let collectedsheetDB=[]; 
let sheetDB=[];
let activeColorProp="#95a5a6";
let inactiveColorProp="#d2dae2";

{
    let addsheetbtn = document.querySelector(".sheet-add-icon");
    addsheetbtn.click();
}

// for(let i=0;i<rows;i++)
// {
//     let sheetRow=[];
//     for(let j=0;j<cols;j++)
//     {
//         let cellProp={
//             bold: false,
//             italic: false,
//             underline: false,
//             aligment: "left",
//             fontfamily:"monospace",
//             fontsize:"14",
//             fontcolor:"#000000",
//             bgcolor: "#ecf0f1",
//             value:"",
//             formula:"",
//             children:[],  // parent-child relationship for formula calc

//         }
//         sheetRow.push(cellProp);
//     }
//     sheetDB.push(sheetRow);
// }
// selecting all cell properties 
let bold= document.querySelector(".bold");
let italic= document.querySelector(".italic");
let underline= document.querySelector(".underline");
let fontsize= document.querySelector(".font-size-prop");
let fontfamily= document.querySelector(".font-family-prop");
let fontcolor= document.querySelector(".font-color-prop");
let bgcolor= document.querySelector(".bg-color-prop");
let aligment=document.querySelectorAll(".aligment");
let addressbar= document.querySelector(".address-bar");
// selecting aligment from aligment node list 

let leftalign= aligment[0];
let centeralign= aligment[1];
let rightalign= aligment[2];

//application of two way binding 
// attaching listners to each button 
bold.addEventListener("click",(e)=>{
    let address= addressbar.value;
    let [cell,cellprop]=activecell(address);

    //modifictaion 
    cellprop.bold=!cellprop.bold; //data change
    cell.style.fontWeight= cellprop.bold? "bold":"normal"; //UI change(1)
    bold.style.backgroundColor= cellprop.bold? activeColorProp: inactiveColorProp; //UI(2)

})

italic.addEventListener("click",(e)=>{
    let address= addressbar.value;
    let [cell,cellprop]=activecell(address);

    //modifictaion 
    cellprop.italic=!cellprop.italic; //data change
    cell.style.fontStyle= cellprop.italic? "italic":"normal"; //UI change(1)
    italic.style.backgroundColor= cellprop.italic? activeColorProp: inactiveColorProp; //UI(2)

})

underline.addEventListener("click",(e)=>{
    let address= addressbar.value;
    let [cell,cellprop]=activecell(address);

    //modifictaion 
    cellprop.underline=!cellprop.underline //data change
    cell.style.textDecoration= cellprop.underline? "underline":"none"; //UI change(1)
    underline.style.backgroundColor= cellprop.underline? activeColorProp: inactiveColorProp; //UI(2)

})

fontsize.addEventListener("change",(e)=>{
    let address= addressbar.value;
    let [cell,cellprop]=activecell(address);

    //modifictaion 
    cellprop.fontsize= fontsize.value;
    cell.style.fontSize=cellprop.fontsize + "px";
    fontsize.value= cellprop.fontsize;

})

fontfamily.addEventListener("change",(e)=>{
    let address= addressbar.value;
    let [cell,cellprop]=activecell(address);

    //modifictaion 
    cellprop.fontfamily= fontfamily.value;
    cell.style.fontFamily=cellprop.fontfamily
    fontfamily.value= cellprop.fontfamily;

})

fontcolor.addEventListener("change",(e)=>{
    let address= addressbar.value;
    let [cell,cellprop]=activecell(address);

    //modifictaion 
    cellprop.fontcolor= fontcolor.value;
    cell.style.color=cellprop.fontcolor;
    fontcolor.value= cellprop.fontcolor;

})

bgcolor.addEventListener("change",(e)=>{
    let address= addressbar.value;
    let [cell,cellprop]=activecell(address);

    //modifictaion 
    cellprop.bgcolor= bgcolor.value;
    cell.style.backgroundColor=cellprop.bgcolor;
    bgcolor.value= cellprop.bgcolor;

})

aligment.forEach((alignele)=>{
    alignele.addEventListener("click",(e)=>{
        let address= addressbar.value;
        let [cell,cellprop]=activecell(address);

        let alignvalue= e.target.classList[0];
        cellprop.aligment=alignvalue; //Data changes
        cell.style.textAlign= cellprop.aligment; //UI (1)

        switch(alignvalue){ //UI(2)
            case "left":
                leftalign.style.backgroundColor=activeColorProp;
                centeralign.style.backgroundColor=inactiveColorProp;
                rightalign.style.backgroundColor=inactiveColorProp;
                break;
            case "center":
                leftalign.style.backgroundColor=inactiveColorProp;
                centeralign.style.backgroundColor=activeColorProp;
                rightalign.style.backgroundColor=inactiveColorProp;
                break;
            case "right":
                leftalign.style.backgroundColor=inactiveColorProp;
                centeralign.style.backgroundColor=inactiveColorProp;
                rightalign.style.backgroundColor=activeColorProp;
                break;
        }

    })
})

let allcells= document.querySelectorAll(".cell");
for(let i=0;i<allcells.length;i++)
{
    addlistnertoattachCellpropties(allcells[i]);
}


function addlistnertoattachCellpropties(cell){
    cell.addEventListener("click",(e)=>{
        let address= addressbar.value;
        let [rid,cid]=decodeidsfromaddress(address);
        let cellprop= sheetDB[rid][cid];

        // add properties on cells after they are clicked again 
        cell.style.fontWeight= cellprop.bold? "bold":"normal";
        cell.style.fontStyle= cellprop.italic? "italic":"normal";
        cell.style.textDecoration= cellprop.underline? "underline":"none";
        cell.style.fontSize=cellprop.fontsize + "px";
        cell.style.fontFamily=cellprop.fontfamily
        cell.style.color=cellprop.fontcolor;
        cell.style.backgroundColor=cellprop.bgcolor;
        cell.style.textAlign= cellprop.aligment;

        

        // apply properties on container UI 

        bold.style.backgroundColor= cellprop.bold? activeColorProp: inactiveColorProp;
        italic.style.backgroundColor= cellprop.italic? activeColorProp: inactiveColorProp;
        underline.style.backgroundColor= cellprop.underline? activeColorProp: inactiveColorProp;
        fontfamily.value= cellprop.fontfamily;
        fontsize.value= cellprop.fontsize;
        fontcolor.value= cellprop.fontcolor;
        bgcolor.value= cellprop.bgcolor;

        switch(cellprop.aligment){ 
            case "left":
                leftalign.style.backgroundColor=activeColorProp;
                centeralign.style.backgroundColor=inactiveColorProp;
                rightalign.style.backgroundColor=inactiveColorProp;
                break;
            case "center":
                leftalign.style.backgroundColor=inactiveColorProp;
                centeralign.style.backgroundColor=activeColorProp;
                rightalign.style.backgroundColor=inactiveColorProp;
                break;
            case "right":
                leftalign.style.backgroundColor=inactiveColorProp;
                centeralign.style.backgroundColor=inactiveColorProp;
                rightalign.style.backgroundColor=activeColorProp;
                break;
        }

        let formulabar = document.querySelector(".formula-bar");
        formulabar.value=cellprop.formula;
        cell.innerText=cellprop.value;
    })
}



function activecell(address){
    let [rid,cid]= decodeidsfromaddress(address);
    //Access cell and storage eobject 

    let cell= document.querySelector(`.cell[rid="${rid}"][cid="${cid}"]`);
    let cellprop= sheetDB[rid][cid];
    return [cell,cellprop];

}

function decodeidsfromaddress(address){
    //let say adrees is -> "A5"
    let rowid= Number(address.slice(1)-1);
    let colid= Number(address.charCodeAt(0)) - 65;;
    return [rowid,colid];

}

