
for(let i=0;i<rows;i++)
{
    for(let j=0;j<cols;j++)
    {
        let cell= document.querySelector(`.cell[rid="${i}"][cid="${j}"]`);
        cell.addEventListener("blur",(e)=>{
            let address= addressbar.value;
            let [activeCell, cellprop]=activecell(address);
            let enetereddata=activeCell.innerText;


            if(enetereddata===cellprop.value){
                return ;
            }

            cellprop.value=enetereddata;
            // if data modifes update everything 
            
            removechildfromparent(cellprop.formula);
            cellprop.formula="";
            updatechildrencells(address);
        })
    }
}

let formulabar= document.querySelector(".formula-bar");
// afterclicking on enter in formula bar we need to store value on that cell 
// also show that value on UI

formulabar.addEventListener("keydown",async (e)=>{
    let inputformula= formulabar.value
    if(e.key==="Enter"&&  inputformula)
    {
        let address= addressbar.value;
        let [cell,cellprop]=activecell(address);
        if(inputformula!==cellprop.formula)
        {
            //if there is any change in new formula we need to delete parent child relationship
            removechildfromparent(cellprop.formula);

        }
        let evaluatedvalue= evaluateformula(inputformula);

        addchildtographcomponent(inputformula,address);
        //check if there is not any cycle 
        let cycleresponse = isgraphcyclic(graphcomponentmatrix);
        if(cycleresponse)
        {
            // since its cyclic we need to remove chidl from parent here 

            // alert("Your formula is Cyclic. Please change Parent Child relation!");
            let response= confirm("Your Formula is cyclic. Do you want to trace it ?");

            while(response===true)
            {
                // keep on tracking coclor until user clicks on No
                await isgraphcyclicTracePath(graphcomponentmatrix, cycleresponse);// now i want to wait for full traking 

                response= confirm("Your Formula is cyclic. Do you want to trace it ?");
            }

            removechildfromgraphcomponent(inputformula, address);
            return ;
        }


        setcellUIandCellprop(evaluatedvalue,inputformula,address);
        addchildtoparent(inputformula);
        updatechildrencells(address);
        
    }
})

function addchildtographcomponent(formula, childaddress){
    // from formula we will have parent and from childaddress we will decode child address
    let [crid,ccid]=decodeidsfromaddress(childaddress);
    let encodedformula= formula.split(" ");

    for(let i=0;i<encodedformula.length;i++)
    {
        let asciivalue= encodedformula[i].charCodeAt(0);
        if(asciivalue>=65 && asciivalue<=90)
        {
            let[prid,pcid]= decodeidsfromaddress(encodedformula[i]);

            graphcomponentmatrix[prid][pcid].push([crid,ccid]);
        }
    }

}

function removechildfromgraphcomponent(formula, childaddress){
    let [crid,ccid]=decodeidsfromaddress(childaddress);
    let encodedformula= formula.split(" ");

    for(let i=0;i<encodedformula.length;i++)
    {
        let asciivalue= encodedformula[i].charCodeAt(0);
        if(asciivalue>=65 && asciivalue<=90)
        {
            let[prid,pcid]= decodeidsfromaddress(encodedformula[i]);

            graphcomponentmatrix[prid][pcid].pop(); // removing lastly added relation 
        }
    }

}

function updatechildrencells(parentaddress){
    let[parentcell,parentcellprop]= activecell(parentaddress);
    let children= parentcellprop.children;
    for(let i=0;i<children.length;i++)
    {
        let childaddress= children[i];
        let[childcell,childcellprop]= activecell(childaddress);
        let childformula= childcellprop.formula;

        let evaluatedvalue= evaluateformula(childformula);
        setcellUIandCellprop(evaluatedvalue, childformula, childaddress);
        updatechildrencells(childaddress);

    }
}

function addchildtoparent(formula){
    let childaddress= addressbar.value;
    let encodedformula= formula.split(" "); //formula must be space seprated
    for(let i=0;i<encodedformula.length;i++)
    { 
        let asciivalue= encodedformula[i].charCodeAt(0);
        if(asciivalue>=65 && asciivalue<=90){
            //this value needs parent-child formulation  
            let[parentcell,parentcellprop]=activecell(encodedformula[i]);
            parentcellprop.children.push(childaddress);
        }
    }

}

function removechildfromparent(formula){
    let childaddress= addressbar.value;
    let encodedformula= formula.split(" "); //formula must be space seprated
    for(let i=0;i<encodedformula.length;i++)
    { 
        let asciivalue= encodedformula[i].charCodeAt(0);
        if(asciivalue>=65 && asciivalue<=90){
            //this value needs deletion in  parent-child formulation  
            let[parentcell,parentcellprop]=activecell(encodedformula[i]);
            let idx= parentcellprop.children.indexOf(childaddress);
            parentcellprop.children.splice(idx,1);
        }
    }
}

function evaluateformula(formula){
    let encodedformula= formula.split(" "); //formula must be space seprated
    for(let i=0;i<encodedformula.length;i++)
    {
        // we will decode the value using the cell's cellprop object values 
        let asciivalue= encodedformula[i].charCodeAt(0);
        if(asciivalue>=65 && asciivalue<=90){
            //this value needs decoding 
            let[cell,cellprop]=activecell(encodedformula[i]);
            encodedformula[i]=cellprop.value;
        }
    }

    let decodedformula= encodedformula.join(" ");
    return eval(decodedformula);
}

function setcellUIandCellprop(evaluatedvalue, inputformula, address){
    let[cell,cellprop]=activecell(address);
    cell.innerText=evaluatedvalue;//UI change
    cellprop.value=evaluatedvalue; //DB update
    cellprop.formula= inputformula;
}
