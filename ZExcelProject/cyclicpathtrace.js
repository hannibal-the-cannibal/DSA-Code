
function colorPromise() {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve();
        }, 1000);
    })
}

async function isgraphcyclicTracePath(graphcomponentmatrix, cycleresponse){
    let[srcr, srcc]=cycleresponse;

    let visited=[];
    let dfsvisited=[];

    for(let i=0;i<rows;i++)
    {
        let visitedrow=[]; // node tracking 
        let dfsvisitedrow=[]; // stack tracking 
        for(let j=0;j<cols;j++)
        {
            // by deafult we have false
            visitedrow.push(false);
            dfsvisitedrow.push(false);
        }
        visited.push(visitedrow);
        dfsvisited.push(dfsvisitedrow);
    }


    let finalresposne= await DFScycledetectionTracePath(graphcomponentmatrix, srcr, srcc, visited, dfsvisited);
    if(finalresposne===true)
    {
        return Promise.resolve(true);
    }

    return Promise.resolve(false);
}




//Coloring cell for Tracking 
async function DFScycledetectionTracePath(graphcomponentmatrix, srcr, srcc, visited, dfsvisited){

    visited[srcr][srcc]=true;
    dfsvisited[srcr][srcc]=true;

    let cell= document.querySelector(`.cell[rid="${srcr}"][cid="${srcc}"]`);

    cell.style.backgroundColor="lightblue";
    await colorPromise(); //below code will continue after 1000ms

    for(let children=0;children<graphcomponentmatrix[srcr][srcc].length; children++)
    {
        let [crid,ccid]=graphcomponentmatrix[srcr][srcc][children];
        if(visited[crid][ccid]==false)
        {
            let response=  await DFScycledetectionTracePath(graphcomponentmatrix,crid,ccid,visited,dfsvisited);
            if (response==true)
            {
                cell.style.backgroundColor="transparent";
                await colorPromise();
                // if any of the point is cyclic whole graph is cyclic 
                return Promise.resolve(true);
            }
        }
        else if(visited[crid][ccid]== true && dfsvisited[crid][ccid]==true)
        {
            let cycliccell= document.querySelector(`.cell[rid="${crid}"][cid="${ccid}"]`);
            cycliccell.style.backgroundColor="lightsalmon";
            await colorPromise();
            cycliccell.style.backgroundColor="transparent";
            
            cell.style.backgroundColor="transparent";

            await colorPromise();
            return Promise.resolve(true);
        }
    }

    dfsvisited[srcr][srcc]=false;
    return Promise.resolve(false);

}