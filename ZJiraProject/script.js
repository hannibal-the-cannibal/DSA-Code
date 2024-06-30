let addbutton = document.querySelector(".plus-btn");
let removebutton = document.querySelector(".x-btn");
let modalcont=document.querySelector(".modal-content");
let maincontent= document.querySelector(".main-content");
let textareacont= document.querySelector(".textareacontent");
let addflag=false;
let removeflag=false;

let toolboxcolors= document.querySelectorAll(".color");


let ticketsarr=[];

if(localStorage.getItem("Tickets")){
    //reterive and display tickets 
    ticketsarr= JSON.parse(localStorage.getItem("Tickets"));
    ticketsarr.forEach((ticketobj)=>{
        createticket(ticketobj.ticketcolor,ticketobj.ticketdata,ticketobj.ticketid);
    })
}


let colorsarr=["lightpink", "lightblue","lightorange","lightyellow"];
let modalprioritycolor= colorsarr[0];

let allprioritycolor= document.querySelectorAll(".allcolors");



for(let i=0;i< toolboxcolors.length;i++){
    toolboxcolors[i].addEventListener("click",(e)=>{
        let currtoolboxcolor= toolboxcolors[i].classList[0];

        let filteredticket= ticketsarr.filter((ticketobj, idx)=>{
            return currtoolboxcolor===ticketobj.ticketcolor;
        })

        //removing all tickets
        let allticketcont= document.querySelectorAll(".ticket-cont");
        for(let i=0;i<allticketcont.length;i++){
            allticketcont[i].remove();
        }

        // displaying all filtered tickets
        filteredticket.forEach((ticketobj,idx)=>{
            createticket(ticketobj.ticketcolor,ticketobj.ticketdata,ticketobj.ticketid)
        })

    })

    
    toolboxcolors[i].addEventListener("dblclick",(e)=>{
        //removing all tickets
        let allticketcont= document.querySelectorAll(".ticket-cont");
        for(let i=0;i<allticketcont.length;i++){
            allticketcont[i].remove();
        }

        ticketsarr.forEach((ticketobj,idx)=>{
            createticket(ticketobj.ticketcolor,ticketobj.ticketdata,ticketobj.ticketid);
        })
    })
}

//attaching listner for modal coloring

allprioritycolor.forEach((colorele, idx)=>{
    colorele.addEventListener("click",(e)=>{
        allprioritycolor.forEach((innerele,idx)=>{
            innerele.classList.remove("defaultselect");
        })
        colorele.classList.add("defaultselect");

        modalprioritycolor= colorele.classList[1];
    })
})


addbutton.addEventListener("click", (e)=>{
    //display modal 
    // genretate ticket

    addflag=!addflag;
    if(addflag)
    {
        modalcont.style.display="flex";
    }
    else{
        modalcont.style.display="none";
    }
})

removebutton.addEventListener("click",(e)=>{
    removeflag=!removeflag;

})

modalcont.addEventListener("keydown", (e)=>{
    let key= e.key;
    if(key=="Shift"){
        createticket(modalprioritycolor,textareacont.value);
        addflag=!addflag;
        setmodaldefault();
    }
})
function ticketidgenrate(){
   return shortid();
}

async function createticket(ticketcolor, ticketdata, ticketid){
    let id=  await ticketidgenrate();
    let ticket= document.createElement("div");
    ticket.setAttribute("class", "ticket-cont");
    ticket.innerHTML=`
        <div class="ticketcolor ${ticketcolor}"></div>
        <div class="ticketid">#${ticketid}</div>
        <div class="task-area">${ticketdata}</div>
        <div class="ticket-lock">
                <i class="fa-solid fa-lock"></i>
         </div>
    `;

    maincontent.appendChild(ticket);

    //create objects of all ticket
    if(!ticketid)
    {
        ticketsarr.push({ticketcolor,ticketdata,ticketid: id});
        localStorage.setItem("Tickets", JSON.stringify(ticketsarr));
    }
    

    // every newly created ticket should have functionality 
    // of handling removal 

    handleremoval(ticket,id);

    //handle lock

    handlelock(ticket, id);

    //handle colors of top 

    handlecolor(ticket, id);
}

function handleremoval(ticket, id){
    ticket.addEventListener("click",(e)=>{
        //removeflag==true then remove
    if(!removeflag)
    {
        return ;
    }
   let idxx= getticketidx(id);
   ticketsarr.splice(idxx,1); // db removal
   localStorage.setItem("Tickets", JSON.stringify(ticketsarr));

    ticket.remove(); // UI removal
    })
}

let lockclass= "fa-lock";
let unlockclass="fa-lock-open";

function handlelock(ticket, id){
    let tickettaskarea= ticket.querySelector(".task-area");
    let ticketlockele= ticket.querySelector(".ticket-lock");
    let ticketlock = ticketlockele.children[0];
    ticketlock.addEventListener("click",(e)=>{
        let ticketidx= getticketidx(id);

        if(ticketlock.classList.contains(lockclass))
        {
            ticketlock.classList.remove(lockclass);
            ticketlock.classList.add(unlockclass)
            tickettaskarea.setAttribute("contenteditable","true");
        }
        else
        {
            ticketlock.classList.remove(unlockclass);
            ticketlock.classList.add(lockclass);
            tickettaskarea.setAttribute("contenteditable","false");
        }

        // if it's unlock saving the changes of textarea
        ticketsarr[ticketidx].ticketdata=tickettaskarea.innerText;
        localStorage.setItem("Tickets", JSON.stringify(ticketsarr));



    })
}

function handlecolor(ticket, id){
    let ticketcolor= ticket.querySelector(".ticketcolor");
    ticketcolor.addEventListener("click",(e)=>{

        //getting ticketidx from tickets arr to change color in object
        let ticketidx= getticketidx(id);


        let currticketcolor= ticketcolor.classList[1];
    //get ticket color idx

    let curridx= colorsarr.findIndex((color)=>{
        return currticketcolor===color;
    })

    curridx=(curridx+1)%4;
    let newticketcolor=colorsarr[curridx];

    ticketcolor.classList.remove(currticketcolor);
    ticketcolor.classList.add(newticketcolor);

    //modify data in local storage becausse color has been changed
    ticketsarr[ticketidx].ticketcolor=newticketcolor;
    localStorage.setItem("Tickets", JSON.stringify(ticketsarr));
    })

}

function getticketidx(id){
    let ticketidx= ticketsarr.findIndex((ticketobj)=>{
        return ticketobj.ticketid===id;
    })
    return ticketidx;
}

function setmodaldefault(){
    modalcont.style.display="none";
    textareacont.value="";
    modalprioritycolor= colorsarr[0];
    allprioritycolor.forEach((innerele,idx)=>{
        innerele.classList.remove("defaultselect");
    })
    allprioritycolor[0].classList.add("defaultselect");
}
