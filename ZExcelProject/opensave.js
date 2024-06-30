let downloadbtn = document.querySelector(".download");
let openbtn= document.querySelector(".open");

//downlaod
downloadbtn.addEventListener("click", (e)=>{
    let jsondata=JSON.stringify([sheetDB,graphcomponentmatrix]);
    let file= new Blob([jsondata], {type: "application/json"});

    let a = document.createElement("a");
    a.href= URL.createObjectURL(file);
    a.download= "SheetData.json";
    a.click();
})


//upload/open 

openbtn.addEventListener("click", (e)=>{
    //opening file explorer in OS 
    let input= document.createElement("input");
    input.setAttribute("type", "file");
    input.click();

    input.addEventListener("change", (e)=>{
        let fr= new FileReader();
        let files= input.files;
        let fileobj= files[0];

        fr.readAsText(fileobj);
        fr.addEventListener("load", (e)=>{
            let readsheetdata= JSON.parse(fr.result);

            //create new sheet with default data will e created 
            addsheetbtn.click();

            //Sheetdb, graphcomp will also be created 
            sheetDB= readsheetdata[0];
            graphcomponentmatrix= readsheetdata[1];
            collectedsheetDB[collectedsheetDB.length-1]=sheetDB;
            collectedgraphcomponent[collectedgraphcomponent.length-1]=graphcomponentmatrix;
            handlesheetProperties();
        })

    })

})