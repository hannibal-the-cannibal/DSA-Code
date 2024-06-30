//Storage -> 2D Array
let collectedgraphcomponent=[];
let graphcomponentmatrix= [];
// for(let i=0;i<rows;i++)
// {
//     let row=[];
//     for(let j=0;j<cols;j++)
//     {
//         // each cell can have more than parent-child relation hence using array for each cell

//         row.push([]);

//     }
//     graphcomponentmatrix.push(row);
// }

function isgraphcyclic(graphcomponentmatrix){
    //Dependancy-> visited, dfs visited = 2D-Array
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

    for(let i=0;i<rows;i++)
    {
        for(let j=0;j<cols;j++)
        {
            if(visited[i][j]==false)
            {
                // appling loop on every cell and checking for cyclic nature 
                let finalresposne=DFScycledetection(graphcomponentmatrix, i, j, visited, dfsvisited);
                if(finalresposne===true)
                {
                    return [i,j]; // it is starting point of cycle
                }
            } 
        }
    }
    return null;
}

function DFScycledetection(graphcomponentmatrix, srcr, srcc, visited, dfsvisited){
    // first marked that point as true in vsited and dfsvisisted 
    // in end dfs we will mark false as it removed from stack sort of backtrack 
    // cycle found-> if vis of that point =true and dfsvis of that point=true . We have cycle here 
    // Also if vis of that point is true we will return has we have already explored that area 
    
    visited[srcr][srcc]=true;
    dfsvisited[srcr][srcc]=true;

    for(let children=0;children<graphcomponentmatrix[srcr][srcc].length; children++)
    {
        let [crid,ccid]=graphcomponentmatrix[srcr][srcc][children];
        if(visited[crid][ccid]==false)
        {
            let response= DFScycledetection(graphcomponentmatrix,crid,ccid,visited,dfsvisited);
            if (response==true)
            {
                // if any of the point is cyclic whole graph is cyclic 
                return true;
            }
        }
        else if(visited[crid][ccid]== true && dfsvisited[crid][ccid]==true)
        {
            return true;
        }
    }

    dfsvisited[srcr][srcc]=false;
    return false;

}